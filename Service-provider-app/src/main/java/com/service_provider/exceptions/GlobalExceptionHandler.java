package com.service_provider.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.service_provider.dto.ResourceNotFoundResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex){
		String message=ex.getMessage();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResourceNotFoundResponse.builder()
				.status(HttpStatus.NOT_FOUND)
				.message(message)
				.success(false)
				.build());
	}
}
