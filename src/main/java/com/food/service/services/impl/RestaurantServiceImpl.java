package com.food.service.services.impl;

import com.food.service.dto.request.RestaurantRequest;
import com.food.service.dto.response.AddressResponse;
import com.food.service.model.Address;
import com.food.service.model.Restaurant;
import com.food.service.model.TypePayment;
import com.food.service.repository.KitchenRepository;
import com.food.service.repository.PaymentRepository;
import com.food.service.repository.RestaurantRepository;
import com.food.service.services.RestaurantService;
import com.food.service.utils.ViaCep;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KitchenRepository kitchenRepository;
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final ViaCep viaCep;

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUM RESTAURANTE ENCONTRADO!"));
    }

    @Override
    public List<Restaurant> findByName(String name) {
        List<Restaurant> restaurants = restaurantRepository.findByNameContains(name);
        if (restaurants.isEmpty()) {
            throw new EntityNotFoundException("NENHUM RESTAURANTE ENCONTRADO!");
        }
        return restaurants;
    }

    @Override
    public List<Restaurant> listAll() {

        List<Restaurant> restaurant = restaurantRepository.findAll();
        if (restaurant.isEmpty()) {
            throw new EntityNotFoundException("NENHUM RESTAURANTE ENCONTRADO!");
        }
        return restaurant;
    }

    @Override
    public Restaurant create(RestaurantRequest request) {

        Restaurant restaurant = new Restaurant();
        Address address = new Address();

        try {
            var kitchen = kitchenRepository.findById(request.getKitchenId());
            if (kitchen.isEmpty()) {
                throw new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!");
            }

            List<TypePayment> payment = paymentRepository.findAllById(request.getTypePaymentId());
            if (payment.isEmpty()) {
                throw new EntityNotFoundException("NENHUMA FORMA DE PAGAMENTO ENCONTRADA!");
            }

            Optional<AddressResponse> response = viaCep.getAddress(request);
            if (response.isEmpty()) {
                throw new ResourceAccessException("ENDEREÇO INVÁLIDO OU NÃO ENCONTRADO!");
            }

            restaurant = modelMapper.map(request, Restaurant.class);

            restaurant.setActive(true);
            restaurant.setOpenUp(false);
            restaurant.setDateRegistrer(LocalDateTime.now());
            restaurant.setKitchen(kitchen.get());
            restaurant.setTypePayments(payment);

            address.setPostalCode(response.get().getCep());
            address.setAddress(response.get().getLogradouro());
            address.setCity(response.get().getLocalidade());
            address.setState(response.get().getUf());
            address.setDistrict(response.get().getBairro());
            address.setPlaceNumber(request.getPlaceNumber());

            restaurant.setAddress(address);

        } catch (Exception e) {
            throw new ResourceAccessException("NÃO FOI POSSIVEL CRIAR O RESTAURANTE!");
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(RestaurantRequest request, Long id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) {
            throw new EntityNotFoundException("NENHUM RESTAURANTE ENCONTRADO!");
        }

        Optional<AddressResponse> response = viaCep.getAddress(request);
        if (response.isEmpty()) {
            throw new ResourceAccessException("ENDEREÇO INVÁLIDO OU NÃO ENCONTRADO!");
        }

        Address address = new Address();
        try {

            // Lógica para atualizar apenas um campo caso os outros sejam nulos ou vazios no JSON
            restaurant.get().setName(request.getName().equals("") || request.getName() == null ? restaurant.get().getName() : request.getName());
            restaurant.get().setDeliveryFee(request.getDeliveryFee() == null ? restaurant.get().getDeliveryFee() : request.getDeliveryFee());
            restaurant.get().setActive(request.getActive() == null ? restaurant.get().getActive() : request.getActive());
            restaurant.get().setOpenUp(request.getOpen() == null ? restaurant.get().getOpenUp() : request.getOpen());
            restaurant.get().setDateUpdate(LocalDateTime.now());
            restaurant.get().setKitchen(request.getKitchenId() == null ? restaurant.get().getKitchen() : kitchenRepository.findById(request.getKitchenId()).get());
            restaurant.get().setTypePayments(request.getTypePaymentId() == null ? restaurant.get().getTypePayments() : paymentRepository.findAllById(request.getTypePaymentId()));

            address.setPlaceNumber(request.getPlaceNumber() == null || request.getPlaceNumber().equals("")? restaurant.get().getAddress().getPlaceNumber() : request.getPlaceNumber());
            address.setPostalCode(response.get().getCep());
            address.setAddress(response.get().getLogradouro());
            address.setCity(response.get().getLocalidade());
            address.setState(response.get().getUf());
            address.setDistrict(response.get().getBairro());

            restaurant.get().setAddress(address);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceAccessException("NÃO FOI POSSIVEL ATUALIZAR O RESTAURANTE!");
        }
        return restaurantRepository.save(restaurant.get());
    }

    @Override
    public void delete(Long id) {
        try {
            restaurantRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            throw new EntityNotFoundException("NÃO FOI POSSIVEL EXCLUIR O RESTAURANTE!");
        }
    }
}
