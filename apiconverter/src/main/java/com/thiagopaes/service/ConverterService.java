package com.thiagopaes.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.thiagopaes.dto.GetResponseDTO;
import com.thiagopaes.dto.PostRequestDTO;
import com.thiagopaes.dto.PostResponseDTO;
import com.thiagopaes.handler.ArgumentNotValidException;
import com.thiagopaes.model.Unit;

@Service
public class ConverterService {
	private List<Unit> beans;
	private List<String> variableNamesList;

	@Autowired
	private Optional<Unit> unitFrom;
	@Autowired
	private Optional<Unit> unitTo;

	@Value("${file.path}")
	private String filePath;

	// runs on program startup
	@PostConstruct
	void postConstruct() {
		// Pass values from csv to a list of beans
		try {
			// String currentDirectory = System.getProperty("user.dir");
			// String path = currentDirectory + "/src/main/resources/units.csv";
			beans = new CsvToBeanBuilder<Unit>(new FileReader(filePath)).withType(Unit.class).build().parse();
			System.out.println("File read succesfully at:" + filePath);
		} catch (Exception e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		// @formatter:off
		// variables list
		variableNamesList = beans
					.stream()
					.map(e -> e.getVariable())
					.distinct()
					.collect(Collectors.toList());		
		// @formatter:on

	}

	public GetResponseDTO listUnits(String variable) {
		if (!variableNamesList.contains(variable))
			throw new ArgumentNotValidException("Invalid variable: " + variable);
		Predicate<Unit> byVariable = unit -> unit.getVariable().equals(variable.toLowerCase());
		// @formatter:off
		List<String> units = beans
				.stream()
				.filter(byVariable)
				.map(e -> e.getUnit())
				.collect(Collectors.toList());
		// @formatter:on
		return new GetResponseDTO(variable, units);
	}

	// TODO: get this on program startup
	public List<GetResponseDTO> listAll() {
		List<GetResponseDTO> list = new ArrayList<>();
		for (String variable : variableNamesList) {
			list.add(listUnits(variable));
		}
		return list;
	}

	public PostResponseDTO convert(String variable, PostRequestDTO r) {
		List<Unit> units = filterVariable(variable);
		unitFrom = filterUnit(units, r.getFromUnit());
		unitTo = filterUnit(units, r.getToUnit());

		// TODO: verify double value
		Double resultValue = (unitTo.get().getFactor() / unitFrom.get().getFactor()) * r.getValue();
		PostResponseDTO result = new PostResponseDTO(r.getToUnit(), resultValue);
		return result;
	}

	public Optional<Unit> filterUnit(List<Unit> units, String unitStr) {
		// @formatter:off
		Predicate<Unit> byUnitStr = 
				unit -> unit.getUnit().equals(unitStr.toLowerCase());
		Optional<Unit> unidade = units
						.stream()
						.filter(byUnitStr)
						.findFirst();
		// @formatter:on
		if (unidade.isEmpty())
			throw new ArgumentNotValidException("Invalid unit: " + unitStr);
		return unidade;
	}

	public List<Unit> filterVariable(String variable) {
		if (!variableNamesList.contains(variable))
			throw new ArgumentNotValidException("Invalid variable: " + variable);
		// @formatter:off
		Predicate<Unit> byVariableStr = 
				unit -> unit.getVariable()
				.equals(variable.toLowerCase());
		List<Unit> lista = beans
				.stream()
				.filter(byVariableStr)
				.collect(Collectors.toList());
		// @formatter:on
		return lista;
	}

	public List<Unit> getBeans() {
		return beans;
	}

	public void setBeans(List<Unit> beans) {
		this.beans = beans;
	}

	public List<String> getVariableNamesList() {
		return variableNamesList;
	}

	public void setVariableNamesList(List<String> variableNamesList) {
		this.variableNamesList = variableNamesList;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Optional<Unit> getUnitFrom() {
		return unitFrom;
	}

	public void setUnitFrom(Optional<Unit> unitFrom) {
		this.unitFrom = unitFrom;
	}

	public Optional<Unit> getUnitTo() {
		return unitTo;
	}

	public void setUnitTo(Optional<Unit> unitTo) {
		this.unitTo = unitTo;
	}

}
