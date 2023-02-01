package com.github.allanccruz.EmployeeCRUD.api.exceptions;

import com.github.allanccruz.EmployeeCRUD.api.dto.response.ErrorResponse;
import com.github.allanccruz.EmployeeCRUD.api.dto.response.FieldErrorResponse;
import com.github.allanccruz.EmployeeCRUD.api.enums.Errors;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception, WebRequest request) {

        ErrorResponse response = new ErrorResponse();

        response.setHttpCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setInternalCode(exception.getErrorCode());
        response.setPath(request.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {

        ErrorResponse response = new ErrorResponse();

        response.setHttpCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(Errors.EC001.getMessage());
        response.setInternalCode(Errors.EC001.getCode());
        response.setErrors(exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> new FieldErrorResponse(e.getDefaultMessage(), e.getField()))
                .collect(Collectors.toList()));
        response.setPath(request.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {

        ErrorResponse response = new ErrorResponse();

        response.setHttpCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(Errors.EC002.getMessage());
        response.setInternalCode(Errors.EC002.getCode());
        response.setPath(request.getDescription(false));
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
