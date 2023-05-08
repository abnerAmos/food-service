package com.food.service.services.impl;

import com.food.service.dto.request.OrderItemRequest;
import com.food.service.exception.*;
import com.food.service.model.Order;
import com.food.service.model.OrderItem;
import com.food.service.model.Product;
import com.food.service.repository.OrderItemRepository;
import com.food.service.repository.OrderRepository;
import com.food.service.repository.ProductRepository;
import com.food.service.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrderItemImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderItem findById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id).
                orElseThrow(() -> new OrderItemNotFoundException(id));
        return orderItem;
    }

    @Override
    public List<OrderItem> listAll() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        if (orderItems.isEmpty())
            throw new EntityNotFoundException();
        return orderItems;
    }

    @Override
    public OrderItem create(OrderItemRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException(request.getOrderId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(request.getProductId()));

        OrderItem orderItem = new OrderItem();

        try {

            orderItem.setQuantity(request.getQuantity());
            orderItem.setUnitPrice(request.getUnitPrice());
            orderItem.setTotalValue(request.getTotalValue());
            orderItem.setObservation(request.getObservation());
            orderItem.setOrder(order);
            orderItem.setProduct(product);

            orderItemRepository.save(orderItem);

        } catch (Exception e) {
            throw new EntityNotCreateOrUpdateException();
        }
        return orderItem;
    }

    @Override
    public OrderItem update(OrderItemRequest request, Long id) {

        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(id));

        try {

            orderItem.setQuantity(request.getQuantity());
            orderItem.setUnitPrice(request.getUnitPrice());
            orderItem.setTotalValue(request.getTotalValue());
            orderItem.setObservation(request.getObservation());

            orderItemRepository.save(orderItem);

        } catch (Exception e) {
            throw new EntityNotCreateOrUpdateException();
        }
        return orderItem;
    }

    @Override
    public void delete(Long id) {
        try {
            orderItemRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new OrderItemNotFoundException();
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException();
        }
    }
}
