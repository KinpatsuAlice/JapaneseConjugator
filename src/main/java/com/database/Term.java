package com.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.japanese.PartOfSpeech;
import com.japanese.Word;

public class Term {
	
	private Kanji kanji[];
	private Kana kana[];
	private Sense sense[];
	
	public Term() {
		super();
	}
	
	public Term(@JsonProperty("kanji")Kanji[] kanji,
			@JsonProperty("kana")Kana kana[],
			@JsonProperty("sense")Sense[] sense) {
		super();
		this.kanji = kanji;
		this.kana = kana;
		this.sense = sense;
	}

	public Kanji[] getKanji() {
		return kanji;
	}

	public void setKanji(Kanji[] kanji) {
		this.kanji = kanji;
	}

	public Kana[] getKana() {
		return kana;
	}

	public void setKana(Kana[] kana) {
		this.kana = kana;
	}

	public Sense[] getSense() {
		return sense;
	}

	public void setSense(Sense[] sense) {
		this.sense = sense;
	}

	
	public Word convertToWord() {
		String kana = this.getKana()[0].getText();
		String kanji = this.getKanji().length == 0 ? kana : this.getKanji()[0].getText();
		boolean isCommon = this.getKanji().length == 0 ? this.getKana()[0].isCommon() : this.getKanji()[0].isCommon();
		List<String> meanings = new ArrayList<>();
		for (Sense s : this.getSense())
			for (Gloss g : s.getGloss())
				meanings.add(g.getText());
		List<PartOfSpeech> categories = new ArrayList<>();
		for(String pos : this.getSense()[0].getPartOfSpeech()) {
			switch(pos) {
				case "v1":
					categories.add(PartOfSpeech.VERB_ICHIDAN);
					break;
				case "v5b":
				case "v5g":
				case "v5k":
				case "v5m":
				case "v5n":
				case "v5r":
				case "v5s":
				case "v5t":
				case "v5u":
					categories.add(PartOfSpeech.VERB_GODAN);
					break;
				case "vk":
				case "vs-s":
				case "vs-i":
					categories.add(PartOfSpeech.VERB_IRREGULAR);
					break;
				case "v5k-s":
					categories.add(PartOfSpeech.VERB_IKU);
					break;
				case "adj-i":
					categories.add(PartOfSpeech.ADJECTIVE_I);
					break;
				case "adj-na":
					categories.add(PartOfSpeech.ADJECTIVE_NA);
					break;
				case "adj-ix":
					categories.add(PartOfSpeech.ADJECTIVE_IRREGULAR);
					break;
				case "exp":
					categories.add(PartOfSpeech.EXPRESSION);
					break;
				case "vt":
					categories.add(PartOfSpeech.TRANSITIVE);
					break;
				case "vi":
					categories.add(PartOfSpeech.INTRANSITIVE);
					break;
			}
		}
		return new Word(kanji, kana,meanings,categories, "", isCommon);
	}
	
	
	/*public  Verb convertToVerb() {
		VerbType verbType = VerbType.IRREGULAR;
		boolean isTransitive = false;
		boolean isCommon = this.getKanji().length == 0 ? this.getKana()[0].isCommon() : this.getKanji()[0].isCommon();
		boolean isExpression = false;
		for(String pos : this.getSense()[0].getPartOfSpeech()) {
			switch(pos) {
			case "v1":
				verbType = VerbType.ICHIDAN;
				break;
			case "vk":
			case "vs-s":
				verbType = VerbType.IRREGULAR;
				break;
			case "v5b":
			case "v5g":
			case "v5k":
			case "v5m":
			case "v5n":
			case "v5r":
			case "v5s":
			case "v5t":
			case "v5u":
				verbType = VerbType.GODAN;
				break;
			}
			if(pos.equals("vt"))
				isTransitive = true;
			if(pos.equals("exp"))
				isExpression = true;
		}
		List<String> gloss = new ArrayList<>();
		for (Sense s : this.getSense())
			for (Gloss g : s.getGloss())
				gloss.add(g.getText());
		String[] meanings = gloss.toArray(new String[0]);
		Verb verb = new Verb((this.getKanji().length == 0 ? this.getKana()[0].getText() : this.getKanji()[0].getText()),this.getKana()[0].getText(),
				verbType.toString(),meanings,isTransitive,isCommon,isExpression);
		return verb;
	}
	
	public  Adjective convertToAdjective() {
		AdjectiveType type = AdjectiveType.I;
		boolean isCommon = this.getKanji().length == 0 ? this.getKana()[0].isCommon() : this.getKanji()[0].isCommon();
		boolean isExpression = false;
		for (String s: this.getSense()[0].getPartOfSpeech()) {
			switch(s) {
			case "adj-i":
				type = AdjectiveType.I;
				break;
			case "adj-na":
				type = AdjectiveType.NA;	
				break;
			case "adj-ix":
				type = AdjectiveType.IRREGULAR;	
			}
			if(s.equals("exp"))
				isExpression = true;
		}
		List<String> gloss = new ArrayList<>();
		for (Sense s : this.getSense())
			for (Gloss g : s.getGloss())
				gloss.add(g.getText());
		String[] meanings = gloss.toArray(new String[0]);
		Adjective adjective = new Adjective((this.getKanji().length == 0 ? this.getKana()[0].getText() : this.getKanji()[0].getText()),this.getKana()[0].getText(),type.toString(),meanings,isCommon,isExpression);
		return adjective;
	}*/
	
	public void printContent() {
		System.out.println("Kanji:");
		Stream.of(this.kanji).forEach(x -> System.out.println("\tText: " + x.getText()));
		System.out.println("Kana:");
		Stream.of(this.kana).forEach(x -> System.out.println("\tText: " + x.getText()));
		System.out.println("Sense:");
		for(Sense s: this.sense) {
			System.out.println("\tPartOfSpeech:");
			Stream.of(s.getPartOfSpeech()).forEach(System.out::println);
			System.out.println("\tGloss:");
			Stream.of(s.getGloss()).forEach(x -> System.out.println(x.getText()));
		}
	}
	
	

}
