package com.japanese;

import java.util.stream.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Verb {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String word;
	private String furigana;
	
	@Enumerated(EnumType.ORDINAL)
	private VerbType type;
	
	private String[] meaning;
	
	private boolean isTransitive;
	
	private boolean isCommon;
	
	private boolean isExpression;
	
	private String pitchAccent;
	
	@Transient
	private String conjugatedVerb;
	
	@Transient
	private String verbConjugation;
	
	//Constructors
	
	protected Verb() {}
	
	public Verb(String word, String furigana, String type, String[] meaning,
			boolean isTransitive, boolean isCommon, boolean isExpression) {
		this(word, furigana, type, meaning, isTransitive,"",isCommon, isExpression);
	}

	public Verb(String word, String furigana, String type, String[] meaning,
			boolean isTransitive, String pitchAccent, boolean isCommon, boolean isExpression) {
		super();
		this.word = word;
		this.furigana = furigana;
		this.type = VerbType.valueOf(type);
		this.meaning = meaning;
		this.isTransitive = isTransitive;
		this.pitchAccent = pitchAccent;
		this.isCommon = isCommon;
		this.isExpression = isExpression;
	}
	
	//Utility methods
	
	public String conjugate(String conj) {
		if (this.type == VerbType.ICHIDAN) {
			return this.toStemForm().concat(VerbConjugation.getVerbConjugation(conj).getIchidanConjugation());
		}
		if (this.type == VerbType.IRREGULAR) {
			return VerbConjugation.getVerbConjugation(conj).getIrregularConjugation(this);
		}
		VerbConjugation conjugation = VerbConjugation.getVerbConjugation(conj);
		String conjugatedVerb = this.toStemForm().concat(conjugation.getGodanInflection(this));
		switch(conjugation) {
		case PAST_FORM:
		case CONJUNCTIVE_FORM:
		case CONDITIONAL_FORM:
			return conjugatedVerb;
		default:
			return conjugatedVerb.concat(conjugation.getGodanConjugation());
		}
	}
	
	public String getVerbEnding() {
		return "" + this.furigana.charAt(this.furigana.length() - 1);
	}
	
	public String toStemForm() {
		return this.furigana.substring(0,this.furigana.length()-1);
	}
	
	public void printAllConjugations() {
		if (this.type == VerbType.ICHIDAN)
			Stream.of(VerbConjugation.values())
			.forEach(x -> System.out.println(this.toStemForm().concat(x.getIchidanConjugation())));
		else {
			Stream.of(VerbConjugation.values())
			.filter(x -> x != VerbConjugation.CONJUNCTIVE_FORM
					&& x != VerbConjugation.CONDITIONAL_FORM
					&& x != VerbConjugation.PAST_FORM)
			.forEach(x -> System.out.println(this.toStemForm().concat(x.getGodanInflection(this)).concat(x.getGodanConjugation())));
			System.out.println(this.toStemForm()
					.concat(VerbConjugation.CONJUNCTIVE_FORM.getGodanInflection(this)));
			System.out.println(this.toStemForm()
					.concat(VerbConjugation.CONDITIONAL_FORM.getGodanInflection(this)));
			System.out.println(this.toStemForm()
					.concat(VerbConjugation.PAST_FORM.getGodanInflection(this)));
		}
	}
	
	public void setConjugation(String conj) {
		this.conjugatedVerb = this.conjugate(conj);
		this.verbConjugation = VerbConjugation.getVerbConjugation(conj).getConjugationName();
	}
	
	//Getters & Setters

	public long getId() {
		return id;
	}

	public String getWord() {
		return word;
	}

	public String getFurigana() {
		return furigana;
	}

	public VerbType getType() {
		return type;
	}

	public String[] getMeaning() {
		return meaning;
	}

	public boolean isTransitive() {
		return isTransitive;
	}
	
	public boolean isCommon() {
		return isCommon;
	}

	public boolean isExpression() {
		return isExpression;
	}

	public String getPitchAccent() {
		return pitchAccent;
	}

	public String getConjugatedVerb() {
		return conjugatedVerb;
	}

	public String getVerbConjugation() {
		return verbConjugation;
	}
	
}
