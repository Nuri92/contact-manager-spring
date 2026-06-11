package de.nuri.contactmanagerspring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidationException(
			MethodArgumentNotValidException exception
	) {
		List<String> errors = exception.getBindingResult()
		                               .getFieldErrors()
		                               .stream()
		                               .map(error -> error.getDefaultMessage())
		                               .toList();
		
		ApiErrorResponse response = new ApiErrorResponse(
				HttpStatus.BAD_REQUEST.value(),
				"Validierung fehlgeschlagen",
				errors
		);
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(response);
	}
}