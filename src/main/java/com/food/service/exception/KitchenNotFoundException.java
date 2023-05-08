package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class KitchenNotFoundException extends EntityNotFoundException {

    private static final String KITCHEN_NOT_FOUND = "Nenhuma cozinha encontrada :: ";

    public KitchenNotFoundException(String msg) {
        super(msg);
    }

    public KitchenNotFoundException(Long id) {
        this(KITCHEN_NOT_FOUND + "id: " + id);
    }

    public KitchenNotFoundException() {
        this(KITCHEN_NOT_FOUND);
    }

}
