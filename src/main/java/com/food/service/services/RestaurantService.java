package com.food.service.services;

import com.food.service.dto.request.RestaurantRequest;
import com.food.service.dto.response.RestaurantResponse;
import com.food.service.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant findById(Long id);

    List<Restaurant> findByName(String name);

    List<RestaurantResponse> listAll();

    Restaurant create(RestaurantRequest request);

    Restaurant update(RestaurantRequest request, Long id);

    void delete(Long id);
}
