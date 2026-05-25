package com.onlinelearning.exception;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

	private final MessageSource messageSource;

	// Not Found
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex, Locale locale) {

		String message = messageSource.getMessage(ex.getMessage(), null, ex.getMessage(), locale);

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Map.of("status", 404, "error", "Not Found", "messages", Map.of("error", message)));
	}

	// VALIDATION ERROR
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {

		Map<String, String> fieldErrors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

		return ResponseEntity.badRequest().body(Map.of("status", 400, "error", "Bad Request", "messages", fieldErrors));
	}

	// Message Not Readable Format
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, Object>> handleJsonParseError(HttpMessageNotReadableException ex, Locale locale) {

		Map<String, String> fieldErrors = new HashMap<>();

		Throwable cause = ex.getCause();

		if (cause instanceof InvalidFormatException ife) {

			String fieldName = (ife.getPath() != null && !ife.getPath().isEmpty())
					? ife.getPath().get(0).getPropertyName()
					: "unknown";

			if (ife.getTargetType().equals(LocalDate.class)) {

				fieldErrors.put(fieldName, messageSource.getMessage("error.invalid.date.format", new Object[] {},
						"Invalid date format. Expected format: yyyy-MM-dd", locale));

			} else if (ife.getTargetType().isEnum()) {

				fieldErrors.put(fieldName,
						messageSource.getMessage("error.invalid.enum",
								new Object[] { Arrays.toString(ife.getTargetType().getEnumConstants()) },
								"Invalid value. Allowed values: {0}", locale));

			} else {
				fieldErrors.put(fieldName, messageSource.getMessage("error.invalid.field", new Object[] { fieldName },
						"Invalid value for field '" + fieldName + "'", locale));
			}

		} else {
			fieldErrors.put("error", messageSource.getMessage("error.malformed.json", new Object[] {},
					"Malformed JSON request", locale));
		}

		return ResponseEntity.badRequest()
				.body(Map.of("status", 400, "error",
						messageSource.getMessage("error.bad.request", new Object[] {}, "Bad Request", locale),
						"messages", fieldErrors));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {

		Map<String, String> fieldErrors = new HashMap<>();

		String fieldName = ex.getName();

		String message = String.format("Invalid value '%s'. Expected type is %s.", ex.getValue(),
				ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "valid type");

		fieldErrors.put(fieldName, message);

		Map<String, Object> response = new HashMap<>();
		response.put("status", 400);
		response.put("error", "Bad Request");
		response.put("messages", fieldErrors);

		return ResponseEntity.badRequest().body(response);
	}

	// 404 Not Found Error
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<?> handleNotFoundUrl(NoHandlerFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", 404, "error", "Not Found", "messages",
				Map.of("error", "API endpoint not found: " + ex.getRequestURL())));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

		Locale locale = LocaleContextHolder.getLocale();

		String userMessage = messageSource.getMessage("error.generic", // key from messages.properties
				null, "Something went wrong", // fallback default
				locale);

		Map<String, String> error = new HashMap<>();
		error.put("error", userMessage);

		ex.printStackTrace();

		Map<String, Object> response = new HashMap<>();
		response.put("status", 500);
		response.put("error", "Internal Server Error");
		response.put("messages", error);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}