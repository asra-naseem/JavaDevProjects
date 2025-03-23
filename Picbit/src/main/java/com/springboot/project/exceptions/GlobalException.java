package com.springboot.project.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.project.payloads.ApiErrorResponse;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> resourceNotFound(ResourceNotFoundException ex){
	
		String message = ex.getMessage();
		ApiErrorResponse errorResponse = new ApiErrorResponse(message,false);
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String Field = ((FieldError)(error)).getField();
			String errorField = error.getDefaultMessage();
			response.put(Field, errorField);
			
		});
		
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	} 	
	

}
