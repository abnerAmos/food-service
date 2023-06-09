package com.food.service.services;

import com.food.service.dto.request.KitchenRequest;
import com.food.service.model.Kitchen;

import java.util.List;

public interface KitchenService {

    Kitchen findById(Long id);

    List<Kitchen> findByName (String name);

    List<Kitchen> listAll();

    Kitchen create(KitchenRequest request);

    Kitchen update(KitchenRequest request, Long id);

    void delete(Long id);
}
