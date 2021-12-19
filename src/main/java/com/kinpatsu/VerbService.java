package com.kinpatsu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.japanese.ConjugatedWord;
import com.japanese.PartOfSpeech;
import com.japanese.VerbConjugation;
import com.japanese.VerbDTO;
import com.japanese.ConjugationSettings;
import com.japanese.Word;
import com.japanese.WordRepository;

@Service
public class VerbService {
	
	@Autowired
	WordRepository wordRepository;
	
	public VerbDTO getVerb(ConjugationSettings settings) {
		if (settings.getTypeFilters().isEmpty()) {
			settings.setTypeFilters("VERB_ICHIDAN:"
					+ "VERB_GODAN:"
					+ "VERB_IRREGULAR");
		}
		Word word;
		Pageable firstElement = PageRequest.of(0, 1);
		Page<Word> page = wordRepository.findAll(settings.getSpecifications(),firstElement);
		word = page.toList().get(0);
		return convertToVerbDto(word);
	}
	
	public ConjugatedWord getConjugation(VerbDTO verb, String conjugations) {
		Random randomGenerator = new Random();
		if(conjugations.isEmpty()) {
			VerbConjugation[] allConj = VerbConjugation.values();
			int conjNum = allConj.length;
			return conjugate(verb,allConj[randomGenerator.nextInt(conjNum)].getId());
		} else {
			List<String> conjugationList = new ArrayList<>();
			Stream.of(conjugations.split(":")).forEach(conjugationList::add);
			return conjugate(verb,conjugationList.get(randomGenerator.nextInt(conjugationList.size())));
		}
	}
	
	private ConjugatedWord conjugate(VerbDTO verb, String conj) {
		VerbConjugation conjugation = VerbConjugation.getVerbConjugation(conj);
		if (verb.getVerbType() == PartOfSpeech.VERB_ICHIDAN) {
			return new ConjugatedWord(conjugation.getConjugationName(), getRootForm(verb).concat(conjugation.getIchidanConjugation()));
		}
		if (verb.getVerbType() == PartOfSpeech.VERB_IRREGULAR) {
			return new ConjugatedWord(conjugation.getConjugationName(), conjugation.getIrregularConjugation(verb));
		}
		String conjugatedVerb = getRootForm(verb).concat(conjugation.getGodanInflection(getVerbEnding(verb)));
		switch(conjugation) {
		case PAST_FORM:
		case CONJUNCTIVE_FORM:
		case CONDITIONAL_FORM:
			return new ConjugatedWord(conjugation.getConjugationName(),conjugatedVerb);
		default:
			return new ConjugatedWord(conjugation.getConjugationName(), conjugatedVerb.concat(conjugation.getGodanConjugation()));
		}
	}
	
	private String getRootForm(VerbDTO verb) {
		return verb.getKana().substring(0,verb.getKana().length()-1);
	}
	
	private String getVerbEnding(VerbDTO verb) {
		return "" + verb.getKana().charAt(verb.getKana().length() - 1);
	}
	
	private VerbDTO convertToVerbDto(Word word) {
		PartOfSpeech verbType = null;
		for(PartOfSpeech pos : word.getWordCategories()) {
			switch(pos) {
				case VERB_ICHIDAN:
					verbType = PartOfSpeech.VERB_ICHIDAN;
					break;
				case VERB_GODAN:
					verbType = PartOfSpeech.VERB_GODAN;
					break;
				case VERB_IKU:
					verbType = PartOfSpeech.VERB_IKU;
					break;
				case VERB_IRREGULAR:
					verbType = PartOfSpeech.VERB_IRREGULAR;
					break;
				default:
					continue;
			}
			if(verbType != null)
				break;
		}
		return new VerbDTO(word.getKanji(),word.getKana(),word.getMeanings(),verbType,word.getPitchAccent());
	}
	

}
