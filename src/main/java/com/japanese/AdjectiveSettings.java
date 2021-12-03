package com.japanese;

import static com.japanese.AdjectiveSpecifications.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class AdjectiveSettings {
	
	private String conjugations;
	private String typeFilters;
	private boolean noExpression;
	private boolean onlyCommon;
	
	//Constructors
	public AdjectiveSettings() {
		super();
	}
	
	public AdjectiveSettings(String conjugations, String typeFilters, boolean noExpression, boolean onlyCommon) {
		super();
		this.conjugations = conjugations;
		this.typeFilters = typeFilters;
		this.noExpression = noExpression;
		this.onlyCommon = onlyCommon;
	}
	
	public Specification<Adjective> getSpecifications() {
		List<String> filters =  Arrays.asList(this.typeFilters.split(":"));
		List<AdjectiveType> types = filters.stream()
				.map(AdjectiveType::valueOf)
				.toList();
		Specification<Adjective> adjectiveSpecs = filterByAdjectiveType(types);
		if (onlyCommon)
			adjectiveSpecs = adjectiveSpecs.and(filterByCommon());
		if (noExpression)
			adjectiveSpecs = adjectiveSpecs.and(restrictExpressions());
		return adjectiveSpecs;
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
	
	public void setTypeFilters(String typeFilters) {
		this.typeFilters = typeFilters;
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
