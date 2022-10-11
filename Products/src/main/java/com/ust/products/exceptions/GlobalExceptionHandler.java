package com.ust.products.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorInfo> productNotFoundException(ProductException ex){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(ex.getErrorMessage());
		errorInfo.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(ProductAlreadyDeletedException.class)
	public ResponseEntity<ErrorInfo> productAreadyExistsException(ProductAlreadyDeletedException ex){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(ex.getMessage());
		errorInfo.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo,HttpStatus.CONFLICT);
		}
	
	
	@ExceptionHandler(ProductNotExistsException.class)
	public ResponseEntity<ErrorInfo> productNotExistsException(ProductNotExistsException ex){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(ex.getMessage());
		errorInfo.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo,HttpStatus.NOT_FOUND);
		
}
	
	@ExceptionHandler(ProductExistsException.class)
	public ResponseEntity<ErrorInfo> productExistsException(ProductExistsException ex){
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(ex.getMessage());
		errorInfo.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo,HttpStatus.CONFLICT);
	}
}	
