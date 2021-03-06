package com.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.japanese.AdjectiveType;
import com.japanese.VerbType;

public class Jmdict {
	
	private Term[] words;

	public Jmdict() {}
	
	public Jmdict(@JsonProperty("words")Term[] words) {
		super();
		this.words = words;
	}



	public Term[] getWords() {
		return words;
	}
	
	public List<Term> getAllVerbs() {
		List<Term> verbs = new ArrayList<>();
		for(Term w : this.words) {
			for (String s: w.getSense()[0].getPartOfSpeech()) {
				switch(s) {
				case "v1":
				case "vk":
				case "vs-s":
				case "v5b":
				case "v5g":
				case "v5k":
				case "v5m":
				case "v5n":
				case "v5r":
				case "v5s":
				case "v5t":
				case "v5u":
					verbs.add(w);
					break;
					default:
						continue;
				}
			}
		}
		return verbs;
	}
	
	
	public List<Term> getAllAdjectives() {
		List<Term> adjectives = new ArrayList<>();
		for(Term w : this.words) {
			for (String s: w.getSense()[0].getPartOfSpeech()) {
				switch(s) {
				case "adj-i":
				case "adj-na":
				case "adj-ix":
					adjectives.add(w);
				}
			}
		}
		return adjectives;
	}
	
}
