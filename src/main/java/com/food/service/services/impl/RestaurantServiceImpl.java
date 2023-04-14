package com.food.service.services.impl;

import com.food.service.dto.request.RestaurantRequest;
import com.food.service.model.Payment;
import com.food.service.model.Restaurant;
import com.food.service.repository.KitchenRepository;
import com.food.service.repository.PaymentRepository;
import com.food.service.repository.RestaurantRepository;
import com.food.service.services.RestaurantService;
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

        try {
        var kitchen = kitchenRepository.findById(request.getKitchenId());
        if (kitchen.isEmpty()) {
            throw new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!");
        }

        Optional<Payment> payment = paymentRepository.findById(request.getPaymentId());
        if (payment.isEmpty()) {
            throw new EntityNotFoundException("NENHUMA FORMA DE PAGAMENTO ENCONTRADA!");
        }

            restaurant = modelMapper.map(request, Restaurant.class);

            restaurant.setActive(true);
            restaurant.setOpen(false);
            restaurant.setDateRegistrer(LocalDateTime.now());
            restaurant.setKitchen(kitchen.get());
            restaurant.setPayment(payment.get());

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

        try {

            // Lógica para atualizar apenas um campo caso os outros sejam nulos ou vazios no JSON
            restaurant.get().setName(request.getName().equals("") || request.getName() == null ? restaurant.get().getName() : request.getName());
            restaurant.get().setDeliveryFee(request.getDeliveryFee() == null ? restaurant.get().getDeliveryFee() : request.getDeliveryFee());
            restaurant.get().setActive(request.getActive() == null ? restaurant.get().getActive() : request.getActive());
            restaurant.get().setOpen(request.getOpen() == null ? restaurant.get().getOpen() : request.getOpen());
            restaurant.get().setDateUpdate(LocalDateTime.now());
            restaurant.get().setKitchen(request.getKitchenId() == null ? restaurant.get().getKitchen() : kitchenRepository.findById(request.getKitchenId()).get());
            restaurant.get().setPayment(request.getPaymentId() == null ? restaurant.get().getPayment() : paymentRepository.findById(request.getPaymentId()).get());

        } catch (Exception e) {
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
