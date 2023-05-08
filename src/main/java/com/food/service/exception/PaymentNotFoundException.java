package com.food.service.exception;

import javax.persistence.EntityNotFoundException;

public class PaymentNotFoundException extends EntityNotFoundException {

    private static final String PAYMENT_NOT_FOUND = "Nenhuma forma de pagamento encontrada :: ";

    public PaymentNotFoundException(String msg) {
        super(msg);
    }

    public PaymentNotFoundException(Long id) {
        this(PAYMENT_NOT_FOUND + "id: " + id);
    }

    public PaymentNotFoundException() {
        this(PAYMENT_NOT_FOUND);
    }

}
