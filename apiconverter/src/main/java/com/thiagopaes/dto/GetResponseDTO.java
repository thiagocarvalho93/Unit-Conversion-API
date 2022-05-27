package com.thiagopaes.dto;

import java.util.List;

public class GetResponseDTO {

	private String variable;
	private List<String> unitList;

	public GetResponseDTO(String variable, List<String> unitList) {
		super();
		this.unitList = unitList;
		this.variable = variable;
	}

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
