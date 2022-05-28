package com.thiagopaes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequestDTO {

	@JsonProperty("from_unit")
	@NotBlank(message = "from_unit is mandatory.")
	@Size(max = 4, message = "invalid size for from_unit.")
	private String fromUnit;

	@JsonProperty("to_unit")
	@NotBlank(message = "to_unit is mandatory.")
	@Size(max = 4, message = "invalid size for to_unit.")
	private String toUnit;

	@NotBlank(message = "to_unit is mandatory.")
	private String value;

	public PostRequestDTO(
			@NotBlank(message = "from_unit is mandatory.") @Size(max = 4, message = "invalid size for from_unit.") String fromUnit,
			@NotBlank(message = "to_unit is mandatory.") @Size(max = 4, message = "invalid size for to_unit.") String toUnit,
			@NotBlank(message = "to_unit is mandatory.") String value) {
		super();
		this.fromUnit = fromUnit;
		this.toUnit = toUnit;
		this.value = value;
	}

	public String getFromUnit() {
		return fromUnit;
	}

	public void setFromUnit(String fromUnit) {
		this.fromUnit = fromUnit;
	}

	public String getToUnit() {
		return toUnit;
	}

	public void setToUnit(String toUnit) {
		this.toUnit = toUnit;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PostRequestDTO [from_unit=" + fromUnit + ", to_unit=" + toUnit + ", value=" + value + "]";
	}

}
