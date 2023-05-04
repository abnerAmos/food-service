package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class PaymentNotFound extends EntityNotFoundException {

    private static final String PAYMENT_NOT_FOUND = "Nenhuma forma de pagamento encontrada :: ";

    public PaymentNotFound(String msg) {
        super(msg);
    }

    public PaymentNotFound(Long id) {
        this(PAYMENT_NOT_FOUND + "id: " + id);
    }

    public PaymentNotFound() {
        this(PAYMENT_NOT_FOUND);
    }

}
