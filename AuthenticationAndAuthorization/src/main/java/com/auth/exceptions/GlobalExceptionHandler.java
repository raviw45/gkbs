package com.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth.dto.AuthResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<AuthResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
		String message=ex.getMessage();
		AuthResponse authResponse=AuthResponse.builder()
		             .accessToken(null)
		             .refreshToken(null)
		             .message(message)
		             .status(HttpStatus.NOT_FOUND)
		             .success(false)
		            .build();
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.NOT_FOUND);
	}
	
}
