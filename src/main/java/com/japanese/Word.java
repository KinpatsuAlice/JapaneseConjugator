package com.japanese;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@IdClass(WordId.class)
public class Word {
	
	@Id
	private String kanji;
	
	@Id
	private String kana;
	
	@ElementCollection
	@CollectionTable(name = "word_meanings",
					joinColumns = {@JoinColumn(name = "kanji"),
									@JoinColumn(name = "kana")})
	private List<String> meanings;
	
	@ElementCollection
	@Enumerated(EnumType.ORDINAL)
	@CollectionTable(name = "word_categories",
					joinColumns = {@JoinColumn(name = "kanji"),
									@JoinColumn(name = "kana")})
	private List<PartOfSpeech> wordCategories;
	
	private String pitchAccent;
	
	private boolean isCommon;

	public Word() {}

	public Word(String kanji, String kana, List<String> meanings, List<PartOfSpeech> wordCategories, String pitchAccent,
			boolean isCommon) {
		super();
		this.kanji = kanji;
		this.kana = kana;
		this.meanings = meanings;
		this.wordCategories = wordCategories;
		this.pitchAccent = pitchAccent;
		this.isCommon = isCommon;
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

	public List<PartOfSpeech> getWordCategories() {
		return wordCategories;
	}

	public String getPitchAccent() {
		return pitchAccent;
	}

	public boolean isCommon() {
		return isCommon;
	}

}
