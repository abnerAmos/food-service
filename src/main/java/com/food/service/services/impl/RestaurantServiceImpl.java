package com.food.service.services.impl;

import com.food.service.dto.request.RestaurantRequest;
import com.food.service.model.Payment;
import com.food.service.model.Restaurant;
import com.food.service.repository.KitchenRepository;
import com.food.service.repository.PaymentRepository;
import com.food.service.repository.RestaurantRepository;
import com.food.service.services.RestaurantService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUM RESTAURANTE ENCONTRADO!"));
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

            restaurant.setName(request.getName());
            restaurant.setDeliveryFee(request.getDeliveryFee());
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

        try {
            if (restaurant.isEmpty()) {
                throw new EntityNotFoundException("NENHUM RESTAURANTE ENCONTRADO!");
            }

            var kitchen = kitchenRepository.findById(request.getKitchenId());
            if (kitchen.isEmpty()) {
                throw new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!");
            }

            Optional<Payment> payment = paymentRepository.findById(request.getPaymentId());
            if (payment.isEmpty()) {
                throw new EntityNotFoundException("NENHUMA FORMA DE PAGAMENTO ENCONTRADA!");
            }

            restaurant.get().setName(request.getName());
            restaurant.get().setDeliveryFee(request.getDeliveryFee());
            restaurant.get().setActive(request.getActive());
            restaurant.get().setOpen(request.getOpen());
            restaurant.get().setDateUpdate(LocalDateTime.now());
            restaurant.get().setKitchen(kitchen.get());
            restaurant.get().setPayment(payment.get());
        } catch (Exception e) {
            throw new ResourceAccessException("NÃO FOI POSSIVEL ATUALIZAR O RESTAURANTE!");
        }
        return restaurantRepository.save(restaurant.get());
    }

    @Override
    public void delete(Long id) {
        try {
            restaurantRepository.deleteById(id);
        } catch (Exception e) {
            throw new EntityNotFoundException("NÃO FOI POSSIVEL EXCLUIR O RESTAURANTE!");
        }
    }
}
