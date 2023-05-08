package com.food.service.exception;

public class EntityNotCreateOrUpdateException extends RuntimeException {

    private static final String ENTITY_NOT_CREATE_OR_UPDATE = "Não foi possivel criar ou atualizar :: ";

    public EntityNotCreateOrUpdateException(String msg) {
        super(msg);
    }

    public EntityNotCreateOrUpdateException(){
        this(ENTITY_NOT_CREATE_OR_UPDATE);
    }
}
