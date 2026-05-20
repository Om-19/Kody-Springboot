package com.task.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.task.dto.response.ApiErrorResponse;
import com.task.exception.customExc.DuplicateResourceException;
import com.task.exception.customExc.InvalidOfferException;
import com.task.exception.customExc.ProductAlreadyDeletedException;
import com.task.exception.customExc.ResourceNotFound;
import com.task.exception.customExc.ValidationErrorResponse;

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

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ValidationErrorResponse> handleDataValidationException(
                        MethodArgumentNotValidException ex) {
                Map<String, String> errors = new HashMap<>();

                ex.getBindingResult()
                                .getFieldErrors()
                                .forEach(error -> {

                                        errors.put(
                                                        error.getField(),
                                                        error.getDefaultMessage());

                                });

                ValidationErrorResponse response = ValidationErrorResponse.builder()
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .error("Validation Failed")
                                .messages(errors)
                                .build();

                return new ResponseEntity<>(
                                response,
                                HttpStatus.BAD_REQUEST);
        }

        /*
         * If the request body is missing / not found
         */
        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ApiErrorResponse> handleBadRequest(HttpMessageNotReadableException ex) {

                ApiErrorResponse error = ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .error("INVALID REQUEST")
                                .message("Request body is missing or malformed")
                                .build();

                return new ResponseEntity<>(
                                error,
                                HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ApiErrorResponse> handleDatabaseException(
                        DataIntegrityViolationException ex) {

                ApiErrorResponse error = ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .error("DATABASE ERROR")
                                .message("Invalid or duplicate data")
                                .build();

                return new ResponseEntity<>(
                                error,
                                HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ResourceNotFound.class)
        public ResponseEntity<ApiErrorResponse> handleResourceNotFound(ResourceNotFound ex) {

                ApiErrorResponse response = ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .error("NOT FOUND")
                                .message(ex.getMessage())
                                .build();

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }

        @ExceptionHandler(DuplicateResourceException.class)
        public ResponseEntity<ApiErrorResponse> handleDuplicateResource(DuplicateResourceException ex) {

                ApiErrorResponse response = ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .statusCode(HttpStatus.CONFLICT.value())
                                .error("CONFLICT")
                                .message(ex.getMessage())
                                .build();

                return new ResponseEntity<>(response, HttpStatus.CONFLICT);

        }

        /*
         * Product Already Deleted Exception
         */
        @ExceptionHandler(ProductAlreadyDeletedException.class)
        public ResponseEntity<ApiErrorResponse> handleProductAlreadyDeletedException(
                        ProductAlreadyDeletedException ex) {
                ApiErrorResponse resp = ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .statusCode(HttpStatus.GONE.value())
                                .error("GONE")
                                .message(ex.getMessage())
                                .build();

                return new ResponseEntity<>(
                                resp,
                                HttpStatus.GONE);
        }

        /*
         * Offer Invalid Exception
         */
        @ExceptionHandler(InvalidOfferException.class)
        public ResponseEntity<ApiErrorResponse> handleInvalidOffer(
                        InvalidOfferException ex) {

                ApiErrorResponse error = ApiErrorResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .error("INVALID OFFER")
                                .message(ex.getMessage())
                                .build();

                return new ResponseEntity<>(
                                error,
                                HttpStatus.BAD_REQUEST);
        }

}
