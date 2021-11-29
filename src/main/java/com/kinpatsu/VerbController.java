package com.kinpatsu;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.database.JacksonUnmarshaller;
import com.database.Jmdict;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.japanese.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/verb")
public class VerbController {
	
	@Autowired
	private VerbRepository verbRepository;
	
	@GetMapping
	public String showConjugations(Model model) {
		model.addAttribute("verbConjugations", VerbConjugation.values());
//		try {
//			log.info("Esto va a petar");
//			Jmdict dictionary = JacksonUnmarshaller.getDictionary();
//			log.info("Longitud: " + dictionary.getWords().length);
//			List<Verb> verbs = dictionary.getAllVerbs().stream().map(x -> x.convertToVerb()).collect(Collectors.toList());
//			log.info("Verbos: " + verbs.size());
//			verbRepository.saveAll(verbs);
//			log.info("*Inserte voz de avast* La base de datos de Katsudou, ha sido actualizada :v");
//		} catch (JsonParseException e) {
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "verb";
	}
	
	@GetMapping("/conjugation")
	public @ResponseBody Verb getVerb(String settings) {
		//Verb verb = new Verb("脱ぐ","ぬぐ","GODAN", new String[] {"to undress"},true);
		Verb verb = verbRepository.getRandomVerb(1).get(0);
		if(settings.length() == 0) {
			VerbConjugation[] conjugations = VerbConjugation.values();
			int conjNum = conjugations.length;
			verb.setConjugation(conjugations[new Random().nextInt(conjNum)].getId());	
		} else {
			List<String> conjugationList = new ArrayList<>();
			Stream.of(settings.split(":")).forEach(conjugationList::add);
			verb.setConjugation(conjugationList.get(new Random().nextInt(conjugationList.size())));
		}
		return verb;
	}

}
