package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class AddressNotFoundException extends EntityNotFoundException {

    private static final String ADDRESS_NOT_FOUND = "Endereço inválido ou não encontrado :: ";

    public AddressNotFoundException(String msg) {
        super(msg);
    }

    public AddressNotFoundException(Long id) {
        this(ADDRESS_NOT_FOUND + "id: " + id);
    }

    public AddressNotFoundException() {
        this(ADDRESS_NOT_FOUND);
    }

}
