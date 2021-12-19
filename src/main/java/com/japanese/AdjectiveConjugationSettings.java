package com.japanese;

public class AdjectiveConjugationSettings {
	
	private AdjectiveDTO adjective;
	private String conjugations;
	
	
	public AdjectiveConjugationSettings(AdjectiveDTO adjective, String conjugations) {
		super();
		this.adjective = adjective;
		this.conjugations = conjugations;
	}


	public AdjectiveDTO getAdjective() {
		return adjective;
	}

	public String getConjugations() {
		return conjugations;
	}

}
