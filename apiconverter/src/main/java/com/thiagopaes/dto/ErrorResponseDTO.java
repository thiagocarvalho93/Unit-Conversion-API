package com.thiagopaes.dto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ErrorResponseDTO {
	private Integer status;
	private String title;
	private LocalDateTime timestamp;
	private List<String> errors;

	public ErrorResponseDTO() {

	}

	public ErrorResponseDTO(Integer status, String title, LocalDateTime timestamp, String error) {
		super();
		this.status = status;
		this.title = title;
		this.timestamp = timestamp;
		this.errors = Arrays.asList(error);
	}

	public ErrorResponseDTO(Integer status, String title, LocalDateTime timestamp, List<String> errors) {
		super();
		this.status = status;
		this.title = title;
		this.timestamp = timestamp;
		this.errors = errors;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}