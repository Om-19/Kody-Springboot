package com.olp.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.olp.dto.response.GenericResponse;
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

        @ExceptionHandler(UsernameNotFoundException.class)
        public ResponseEntity<ApiErrorResponse> userNotFound(Exception ex) {

                ApiErrorResponse error = ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .error("NOT FOUND")
                                .message("USername Not Found.")
                                .build();

                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

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

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handleUserAlreadyExist(MethodArgumentNotValidException ex) {

                Map<String, String> errors = new HashMap<>();

                ex.getBindingResult()
                                .getFieldErrors()
                                .forEach(error -> {
                                        errors.put(
                                                        error.getField(),
                                                        error.getDefaultMessage());
                                });

                GenericResponse<?> response = GenericResponse.builder()
                                .statusCode(400)
                                .message("Validation Failed")
                                .data(errors)
                                .build();

                return ResponseEntity
                                .badRequest()
                                .body(response);

        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {

                Map<String, Object> error = new HashMap<>();

                error.put("timestamp", LocalDateTime.now());
                error.put("message", ex.getMessage());
                error.put("status", 400);

                return new ResponseEntity<>(
                                error,
                                HttpStatus.BAD_REQUEST);
        }

}
