package com.japanese;

import java.io.Serializable;
import java.util.Objects;

public class VerbId implements Serializable {
	
	private String word;
	private String furigana;
	
	@Override
	public int hashCode() {
		return Objects.hash(furigana, word);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VerbId other = (VerbId) obj;
		return Objects.equals(furigana, other.furigana) && Objects.equals(word, other.word);
	}

	
	
}
