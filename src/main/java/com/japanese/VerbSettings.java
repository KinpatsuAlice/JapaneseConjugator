package com.japanese;

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
