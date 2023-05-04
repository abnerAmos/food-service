package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class KitchenDeleteError extends EntityNotFoundException {

    private static final String KITCHEN_DELETE_ERROR = "Endereço inválido ou não encontrado :: ";

    public KitchenDeleteError(String msg) {
        super(msg);
    }

    public KitchenDeleteError() {
        this(KITCHEN_DELETE_ERROR);
    }

}
