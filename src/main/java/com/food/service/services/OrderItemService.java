package com.food.service.services;

import com.food.service.dto.request.OrderItemRequest;
import com.food.service.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem findById(Long id);

    List<OrderItem> listAll();

    OrderItem create(OrderItemRequest request);

    OrderItem update(OrderItemRequest request, Long id);

    void delete(Long id);
}
