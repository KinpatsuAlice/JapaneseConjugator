package com.database;

public class Sense {
	
	private String[] partOfSpeech;
	private Gloss[] gloss;
	
	public Sense() {
		super();
	}

	public Sense(String[] partOfSpeech, Gloss[] gloss) {
		super();
		this.partOfSpeech = partOfSpeech;
		this.gloss = gloss;
	}

	public String[] getPartOfSpeech() {
		return partOfSpeech;
	}

	public void setPartOfSpeech(String[] partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}

	public Gloss[] getGloss() {
		return gloss;
	}

	public void setGloss(Gloss[] gloss) {
		this.gloss = gloss;
	}
	
	
	
	
	
	
	
	

}
