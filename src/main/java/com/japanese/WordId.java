package com.japanese;

import java.io.Serializable;

public class WordId implements Serializable {
	
	private String kanji;
	private String kana;
	
	//Constructors
	public WordId() {}
	
	public WordId(String kanji, String kana) {
		super();
		this.kanji = kanji;
		this.kana = kana;
	}

	//Getters & Setters
	public String getKanji() {
		return kanji;
	}
	public void setKanji(String kanji) {
		this.kanji = kanji;
	}
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	
	

}
