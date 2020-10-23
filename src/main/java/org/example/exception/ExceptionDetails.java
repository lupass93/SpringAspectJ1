package org.example.exception;

import java.util.Date;

public class ExceptionDetails {
	
	private Date timestamp;
	
	private String message;
	
	private String details;
	
	public ExceptionDetails(Date timestamp, String message, String details) {
		
		super();
		
		this.timestamp = timestamp;
		
		this.message = message;
		
		this.details = details;
		
	}
	
	public String getDate() {
		
		return this.timestamp.toString();
	}
	
	public String getMessage() {
		
		return this.message;
	
	}
	
	public String getDetails() {
		
		return this.details;
		
	}

}
