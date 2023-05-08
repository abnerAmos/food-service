package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {

    private static final String PRODUCT_NOT_FOUND = "Nenhum produto encontrado :: ";

    public ProductNotFoundException(String msg) {
        super(msg);
    }

    public ProductNotFoundException(Long id) {
        this(PRODUCT_NOT_FOUND + "id: " + id);
    }

    public ProductNotFoundException() {
        this(PRODUCT_NOT_FOUND);
    }

}
