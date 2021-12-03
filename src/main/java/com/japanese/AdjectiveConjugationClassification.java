package com.japanese;

public enum AdjectiveConjugationClassification {
	CASUAL("Casual"), POLITE("Polite"), SOU("Sou"),
	HYPOTHETICAL("Hypothetical"), CONDITIONAL("Conditional"), 
	CONJUNCTIVE("Conjunctive"), ADVERBIAL("Adverbial"),OTHERS("Others");
	
	private String name;

	private AdjectiveConjugationClassification(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
