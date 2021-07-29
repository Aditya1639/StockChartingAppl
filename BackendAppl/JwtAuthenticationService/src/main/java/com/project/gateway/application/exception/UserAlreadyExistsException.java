package com.project.gateway.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAlreadyExistsException extends Throwable
{

	private static final long serialVersionUID = 1L;
	private String message;

	public UserAlreadyExistsException(String message) {
		this.message = message;
	}
}
