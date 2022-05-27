package com.thiagopaes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConversionRequestDTO {

	@NotBlank(message = "fromUnit is mandatory.")
	@Size(max = 4, message = "invalid size for fromUnit.")
	private String fromUnit;

	@NotBlank(message = "toUnit is mandatory.")
	@Size(max = 4, message = "invalid size for toUnit.")
	private String toUnit;

	@NotNull(message = "value is mandatory.")
	private Double value;

	public ConversionRequestDTO(
			@NotBlank(message = "fromUnit is mandatory.") @Size(max = 4, message = "invalid size for fromUnit.") String fromUnit,
			@NotBlank(message = "toUnit is mandatory.") @Size(max = 4, message = "invalid size for toUnit.") String toUnit,
			@NotNull(message = "value is mandatory.") Double value) {
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

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "RequestModel [fromUnit=" + fromUnit + ", toUnit=" + toUnit + ", value=" + value + "]";
	}

}
