package com.thiagopaes.model;

import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvBindByName;

@Component
public class Unit {

	@CsvBindByName
	private String unit;
	@CsvBindByName
	private String variable;
	@CsvBindByName
	private Double factor;

	public Unit() {

	}

	public Unit(String unit, String variable, Double factor) {
		super();
		this.unit = unit;
		this.variable = variable;
		this.factor = factor;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	@Override
	public String toString() {
		return "Unitt [unit=" + unit + ", variable=" + variable + ", factor=" + factor + "]";
	}

}
