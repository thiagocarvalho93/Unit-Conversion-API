package com.thiagopaes.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RequestModel {

	@NotBlank
	@Size(max = 4)
	private String from;

	@NotBlank
	@Size(max = 4)
	private String to;

	private Double value;

	public RequestModel(String from, String to, Double value) {
		super();
		this.from = from;
		this.to = to;
		this.value = value;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
