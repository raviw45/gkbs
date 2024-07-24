package com.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.customer.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(
					ApiResponse
					   .builder()
					    .message(message)
					    .status(HttpStatus.NOT_FOUND)
					    .customers(null)
					    .build()
					);
	}
}
