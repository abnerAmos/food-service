package com.food.service.exception;

public class EntityNotCreateOrUpdate extends RuntimeException {

    public EntityNotCreateOrUpdate(String msg) {
        super(msg);
    }
}
