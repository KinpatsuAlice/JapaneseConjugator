package com.japanese;

import java.util.List;

public class VerbDTO {
	
	private String kanji;
	private String kana;
	private List<String> meanings;
	private PartOfSpeech verbType;
	private String pitchAccent;

	
	public VerbDTO(String kanji, String kana, List<String> meanings, PartOfSpeech verbType, String pitchAccent) {
		super();
		this.kanji = kanji;
		this.kana = kana;
		this.meanings = meanings;
		this.verbType = verbType;
		this.pitchAccent = pitchAccent;
	}
	
	
	public String getKanji() {
		return kanji;
	}
	
	public String getKana() {
		return kana;
	}
	
	public List<String> getMeanings() {
		return meanings;
	}
	
	public PartOfSpeech getVerbType() {
		return verbType;
	}
	
	public String getPitchAccent() {
		return pitchAccent;
	}
	
}
