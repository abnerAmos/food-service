package com.food.service.config;

import com.food.service.exception.DatabaseException;
import com.food.service.exception.EntityNotCreateOrUpdateException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class WarningExceptionHandler extends ResponseEntityExceptionHandler {

    /* Modo ideal para lançar o erro 400 é quando há algum erro por lado do usuário
     Ex.: Erro de digitação, campo não preenchido, etc.*/
    @ExceptionHandler(EntityNotCreateOrUpdateException.class)
    public ResponseEntity<ErrorResponse> entityNotCreateOrUpdate(EntityNotCreateOrUpdateException e, HttpServletRequest request) {
        String error = "NÃO CRIADO OU ATUALIZADO!";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), e.getMessage(), request.getRequestURI(), null);
        return ResponseEntity.status(status).body(err);
    }

    /* Modo ideal para lançar o erro 404 é quando há os dados preenchidos pelo usuário esta OK,
     porém não foi encontrado o item da sua requisição. */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        String error = "NÃO ENCONTRADO!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI(), null);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorResponse> databaseException(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI(), null);
        return ResponseEntity.status(status).body(err);
    }

    @Override   // Possui a anotação @ExceptionHandler que é responsável por capturar a exception quando lançada.
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        List<ErrorResponse.ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(status, errors, ex);
        return new ResponseEntity<>(errorResponse, status);
    }

    private List<ErrorResponse.ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse.ErrorObject(error.getDefaultMessage(), error.getField(),
                        error.getRejectedValue()))
                .collect(Collectors.toList());
    }

    private ErrorResponse getErrorResponse(HttpStatus status, List<ErrorResponse.ErrorObject> errors, MethodArgumentNotValidException ex) {
        return new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
                "Requisição possuí campos inválidos", ex.getBindingResult().getObjectName(), errors);
    }

}
