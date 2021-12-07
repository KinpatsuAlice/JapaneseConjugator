package com.japanese;

public enum VerbConjugationClassification {
	CASUAL("Casual"), POLITE("Polite"), VOLITIONAL("Volitional"),
	HYPOTHETICAL("Hypothetical"), CONDITIONAL("Conditional"), 
	CONJUNCTIVE("Conjunctive"), IMPERATIVE("Imperative"),POTENTIAL("Potential"),
	PASSIVE_CAUSATIVE("Passive & Causative"),TAI("Tai"),OTHERS("Others");
	
	private String name;

	private VerbConjugationClassification(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	

}
