package com.japanese;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public enum AdjectiveConjugation {
	
	NEGATIVE_FORM("Ne","くない","じゃない","Negative form"),
	NEGATIVE_PAST_FORM("Ne-Pa","くなかった","じゃなかった","Negative Past form"),
	PAST_FORM("Pa","かった","だった","Past form"),
	
	POLITE_FORM("Po","いです","です","Polite form"),
	POLITE_NEGATIVE_FORM("Po-Ne","くないです","じゃありません","Polite Negative form"),
	POLITE_NEGATIVE_PAST_FORM("Po-Ne-Pa","くなかったです","じゃありまんせんでした","Polite Negative Past form"),
	POLITE_PAST_FORM("Po-Pa","かったです","でした","Polite Past form"),
	
	CONJUNCTIVE_FORM("Conj","くて","で","Conjunctive form"),
	NEGATIVE_CONJUNCTIVE_FORM("Ne-Conj","くなくて","じゃなくて","Negative Conjunctive form"),
	
	SOU_FORM("Sou","そう","そう","そう form"),
	POLITE_SOU_FORM("Po-Sou","そうです","そうです","Polite そう form"),
	
	CONDITIONAL_FORM("Cond","かったら","だったら","Conditional form"),
	NEGATIVE_CONDITIONAL_FORM("Ne-Cond","くなかったら","じゃなかったら","Negative Conditional form"),
	
	HYPOTHETICAL_FORM("Hy","ければ","であれば","Hypothetical form"),
	NEGATIVE_HYPOTHETICAL_FORM("Ne-Hy","くなければ","でなければ","Negative Hypothetical form"),
	
	ADVERBIAL_FORM("Adv","く","に","Adverbial form"),
	
	NOUN_FORM("Noun","さ","さ","Noun form"),
	SUGIRU_FORM("Sugi","すぎる","すぎる","すぎる form");
	
	//Enum attributes
	String id;
	String iConjugation;
	String naConjugation;
	String conjugationName;
	
	private static final Map<String, AdjectiveConjugation> conjugations;
	
	static {
		Map<String, AdjectiveConjugation> map = new HashMap<>();
		map = Stream.of(AdjectiveConjugation.values())
				.collect(Collectors.toMap(AdjectiveConjugation::getId, Function.identity()));
		conjugations = Collections.unmodifiableMap(map);
	}
	
	private AdjectiveConjugation(String id, String iConjugation, String naConjugation, String conjugationName) {
		this.id = id;
		this.iConjugation = iConjugation;
		this.naConjugation = naConjugation;
		this.conjugationName = conjugationName;
	}
	
	public static AdjectiveConjugation getAdjectiveConjugation(String s) {
		return conjugations.get(s);
	}
	
	public List<String> showSteps(Adjective adjective) {
		AdjectiveType type = adjective.getType();
		String adjInflection;
		List<String> adjectiveSteps = new ArrayList<>();
		List<String> steps = new ArrayList<>();
		steps.add("1. Remove the " + type.getHiragana() + " from adjective.");
		adjectiveSteps.add(adjective.toStemForm());
		steps.add(adjective.getFurigana() + " -> " + adjectiveSteps.get(0));
		adjInflection = (type == AdjectiveType.I ? this.iConjugation : this.naConjugation);
		steps.add("2. Add the inflection: " + adjInflection + " to the adjective.");
		adjectiveSteps.add(adjectiveSteps.get(0).concat(adjInflection));
		steps.add(adjectiveSteps.get(0) + " -> " + adjectiveSteps.get(1));
		return steps;
	}

	public String getId() {
		return id;
	}

	public String getiConjugation() {
		return iConjugation;
	}

	public String getNaConjugation() {
		return naConjugation;
	}

	public String getConjugationName() {
		return conjugationName;
	}
	
	
	
	
	

}
