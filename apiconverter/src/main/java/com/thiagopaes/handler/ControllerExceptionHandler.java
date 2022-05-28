package com.thiagopaes.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.thiagopaes.dto.ErrorResponseDTO;

@ControllerAdvice(annotations = RestController.class)
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	// custom exception to validate if request field exists
	@ExceptionHandler(value = { ArgumentNotValidException.class })
	public ResponseEntity<Object> handleArgumentNotValid(ArgumentNotValidException ex, WebRequest request) {
		ErrorResponseDTO error = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), "There are invalid fields",
				LocalDateTime.now(), ex.getMessage());
		return super.handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	// This exception is thrown when an argument annotated with @Valid failed
	// validation:
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ErrorResponseDTO erroResposta = new ErrorResponseDTO(status.value(), "There are invalid fields",
				LocalDateTime.now(), errors);
		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}

	// This exception is thrown when trying to set bean property with the wrong
	// type.
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = ex.getPropertyName() + " should be of type " + ex.getRequiredType().getName();
		ErrorResponseDTO apiError = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(),
				LocalDateTime.now(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		ErrorResponseDTO apiError = new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(),
				LocalDateTime.now(), error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
