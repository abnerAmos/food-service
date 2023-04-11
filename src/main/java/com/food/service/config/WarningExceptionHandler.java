package com.food.service.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

import javax.persistence.EntityNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class WarningExceptionHandler {

    /* Modo ideal para lançar o erro 400 é quando há algum erro por lado do usuário
     Ex.: Erro de digitação, campo não preenchido, etc.*/
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> resourceAccessException(ResourceAccessException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    /* Modo ideal para lançar o erro 404 é quando há os dados preenchidos pelo usuário esta OK,
     porém não foi encontrado o item da sua requisição. */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

//    @ExceptionHandler(RestaurantNotFoundException.class)
//    public ResponseEntity<String> restaurantNotFoundException(RestaurantNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
//
//    @ExceptionHandler(PaymentNotFoundException.class)
//    public ResponseEntity<String> paymentNotFoundException(PaymentNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
//
//    @ExceptionHandler(KitchenNotFoundException.class)
//    public ResponseEntity<String> kitchenNotFoundException(KitchenNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
//
//    @ExceptionHandler(StateNotFoundException.class)
//    public ResponseEntity<String> stateNotFoundException(StateNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
}
