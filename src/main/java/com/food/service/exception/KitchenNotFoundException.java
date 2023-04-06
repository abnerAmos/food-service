package com.food.service.exception;

public class KitchenNotFoundException extends RuntimeException {

    public KitchenNotFoundException(String message) {
        super(message);
    }
}
