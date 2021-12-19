package com.japanese;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import net.bytebuddy.asm.Advice.This;

import static com.japanese.AdjectiveConjugationClassification.*;

public enum AdjectiveConjugation {
	
	//Casual form
	NEGATIVE_FORM("Ne","くない","じゃない","Negative form",CASUAL),
	NEGATIVE_PAST_FORM("Ne-Pa","くなかった","じゃなかった","Negative Past form",CASUAL),
	PAST_FORM("Pa","かった","だった","Past form",CASUAL),
	//Polite form
	POLITE_FORM("Po","いです","です","Polite form",POLITE),
	POLITE_NEGATIVE_FORM("Po-Ne","くないです","じゃありません","Polite Negative form",POLITE),
	POLITE_NEGATIVE_PAST_FORM("Po-Ne-Pa","くなかったです","じゃありまんせんでした","Polite Negative Past form",POLITE),
	POLITE_PAST_FORM("Po-Pa","かったです","でした","Polite Past form",POLITE),
	//Conjunctive form
	CONJUNCTIVE_FORM("Conj","くて","で","Conjunctive form",CONJUNCTIVE),
	NEGATIVE_CONJUNCTIVE_FORM("Ne-Conj","くなくて","じゃなくて","Negative Conjunctive form",CONJUNCTIVE),
	//Sou form
	SOU_FORM("Sou","そう","そう","そう form",SOU),
	POLITE_SOU_FORM("Po-Sou","そうです","そうです","Polite そう form",SOU),
	//Conditional form
	CONDITIONAL_FORM("Cond","かったら","だったら","Conditional form",CONDITIONAL),
	NEGATIVE_CONDITIONAL_FORM("Ne-Cond","くなかったら","じゃなかったら","Negative Conditional form",CONDITIONAL),
	//Hypothetical form
	HYPOTHETICAL_FORM("Hy","ければ","であれば","Hypothetical form",HYPOTHETICAL),
	NEGATIVE_HYPOTHETICAL_FORM("Ne-Hy","くなければ","でなければ","Negative Hypothetical form",HYPOTHETICAL),
	//Adverbial form
	ADVERBIAL_FORM("Adv","く","に","Adverbial form",ADVERBIAL),
	//Others
	NOUN_FORM("Noun","さ","さ","Noun form",OTHERS),
	SUGIRU_FORM("Sugi","すぎる","すぎる","すぎる form",OTHERS);
	
	//Enum attributes
	private String id;
	private String iConjugation;
	private String naConjugation;
	private String conjugationName;
	private AdjectiveConjugationClassification conjugationClass;
	
	
	private static final Map<String, AdjectiveConjugation> conjugations;
	
	static {
		Map<String, AdjectiveConjugation> map = new HashMap<>();
		map = Stream.of(AdjectiveConjugation.values())
				.collect(Collectors.toMap(AdjectiveConjugation::getId, Function.identity()));
		conjugations = Collections.unmodifiableMap(map);
	}
	
	private AdjectiveConjugation(String id, String iConjugation, String naConjugation,
			String conjugationName, AdjectiveConjugationClassification conjugationClass) {
		this.id = id;
		this.iConjugation = iConjugation;
		this.naConjugation = naConjugation;
		this.conjugationName = conjugationName;
		this.conjugationClass = conjugationClass;
	}
	
	public static AdjectiveConjugation getAdjectiveConjugation(String s) {
		return conjugations.get(s);
	}
	
	/*public List<String> showSteps(Adjective adjective) {
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
	}*/
	
	public static Map<String,List<AdjectiveConjugation>> getClassifiedConjugations() {
		AdjectiveConjugationClassification[] classifications = AdjectiveConjugationClassification.values();
		Map<String,List<AdjectiveConjugation>> map = new LinkedHashMap<>();
		Stream.of(classifications).forEach(x -> map.put(x.getName(), new ArrayList<>()));
		map.forEach((x,y) -> y.addAll(
				Stream.of(AdjectiveConjugation.values())
				.filter(z -> z.conjugationClass.getName().equals(x))
				.toList()));
		return map;
	}

	//Getters
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

	public AdjectiveConjugationClassification getConjugationClass() {
		return conjugationClass;
	}
	
	
	
	
	
	

}
