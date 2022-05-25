package com.thiagopaes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagopaes.dto.ConverterDTO;
import com.thiagopaes.handler.UnitValidationException;
import com.thiagopaes.handler.VariableValidationException;
import com.thiagopaes.service.ConverterService;

@RestController

@RequestMapping("/unitconversionapi/v2")
public class ConverterController {

	@Autowired
	private ConverterService converterService;

	@GetMapping
	public List<String> listVariables() {
		return ConverterService.getVariableList();
	}

	@GetMapping("/{variable}")
	public ResponseEntity<List<String>> listUnits(@PathVariable String variable) {
		if (!converterService.listUnits(variable).isEmpty())
			return ResponseEntity.ok(converterService.listUnits(variable));
		throw new VariableValidationException("variable not found.");
	}

	// TODO: get for ("/{variable}/{unit}")

	@PostMapping("/{variable}")
	public ResponseEntity<Double> convert(@Valid @RequestBody ConverterDTO r, @PathVariable String variable) {
		Double result = converterService.convert(variable, r);
		return ResponseEntity.ok(result);
	}

}
