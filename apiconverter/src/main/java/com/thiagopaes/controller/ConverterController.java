package com.thiagopaes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagopaes.dto.GetResponseDTO;
import com.thiagopaes.dto.PostRequestDTO;
import com.thiagopaes.dto.PostResponseDTO;
import com.thiagopaes.service.ConverterService;

@RestController

@RequestMapping("/unitconversionapi/v2")
@Validated
public class ConverterController {

	@Autowired
	private ConverterService converterService;

	@GetMapping
	public List<GetResponseDTO> listVariables() {
		return converterService.listAll();
	}

	@GetMapping("/{variable}")
	public ResponseEntity<GetResponseDTO> listUnits(@PathVariable String variable) {
		return ResponseEntity.ok(converterService.listUnits(variable));
	}

	// TODO: get for ("/{variable}/{unit}")

	@PostMapping("/{variable}")
	public ResponseEntity<PostResponseDTO> convert(@Valid @RequestBody PostRequestDTO r,
			@PathVariable String variable) {
		PostResponseDTO result = converterService.convert(variable, r);
		return ResponseEntity.ok(result);
	}

}
