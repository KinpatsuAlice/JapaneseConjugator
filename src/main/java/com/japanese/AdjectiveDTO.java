package com.japanese;

import java.util.List;

public class AdjectiveDTO {
	
	private String kanji;
	private String kana;
	private List<String> meanings;
	private PartOfSpeech adjectiveType;
	private String pitchAccent;
	
	
	public AdjectiveDTO(String kanji, String kana, List<String> meanings, PartOfSpeech adjectiveType, String pitchAccent) {
		super();
		this.kanji = kanji;
		this.kana = kana;
		this.meanings = meanings;
		this.adjectiveType = adjectiveType;
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

	public PartOfSpeech getAdjectiveType() {
		return adjectiveType;
	}

	public String getPitchAccent() {
		return pitchAccent;
	}

}
