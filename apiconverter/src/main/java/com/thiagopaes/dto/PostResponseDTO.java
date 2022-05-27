package com.thiagopaes.dto;

public class PostResponseDTO {

	private String unit;
	private Double result;

	public PostResponseDTO(String unit, Double result) {
		super();
		this.unit = unit;
		this.result = result;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

}
