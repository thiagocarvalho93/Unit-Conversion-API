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

import com.thiagopaes.dto.ConversionRequestDTO;
import com.thiagopaes.dto.ResultDTO;
import com.thiagopaes.service.ConverterService;

@RestController

@RequestMapping("/unitconversionapi/v2")
public class ConverterController {

	@Autowired
	private ConverterService converterService;

	@GetMapping
	public List<String> listVariables() {
		return converterService.getVariableNamesList();
	}

	@GetMapping("/{variable}")
	public ResponseEntity<List<String>> listUnits(@PathVariable String variable) {
		return ResponseEntity.ok(converterService.listUnits(variable));
	}

	// TODO: get for ("/{variable}/{unit}")

	@PostMapping("/{variable}")
	public ResponseEntity<ResultDTO> convert(@Valid @RequestBody ConversionRequestDTO r,
			@PathVariable String variable) {
		ResultDTO result = converterService.convert(variable, r);
		return ResponseEntity.ok(result);
	}

}
