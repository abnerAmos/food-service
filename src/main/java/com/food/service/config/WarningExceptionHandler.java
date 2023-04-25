package com.food.service.config;

import com.food.service.exception.DatabaseException;
import com.food.service.exception.EntityNotCreateOrUpdate;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class WarningExceptionHandler {

    /* Modo ideal para lançar o erro 400 é quando há algum erro por lado do usuário
     Ex.: Erro de digitação, campo não preenchido, etc.*/
    @ExceptionHandler(EntityNotCreateOrUpdate.class)
    public ResponseEntity<StatusError> entityNotCreateOrUpdate(EntityNotCreateOrUpdate e, HttpServletRequest request) {
        String error = "NÃO CRIADO OU ATUALIZADO!";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StatusError err = new StatusError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    /* Modo ideal para lançar o erro 404 é quando há os dados preenchidos pelo usuário esta OK,
     porém não foi encontrado o item da sua requisição. */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StatusError> entityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        String error = "NÃO ENCONTRADO!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StatusError err = new StatusError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StatusError> databaseException(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StatusError err = new StatusError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
