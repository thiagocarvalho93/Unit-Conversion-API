package com.thiagopaes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagopaes.model.Converter;
import com.thiagopaes.model.RequestModel;

@RestController

@RequestMapping("/converterapiv1")
public class ConverterController {

	@Autowired
	private Converter converter;

	@GetMapping("/example")
	public RequestModel requestExample() {
		return new RequestModel("mm", "cm", 100.0);
	}

	@GetMapping("/list")
	public List<String> listVariables() {
		return Converter.getVariables();
	}

	@GetMapping("/list/{variable}")
	public ResponseEntity<List<String>> listUnits(@PathVariable String variable) {
		if (!converter.listUnits(variable).isEmpty())
			return ResponseEntity.ok(converter.listUnits(variable));
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/convert/{variable}")
	public ResponseEntity<Double> convert(@Valid @RequestBody RequestModel r, @PathVariable String variable) {
		Double result = converter.convert(variable, r);
		if (converter.convert(variable, r) != null)
			return ResponseEntity.ok(result);
		return ResponseEntity.notFound().build();
	}

}
