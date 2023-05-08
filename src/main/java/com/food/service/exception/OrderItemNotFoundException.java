package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class OrderItemNotFoundException extends EntityNotFoundException {

    private static final String ITEM_NOT_FOUND = "Nenhum item do pedido encontrado :: ";

    public OrderItemNotFoundException(String msg) {
        super(msg);
    }

    public OrderItemNotFoundException(Long id) {
        this(ITEM_NOT_FOUND + "id: " + id);
    }

    public OrderItemNotFoundException() {
        this(ITEM_NOT_FOUND);
    }

}
