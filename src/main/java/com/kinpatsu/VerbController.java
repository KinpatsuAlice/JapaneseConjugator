package com.kinpatsu;

import java.util.*;
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
		//model.addAttribute("verbConjugations", VerbConjugation.values());
		model.addAttribute("verbClassifications", VerbConjugation.getClassifiedConjugations());
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
	
	@PostMapping("/conjugation")
	public @ResponseBody Verb getVerb(@RequestBody VerbSettings settings) {
		List<String> filters = Arrays.asList(settings.getTypeFilters().split(":"));
		Random randomGenerator = new Random();
		Verb verb;
		if (filters.get(0).isEmpty())
			verb = verbRepository.getRandomVerb(1).get(0);
		else {
			Specification<Verb> specs = settings.getSpecifications();
			Pageable firstElement = PageRequest.of(0, 1);
			Page<Verb> page = verbRepository.findAll(specs,firstElement);
			verb = page.toList().get(0);
		}
		if(settings.getConjugations().isEmpty()) {
			VerbConjugation[] conjugations = VerbConjugation.values();
			int conjNum = conjugations.length;
			verb.setConjugation(conjugations[randomGenerator.nextInt(conjNum)].getId());	
		} else {
			List<String> conjugationList = new ArrayList<>();
			Stream.of(settings.getConjugations().split(":")).forEach(conjugationList::add);
			verb.setConjugation(conjugationList.get(randomGenerator.nextInt(conjugationList.size())));
		}
		return verb;
		/*TODO: Paso 1: Generar verbo concurrente (de momento con conj random(masu))
		 * 		Paso 2: Entregar verb concurr a los clientes
		 * 		Paso 3: Si alguien manda una respuesta correcta, GENERAR nuevo verb
		 * 		FRONT
		 * 		Paso 1: Solicitar un verbo a
		 * 		Paso 2: proceso mágico en segundo plano que verifique si el verbo es el mismo
		 * 		Paso 2.1: Si es el mismo que tenemos, no hagas nada
		 * 		Paso 2.2: Si no, cambiarlo :v
		 * 		Paso 3: llorar*/
	}

}
