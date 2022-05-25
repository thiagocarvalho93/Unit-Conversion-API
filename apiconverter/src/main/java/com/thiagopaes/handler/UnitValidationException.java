package com.thiagopaes.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UnitValidationException extends RuntimeException {
	private static final long serialVersionUID = -7039736994934138780L;

	public UnitValidationException(String message) {
		super(message);
	}
}
