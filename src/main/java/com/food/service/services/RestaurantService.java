package com.food.service.services;

import com.food.service.dto.request.RestaurantRequest;
import com.food.service.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant create(RestaurantRequest request);

    Restaurant findById(Long id);

    List<Restaurant> findByName(String name);

    List<Restaurant> listAll();

    Restaurant update(RestaurantRequest request, Long id);

    void delete(Long id);
}
