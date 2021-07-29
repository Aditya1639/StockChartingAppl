package com.project.gateway.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse 
{
	private String errorMessage;
	private int statusCode;
	private Long timeHappened;
	public ErrorResponse(String errorMessage, int statusCode, Long timeHappened) {
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
		this.timeHappened = timeHappened;
	}
}
