package com.kinpatsu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.database.JacksonUnmarshaller;
import com.database.Jmdict;
import com.database.Word;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.japanese.Adjective;
import com.japanese.AdjectiveConjugation;
import com.japanese.AdjectiveRepository;
import com.japanese.AdjectiveSettings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/adjective")
public class AdjectiveController {
	
	@Autowired
	private AdjectiveRepository adjectiveRepository;
	
	@GetMapping
	public String showConjugations(Model model) {
		model.addAttribute("adjectiveClassifications", AdjectiveConjugation.getClassifiedConjugations());
//		try {
//		log.info("Esto va a petar");
//		Jmdict dictionary = JacksonUnmarshaller.getDictionary();
//		log.info("Longitud: " + dictionary.getWords().length);
//		List<Adjective> adjectives = dictionary.getAllAdjectives().stream().map(Word::convertToAdjective).collect(Collectors.toList());
//		log.info("Adjetivos: " + adjectives.size());
//		adjectiveRepository.saveAll(adjectives);
//		log.info("*Inserte voz de avast* La base de datos de Katsudou, ha sido actualizada :v");
//	} catch (JsonParseException e) {
//		e.printStackTrace();
//	} catch (JsonMappingException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
		return "adjective";
	}
	
	@PostMapping("/conjugation")
	public @ResponseBody Adjective getAdjective(@RequestBody AdjectiveSettings settings) {
		Random randomGenerator = new Random();
		Adjective adjective;
		if(settings.getTypeFilters().isEmpty())
			adjective = adjectiveRepository.getRandomAdjective(1).get(0);
		else {
			Specification<Adjective> specs = settings.getSpecifications();
			Pageable firstElement = PageRequest.of(0, 1);
			Page<Adjective> page = adjectiveRepository.findAll(specs,firstElement);
			adjective = page.toList().get(0);
		}
		if(settings.getConjugations().isEmpty()) {
			AdjectiveConjugation[] conjugations = AdjectiveConjugation.values();
			int conjNum = conjugations.length;
			adjective.setConjugation(conjugations[randomGenerator.nextInt(conjNum)].getId());	
		} else {
			List<String> conjugationList = new ArrayList<>();
			Stream.of(settings.getConjugations().split(":")).forEach(conjugationList::add);
			adjective.setConjugation(conjugationList.get(randomGenerator.nextInt(conjugationList.size())));
		}
		return adjective; 
		
	}

}
