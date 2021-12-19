package com.kinpatsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.japanese.*;

@Controller
@RequestMapping("/verb")
public class VerbController {
	
	@Autowired
	private VerbService verbService;
	
	@GetMapping
	public String showConjugations(Model model) {
		model.addAttribute("verbClassifications", VerbConjugation.getClassifiedConjugations());
		return "verb";
		
	}
	
	@PostMapping("/getVerb")
	public @ResponseBody VerbDTO getVerb(@RequestBody ConjugationSettings settings) {
		return verbService.getVerb(settings);
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
	
	@PostMapping("/getConjugation")
	public @ResponseBody ConjugatedWord getConjugation(@RequestBody VerbConjugationSettings settings) {
		return verbService.getConjugation(settings.getVerb(),settings.getConjugations());
	}

}
