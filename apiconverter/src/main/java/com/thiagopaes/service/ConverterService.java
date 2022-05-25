package com.thiagopaes.service;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.thiagopaes.dto.ConverterDTO;
import com.thiagopaes.handler.UnitValidationException;
import com.thiagopaes.handler.VariableValidationException;
import com.thiagopaes.model.Unit;

@Service
public class ConverterService {

	private static List<Unit> beans;
	private static List<String> variableList;
	private static List<String> unitList;

	@Autowired
	private Optional<Unit> unitFrom;
	@Autowired
	private Optional<Unit> unitTo;

	// runs on program load
	static {
		try {
			String currentDirectory = System.getProperty("user.dir");
			String path = currentDirectory + "/src/main/resources/units.csv";
			beans = new CsvToBeanBuilder<Unit>(new FileReader(path)).withType(Unit.class).build().parse();
			System.out.println("Arquivo csv lido em:" + path);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}

		// @formatter:off
		// variables list
		variableList = beans
					.stream()
					.map(e -> e.getVariable())
					.distinct()
					.collect(Collectors.toList());
		
		// units list
		unitList = beans
				.stream()
				.map(e -> e.getUnit())
				.distinct()
				.collect(Collectors.toList());
		// @formatter:on

	}

	public List<String> listUnits(String variable) {
		Predicate<Unit> byVariable = unit -> unit.getVariable().equals(variable.toLowerCase());
		// @formatter:off
		return beans
				.stream()
				.filter(byVariable)
				.map(e -> e.getUnit())
				.collect(Collectors.toList());
		// @formatter:on
	}

	public Double convert(String variable, ConverterDTO r) {
		List<Unit> units = filterVariable(variable);
		unitFrom = filterUnit(units, r.getFromUnit());
		unitTo = filterUnit(units, r.getToUnit());

		// TODO: verify double value
		Double result = (unitTo.get().getFactor() / unitFrom.get().getFactor()) * r.getValue();
		return result;
	}

	public Optional<Unit> filterUnit(List<Unit> listaUnidades, String unitStr) {
		// @formatter:off
		Predicate<Unit> byUnitStr = 
				unit -> unit.getUnit().equals(unitStr.toLowerCase());
		Optional<Unit> unidade = listaUnidades
						.stream()
						.filter(byUnitStr)
						.findFirst();
		// @formatter:on
		if (unidade.isEmpty())
			throw new UnitValidationException("invalid unit: " + unitStr);
		return unidade;
	}

	public List<Unit> filterVariable(String variable) {
		if (!variableList.contains(variable))
			throw new VariableValidationException("invalid variable: " + variable);
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

	public static List<Unit> getBeans() {
		return beans;
	}

	public static void setBeans(List<Unit> beans) {
		ConverterService.beans = beans;
	}

	public static List<String> getVariableList() {
		return variableList;
	}

	public static void setVariableList(List<String> variableList) {
		ConverterService.variableList = variableList;
	}

	public static List<String> getUnitList() {
		return unitList;
	}

	public static void setUnitList(List<String> unitList) {
		ConverterService.unitList = unitList;
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
