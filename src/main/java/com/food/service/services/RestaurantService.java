package com.food.service.services;

import com.food.service.dto.request.RestaurantAddRequest;
import com.food.service.dto.request.RestaurantUpdateRequest;
import com.food.service.dto.response.RestaurantResponse;
import com.food.service.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant findById(Long id);

    List<RestaurantResponse> listAll(String name);

    Restaurant create(RestaurantAddRequest request);

    Restaurant update(RestaurantUpdateRequest request, Long id);

    void delete(Long id);
}
