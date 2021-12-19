package com.kinpatsu;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.*;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.japanese.PartOfSpeech;
import com.japanese.Word;
import com.japanese.WordRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestoreDbService {
	
	@Autowired
	WordRepository wordRepository;
	
	public void restoreDatabase() {
		try {
			Jmdict dictionary = JacksonUnmarshaller.getDictionary();
			log.info("Longitud: " + dictionary.getWords().length);
			List<Word> words = Stream.of(dictionary.getWords()).map(x -> x.convertToWord()).filter(x-> isUsefulWord(x)).toList();
			log.info("Longitud filtrada: " + words.size());
			for(Word w: words) {
				wordRepository.save(w);
			}
			//wordRepository.saveAll(words);
			log.info("La base de datos de virus, ha sido actualizada :v");
			/*for(Term t : dictionary.getWords()) {
				WordMapper wm = t.convertToWord();
				if(!wm.isUsefulWord())
					continue;
				Word word;
				try {
					wordRepository.save(wm.getWord());
					glossRepository.saveAll(wm.getGloss());
					wordCategoryRepository.saveAll(wm.getWordCategories());
				} catch (Exception e) {
					word = wordRepository.getById(new WordId(wm.getWord().getKanji(),wm.getWord().getKana()));
					System.out.println("Kanji:" + word.getKanji()
					+ "\nKana: " + word.getKana());
				}
					
			}*/
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static boolean isUsefulWord(Word word) {
		boolean isUsefulWord = false;
		for(PartOfSpeech pos : word.getWordCategories()) {
			switch(pos) {
			case VERB_GODAN:
			case VERB_ICHIDAN:
			case VERB_IKU:
			case VERB_IRREGULAR:
			case ADJECTIVE_I:
			case ADJECTIVE_IRREGULAR:
			case ADJECTIVE_NA:
				isUsefulWord = true;
				break;
			default:
				break;
			}
			if(isUsefulWord)
				return true;
		}
		return isUsefulWord;
	}
	

}
