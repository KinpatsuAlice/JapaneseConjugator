package com.japanese;

public enum AdjectiveType {
	I("い"),NA("な"),IRREGULAR("良い");
	
	private String hiragana;

	private AdjectiveType(String hiragana) {
		this.hiragana = hiragana;
	}

	public String getHiragana() {
		return hiragana;
	}
	
	
	
}
