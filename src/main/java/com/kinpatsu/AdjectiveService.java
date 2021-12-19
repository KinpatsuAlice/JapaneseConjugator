package com.kinpatsu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.japanese.*;

@Service
public class AdjectiveService {
	
	@Autowired
	WordRepository wordRepository;
	
	public AdjectiveDTO getAdjective(ConjugationSettings settings) {
		if (settings.getTypeFilters().isEmpty()) {
			settings.setTypeFilters("ADJECTIVE_I:"
					+ "ADJECTIVE_NA:"
					+ "ADJECTIVE_IRREGULAR");
		}
		Word word;
		Pageable firstElement = PageRequest.of(0, 1);
		Page<Word> page = wordRepository.findAll(settings.getSpecifications(),firstElement);
		word = page.toList().get(0);
		return convertToAdjectiveDto(word);
	}
	
	public ConjugatedWord getConjugation(AdjectiveDTO adjective, String conjugations) {
		Random randomGenerator = new Random();
		if(conjugations.isEmpty()) {
			AdjectiveConjugation[] allConj = AdjectiveConjugation.values();
			int conjNum = allConj.length;
			return conjugate(adjective,allConj[randomGenerator.nextInt(conjNum)].getId());
		} else {
			List<String> conjugationList = new ArrayList<>();
			Stream.of(conjugations.split(":")).forEach(conjugationList::add);
			return conjugate(adjective,conjugationList.get(randomGenerator.nextInt(conjugationList.size())));
		}
	}
	
	public ConjugatedWord conjugate(AdjectiveDTO adjective, String conj) {
		AdjectiveConjugation conjugation = AdjectiveConjugation.getAdjectiveConjugation(conj);
		String rootAdj = toStemForm(adjective);
		if (adjective.getAdjectiveType() == PartOfSpeech.ADJECTIVE_I)
			return new ConjugatedWord(conjugation.getConjugationName(), rootAdj.concat(conjugation.getiConjugation()));
		return new ConjugatedWord(conjugation.getConjugationName(), rootAdj.concat(conjugation.getNaConjugation()));
	}
	
	private String toStemForm(AdjectiveDTO adjective) {
		if (adjective.getAdjectiveType() == PartOfSpeech.ADJECTIVE_I)
			return adjective.getKana().substring(0,adjective.getKana().length()-1);
		return adjective.getKana();
	}
	
	private AdjectiveDTO convertToAdjectiveDto(Word word) {
		PartOfSpeech adjectiveType = null;
		for(PartOfSpeech pos : word.getWordCategories()) {
			switch(pos) {
				case ADJECTIVE_I:
					adjectiveType = PartOfSpeech.ADJECTIVE_I;
					break;
				case ADJECTIVE_NA:
					adjectiveType = PartOfSpeech.ADJECTIVE_NA;
					break;
				case ADJECTIVE_IRREGULAR:
					adjectiveType = PartOfSpeech.ADJECTIVE_IRREGULAR;
					break;
				default:
					continue;
			}
			if(adjectiveType != null)
				break;
		}
		return new AdjectiveDTO(word.getKanji(),word.getKana(),word.getMeanings(),adjectiveType,word.getPitchAccent());
	}

}
