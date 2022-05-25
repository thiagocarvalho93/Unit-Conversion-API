package com.thiagopaes.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VariableValidationException extends RuntimeException {
	private static final long serialVersionUID = 8717194079897691414L;

	public VariableValidationException(String message) {
		super(message);
	}
}
