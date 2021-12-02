package com.japanese;

import java.util.*;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
public class Adjective {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String word;
	
	private String furigana;
	
	@Enumerated(EnumType.ORDINAL)
	private AdjectiveType type;
	
	private boolean isCommon;
	
	private boolean isExpression;
	
	private String[] meaning;
	
	private String pitchAccent;

	
	
	public Adjective(String word, String furigana, String type,
			String[] meaning, boolean isCommon, boolean isExpression) {
		this(word,furigana,type,meaning,"",isCommon,isExpression);
	}

	public Adjective(String word, String furigana, String type, String[] meaning,
			String pitchAccent, boolean isCommon, boolean isExpression) {
		super();
		this.word = word;
		this.furigana = furigana;
		this.type = AdjectiveType.valueOf(type.toUpperCase());
		this.meaning = meaning;
		this.pitchAccent = pitchAccent;
		this.isCommon = isCommon;
		this.isExpression = isExpression;
	}
	
	public String toStemForm() {
		if (this.type == AdjectiveType.I)
			return this.furigana.substring(0,this.furigana.length()-1);
		return this.furigana;
	}

	public String conjugate(String conj) {
		String adjective = this.toStemForm();
		if (this.type == AdjectiveType.I)
			return adjective.concat(AdjectiveConjugation.getAdjectiveConjugation(conj).getiConjugation());
		return adjective.concat(AdjectiveConjugation.getAdjectiveConjugation(conj).getNaConjugation());
	}
	
	public void printAllConjugations() {
		if (this.type == AdjectiveType.I)
			Stream.of(AdjectiveConjugation.values())
			.forEach(x -> System.out.println(this.toStemForm().concat(x.getiConjugation())));
		else
			Stream.of(AdjectiveConjugation.values())
			.forEach(x -> System.out.println(this.toStemForm().concat(x.getNaConjugation())));
	}
	
	//Getters
	
	public long getId() {
		return id;
	}
	
	public String getWord() {
		return word;
	}

	public String getFurigana() {
		return furigana;
	}

	public AdjectiveType getType() {
		return type;
	}

	public boolean isCommon() {
		return isCommon;
	}

	public boolean isExpression() {
		return isExpression;
	}

	public String[] getMeaning() {
		return meaning;
	}

	public String getPitchAccent() {
		return pitchAccent;
	}
	
	

}
