package com.database;

public class Kanji {
	
	private boolean common;
	private String text;
	

	public Kanji() {
		super();
	}

	public Kanji(boolean common, String text) {
		super();
		this.common = common;
		this.text = text;
	}

	public boolean isCommon() {
		return common;
	}

	public void setCommon(boolean common) {
		this.common = common;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
