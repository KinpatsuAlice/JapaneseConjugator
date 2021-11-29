package com.japanese;

public enum AdjectiveType {
	I("い"),NA("な");
	
	private String hiragana;

	private AdjectiveType(String hiragana) {
		this.hiragana = hiragana;
	}

	public String getHiragana() {
		return hiragana;
	}
	
	
	
}
