Things to Keep In mind

1. Add   @ResponseStatus(HttpStatus.CREATED)   on APi's where req
2. use normalisation for product codes 
3. String normalizedCode =
        requestDTO.getProductCode()
                .trim()
                .toUpperCase();

4. use bigDecimal for price rather than Double

5. Use @Size where necessary

6. Add
    @ExceptionHandler(Exception.class)  for all general exceptions
    MethodArgumentNotValidException
    HttpMessageNotReadableException

7.  Built-in methods only exist for:
    findById()
    findAll()
    save()
    delete()
    existsById()

8. Use mapToResponse Like methods for conversions of entities to dto in service class

9. GetAllProduct will return entity list, so convert it to dto using stream
eg. 
    return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

=====================================================================================
1. What if validation annotations fail?

@NotBlank(message = "Product name is required")
What happens?

@PostMapping
public ProductResponseDTO createProduct(
    @Valid @RequestBody ProductRequestDTO dto
)
    @Valid triggers validation BEFORE method executes.

If validation fails:
Spring throws:  MethodArgumentNotValidException
Controller method NEVER executes.

Then @ControllerAdvice Handles it i.e. GlobalExceptionHandler

Then response becomes:
{
   "productName": "Product name is required"
}

Validation annotations DO NOT directly return response.
They:
throw validation exception
exception handler catches it
handler creates response

==========================================================================

2. Built-in methods only exist for:
findById()
findAll()
save()
delete()
existsById()

But:
findByProductCode()
is custom because:
productCode is YOUR field
JPA doesn't know your business fields automatically

=================================================================================

Business validations should be handled at service layer for clean API responses,
while database constraints should still exist as a final integrity safeguard.

=================================================================================

use normalisation for product codes 
String normalizedCode =
        requestDTO.getProductCode()
                .trim()
                .toUpperCase();

use bigDecimal for price rather than Double

Use @Size where necessary
=================================================================================
Create Separate Exception class for Entity Data Attributes Validations
as  ValidationErrorResponse

And add the handler in GlobalExceptionHandler

@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleDataValidationException(MethodArgumentNotValidException ex) {
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

also we can add
server.error.include-stacktrace=never
in application.properties if we are already handling errors manually


    /*
    * If the request body is missing / not found
    */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse>
    handleBadRequest(HttpMessageNotReadableException ex) {

        ApiErrorResponse error =
                ApiErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("INVALID REQUEST")
                        .message("Request body is missing or malformed")
                        .build();

        return new ResponseEntity<>(
                error,
                HttpStatus.BAD_REQUEST
        );
    }

// DATA INTEGRITY 
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

IMPORTANT
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
=================================================================================