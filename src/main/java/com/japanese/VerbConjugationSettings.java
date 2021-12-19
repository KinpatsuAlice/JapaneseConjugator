package com.japanese;

public class VerbConjugationSettings {
	
	private VerbDTO verb;
	private String conjugations;
	
	public VerbConjugationSettings(VerbDTO verb, String conjugations) {
		super();
		this.verb = verb;
		this.conjugations = conjugations;
	}
	
	public VerbDTO getVerb() {
		return verb;
	}
	
	public String getConjugations() {
		return conjugations;
	}
	
	

}
