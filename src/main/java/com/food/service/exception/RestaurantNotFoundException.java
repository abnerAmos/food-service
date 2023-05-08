package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class RestaurantNotFoundException extends EntityNotFoundException {

    private static final String RESTAURANT_NOT_FOUND = "Nenhum restaurante encontrado :: ";

    public RestaurantNotFoundException(String msg) {
        super(msg);
    }

    public RestaurantNotFoundException(Long id) {
        this(RESTAURANT_NOT_FOUND + "id: " + id);
    }

    public RestaurantNotFoundException() {
        this(RESTAURANT_NOT_FOUND);
    }

}
