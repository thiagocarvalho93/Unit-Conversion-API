package com.thiagopaes.handler;

import java.time.LocalDateTime;

public class ErrorResponse {
	private Integer status;
	private String title;
	private LocalDateTime dateTime;
	// private List<String> errors;

	public ErrorResponse() {

	}

	public ErrorResponse(Integer status, String title, LocalDateTime dateTime) {
		super();
		this.status = status;
		this.title = title;
		this.dateTime = dateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return title;
	}

	public void setTitulo(String titulo) {
		this.title = titulo;
	}

	public LocalDateTime getDataHora() {
		return dateTime;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dateTime = dataHora;
	}

}