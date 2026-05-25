package com.olp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.olp.exception.customExc.EntityAlreadyExistException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {

        ApiErrorResponse error = ApiErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("INTERNAL SERVER ERROR")
                .message("Something went wrong")
                .build();

        return new ResponseEntity<>(
                error,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ApiErrorResponse> handleUserAlreadyExist(EntityAlreadyExistException ex) {

        ApiErrorResponse error = ApiErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.ALREADY_REPORTED.value())
                .error("MAIL ALREADY EXIST")
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(
                error,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
