package com.japanese;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import static com.japanese.VerbSpecifications.*;

public class VerbSettings {
	
	private String conjugations;
	private String typeFilters;
	private boolean noExpression;
	private boolean onlyCommon;
	
	
	//Constructors
	public VerbSettings() {
		super();
	}
	
	public VerbSettings(String conjugations, String typeFilters, boolean noExpression, boolean onlyCommon) {
		super();
		this.conjugations = conjugations;
		this.typeFilters = typeFilters;
		this.noExpression = noExpression;
		this.onlyCommon = onlyCommon;
	}

	//Utility methods
	public Specification<Verb> getSpecifications() {
		List<String> filters =  Arrays.asList(this.typeFilters.split(":"));
		List<VerbType> types = filters.stream()
				.map(VerbType::valueOf)
				.toList();
		Specification<Verb> verbSpecs = filterByVerbType(types);
		if (onlyCommon)
			verbSpecs = verbSpecs.and(filterByCommon());
		if (noExpression)
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
	
	public String getTypeFilters() {
		return typeFilters;
	}
	
	public void setTypeFilters(String filters) {
		this.typeFilters = filters;
	}

	public boolean isNoExpression() {
		return noExpression;
	}

	public void setNoExpression(boolean noExpression) {
		this.noExpression = noExpression;
	}

	public boolean isOnlyCommon() {
		return onlyCommon;
	}

	public void setOnlyCommon(boolean onlyCommon) {
		this.onlyCommon = onlyCommon;
	}
}
