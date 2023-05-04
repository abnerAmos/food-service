package com.food.service.exception;

public class DatabaseException extends RuntimeException {

    private static final String COUPLED_DATA_ERROR = "Entidade possui itens vinculados :: ";

    public DatabaseException(String msg) {
        super(msg);
    }

    public DatabaseException() {
        this(COUPLED_DATA_ERROR);
    }
}
