package com.thiagopaes.model;

import java.io.FileReader;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class Converter {

	private static List<Unit> beans;
	private static List<String> variables;

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

		// variables list
		// @formatter:off
		variables = beans
					.stream()
					.map(e -> e.getVariable())
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

	public Double convert(String variable, RequestModel r) {
		// @formatter:off
		Predicate<Unit> byVariableStr = 
				unit -> unit.getVariable().equals(variable.toLowerCase());
		Predicate<Unit> byUnitFromStr = 
				unit -> unit.getUnit().equals(r.getFrom().toLowerCase());
		Predicate<Unit> byUnitToStr = 
				unit -> unit.getUnit().equals(r.getTo().toLowerCase());

		unitFrom = beans.stream()
				.filter(byVariableStr)
				.filter(byUnitFromStr)
				.findFirst();
		unitTo = beans.stream()
				.filter(byVariableStr)
				.filter(byUnitToStr)
				.findFirst();
		// @formatter:on

		if (unitTo.isEmpty() || unitFrom.isEmpty())
			return null;
		Double result = (unitTo.get().getFactor() / unitFrom.get().getFactor()) * r.getValue();
		return result;
	}

	public static List<Unit> getBeans() {
		return beans;
	}

	public static void setBeans(List<Unit> beans) {
		Converter.beans = beans;
	}

	public static List<String> getVariables() {
		return variables;
	}

	public static void setVariables(List<String> variables) {
		Converter.variables = variables;
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
