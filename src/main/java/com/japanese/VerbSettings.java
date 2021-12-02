package com.japanese;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import lombok.extern.slf4j.Slf4j;

import static com.japanese.VerbSpecifications.*;

@Slf4j
public class VerbSettings {
	
	private String conjugations;
	private String filters;
	
	
	//Constructors
	public VerbSettings() {
		super();
	}
	
	public VerbSettings(String conjugations, String filters) {
		super();
		this.conjugations = conjugations;
		this.filters = filters;
	}
	
	//Utility methods
	public Specification<Verb> getSpecifications() {
		List<String> filters =  Arrays.asList(this.filters.split(":"));
		List<VerbType> types = filters.stream()
				.filter(VerbType::isVerbType)
				.map(x -> VerbType.valueOf(x))
				.toList();
		Specification<Verb> verbSpecs = filterByVerbType(types);
		if (filters.contains("COMMON"))
			verbSpecs = verbSpecs.and(filterByCommon());
		if (filters.contains("NO_EXPRESSION"))
			verbSpecs = verbSpecs.and(restrictExpressions());
		return verbSpecs;
	}
	
	//Getters & Setters
	public String getConjugations() {
		return conjugations;
	}
	public void setConjugations(String conjugations) {
		this.conjugations = conjugations;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	
	

}
