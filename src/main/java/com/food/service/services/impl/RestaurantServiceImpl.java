package com.food.service.services.impl;

import com.food.service.dto.request.RestaurantRequest;
import com.food.service.dto.response.AddressResponse;
import com.food.service.dto.response.RestaurantResponse;
import com.food.service.exception.*;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    @Override
    public List<RestaurantResponse> listAll(String name) {

        List<Restaurant> restaurant = restaurantRepository.listAllByName(name);
        if (restaurant.isEmpty()) {
            throw new RestaurantNotFoundException();
        }

        List<RestaurantResponse> response = new ArrayList<>();
        for (Restaurant value : restaurant) {
            RestaurantResponse r = new RestaurantResponse();
            r.setName(value.getName());
            r.setDeliveryFee(value.getDeliveryFee());
            r.setActive(value.getActive());
            r.setOpenUp(value.getOpenUp());
            r.setDateRegister(value.getDateRegister());
            r.setDateUpdate(value.getDateUpdate());
            response.add(r);
        }
        return response;
    }

    @Override
    public Restaurant create(RestaurantRequest request) {

        Restaurant restaurant = new Restaurant();
        Address address = new Address();

        try {
            var kitchen = kitchenRepository.findById(request.getKitchenId());
            if (kitchen.isEmpty()) {
                throw new KitchenNotFoundException(request.getKitchenId());
            }

            List<TypePayment> payment = paymentRepository.findAllById(request.getTypePaymentId());
            if (payment.isEmpty()) {
                throw new PaymentNotFoundException();
            }

            Optional<AddressResponse> response = viaCep.getAddress(request);
            if (response.isEmpty()) {
                throw new AddressNotFoundException();
            }

            restaurant = modelMapper.map(request, Restaurant.class);

            restaurant.setActive(true);
            restaurant.setOpenUp(false);
            restaurant.setDateRegister(LocalDateTime.now());
            restaurant.setKitchen(kitchen.get());
            restaurant.setTypePayments(payment);

            address.setPostalCode(response.get().getCep());
            address.setAddress(response.get().getLogradouro());
            address.setComplementAddress(request.getComplementAddress());
            address.setCity(response.get().getLocalidade());
            address.setState(response.get().getUf());
            address.setDistrict(response.get().getBairro());
            address.setPlaceNumber(request.getPlaceNumber());

            restaurant.setAddress(address);
            restaurantRepository.save(restaurant);

        } catch (Exception f) {
            throw new EntityNotCreateOrUpdateException();
        }
        return restaurant;
    }

    @Override
    public Restaurant update(RestaurantRequest request, Long id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) {
            throw new RestaurantNotFoundException();
        }

        Optional<AddressResponse> response = viaCep.getAddress(request);
        if (response.isEmpty()) {
            throw new AddressNotFoundException();
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

            address.setPlaceNumber(request.getPlaceNumber() == null || request.getPlaceNumber().equals("") ? restaurant.get().getAddress().getPlaceNumber() : request.getPlaceNumber());
            address.setComplementAddress(request.getComplementAddress() == null || request.getComplementAddress().equals("") ? restaurant.get().getAddress().getComplementAddress() : request.getComplementAddress());
            address.setPostalCode(response.get().getCep());
            address.setAddress(response.get().getLogradouro());
            address.setCity(response.get().getLocalidade());
            address.setState(response.get().getUf());
            address.setDistrict(response.get().getBairro());

            restaurant.get().setAddress(address);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RestaurantNotFoundException();
        }
        return restaurantRepository.save(restaurant.get());
    }

    @Override
    public void delete(Long id) {
        try {
            restaurantRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RestaurantNotFoundException();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException();
        }
    }
}
