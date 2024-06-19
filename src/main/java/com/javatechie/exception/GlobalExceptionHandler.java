package com.javatechie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javatechie.payloads.ApiResponse;
import com.javatechie.util.TusharException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(TusharException.class)
	public ResponseEntity<ApiResponse> tusharExceptionHandler(TusharException ex) {
		String message =ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
