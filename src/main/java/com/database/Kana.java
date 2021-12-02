package com.database;

public class Kana {
	
	private boolean common;
	private String text;
	
	public Kana() {
		super();
	}

	public Kana(boolean common, String text) {
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
