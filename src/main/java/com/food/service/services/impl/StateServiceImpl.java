package com.food.service.services.impl;

import com.food.service.model.State;
import com.food.service.repository.KitchenRepository;
import com.food.service.repository.PaymentRepository;
import com.food.service.repository.StateRepository;
import com.food.service.services.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    public State findById(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NENHUM RESTAURANTE ENCONTRADO!"));
    }

    @Override
    public List<State> listAll() {

        List<State> state = stateRepository.findAll();
            if (state.isEmpty()) {
                throw new EntityNotFoundException("NENHUM ESTADO ENCONTRADO!");
        }

        return state;
    }

//    @Override
//    public Restaurant create(RestaurantRequest request) {
//
//        Restaurant restaurant = new Restaurant();
//
//        var kitchen = kitchenRepository.findById(request.getKitchenId());
//        if (kitchen.isEmpty()) {
//            throw new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!");
//        }
//
//        Optional<TypePayment> payment = paymentRepository.findById(request.getPaymentId());
//        if (payment.isEmpty()) {
//            throw new EntityNotFoundException("NENHUMA FORMA DE PAGAMENTO ENCONTRADA!");
//        }
//
//        restaurant.setName(request.getName());
//        restaurant.setDeliveryFee(request.getDeliveryFee());
//        restaurant.setActive(true);
//        restaurant.setOpen(false);
//        restaurant.setDateRegistrer(LocalDateTime.now());
//        restaurant.setKitchen(kitchen.get());
//        restaurant.setPayment(payment.get());
//
//        return restaurantRepository.save(restaurant);
//    }
//
//    @Override
//    public Restaurant update(RestaurantRequest request, Long id) {
//
//        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
//        if (restaurant.isEmpty()) {
//            throw new EntityNotFoundException("NENHUM RESTAURANTE ENCONTRADO!");
//        }
//
//        var kitchen = kitchenRepository.findById(request.getKitchenId());
//        if (kitchen.isEmpty()) {
//            throw new EntityNotFoundException("NENHUMA COZINHA ENCONTRADA!");
//        }
//
//        Optional<TypePayment> payment = paymentRepository.findById(request.getPaymentId());
//        if (payment.isEmpty()) {
//            throw new EntityNotFoundException("NENHUMA FORMA DE PAGAMENTO ENCONTRADA!");
//        }
//
//        restaurant.get().setName(request.getName());
//        restaurant.get().setDeliveryFee(request.getDeliveryFee());
//        restaurant.get().setActive(request.getActive());
//        restaurant.get().setOpen(request.getOpen());
//        restaurant.get().setDateUpdate(LocalDateTime.now());
//        restaurant.get().setKitchen(kitchen.get());
//        restaurant.get().setPayment(payment.get());
//
//        return restaurantRepository.save(restaurant.get());
//    }
//
//    @Override
//    public void delete(Long id) {
//        try {
//            restaurantRepository.deleteById(id);
//        } catch (Exception e) {
//            throw new EntityNotFoundException("NÃO FOI POSSIVEL EXCLUIR O RESTAURANTE!");
//        }
//    }
}
