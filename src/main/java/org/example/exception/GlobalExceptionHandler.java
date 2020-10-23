package org.example.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Component
@ControllerAdvice
public final class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionDetails> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		
		
		ExceptionDetails exDetails = new ExceptionDetails(new Date(), ex.getMessage(), request.getContextPath());
		
		return new ResponseEntity<ExceptionDetails>(exDetails, HttpStatus.NOT_FOUND);
		
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDetails> globalExceptionHandler(Exception ex, WebRequest request) {
		
		ExceptionDetails exDetails = new ExceptionDetails(new Date(), ex.getMessage(), request.getContextPath());
		
		return new ResponseEntity<ExceptionDetails>(exDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
