package com.thiagopaes.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetResponseDTO {

	private String variable;
	private List<String> unitList;

	public GetResponseDTO(String variable, List<String> unitList) {
		super();
		this.unitList = unitList;
		this.variable = variable;
	}

	@JsonProperty("unit_list")
	public List<String> getUnit() {
		return unitList;
	}

	public void setUnit(List<String> unitList) {
		this.unitList = unitList;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

}
