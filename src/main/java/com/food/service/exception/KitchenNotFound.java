package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class KitchenNotFound extends EntityNotFoundException {

    private static final String KITCHEN_NOT_FOUND = "Nenhuma cozinha encontrada :: ";
    private static final String KITCHEN_DELETE_ERROR = "Endereço inválido ou não encontrado :: ";

    public KitchenNotFound(String msg) {
        super(msg);
    }

    public KitchenNotFound(Long id) {
        this(KITCHEN_NOT_FOUND + "id: " + id);
    }

    public KitchenNotFound() {
        this(KITCHEN_NOT_FOUND);
    }

}
