package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class AddressNotFound extends EntityNotFoundException {

    private static final String ADDRESS_NOT_FOUND = "Endereço inválido ou não encontrado :: ";

    public AddressNotFound(String msg) {
        super(msg);
    }

    public AddressNotFound(Long id) {
        this(ADDRESS_NOT_FOUND + "id: " + id);
    }

    public AddressNotFound() {
        this(ADDRESS_NOT_FOUND);
    }

}
