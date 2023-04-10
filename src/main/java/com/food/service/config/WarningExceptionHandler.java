package com.food.service.config;

import com.food.service.exception.KitchenNotFoundException;
import com.food.service.exception.PaymentNotFoundException;
import com.food.service.exception.RestaurantNotFoundException;
import com.food.service.exception.StateNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class WarningExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
