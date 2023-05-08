package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class OrderNotFoundException extends EntityNotFoundException {

    private static final String ORDER_NOT_FOUND = "Nenhum pedido encontrado :: ";

    public OrderNotFoundException(String msg) {
        super(msg);
    }

    public OrderNotFoundException(Long id) {
        this(ORDER_NOT_FOUND + "id: " + id);
    }

    public OrderNotFoundException() {
        this(ORDER_NOT_FOUND);
    }

}
