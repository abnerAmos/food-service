package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class RestaurantNotFound extends EntityNotFoundException {

    private static final String RESTAURANT_NOT_FOUND = "Nenhum restaurante encontrado :: ";

    public RestaurantNotFound(String msg) {
        super(msg);
    }

    public RestaurantNotFound(Long id) {
        this(RESTAURANT_NOT_FOUND + "id: " + id);
    }

    public RestaurantNotFound() {
        this(RESTAURANT_NOT_FOUND);
    }

}
