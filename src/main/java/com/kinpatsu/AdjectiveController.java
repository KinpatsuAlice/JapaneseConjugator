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
import com.database.Term;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.japanese.AdjectiveConjugation;
import com.japanese.AdjectiveConjugationSettings;
import com.japanese.AdjectiveDTO;
import com.japanese.ConjugatedWord;
import com.japanese.ConjugationSettings;
import com.japanese.VerbConjugationSettings;
import com.japanese.VerbDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/adjective")
public class AdjectiveController {
	
	@Autowired
	private AdjectiveService adjectiveService;
	
	@GetMapping
	public String showConjugations(Model model) {
		model.addAttribute("adjectiveClassifications", AdjectiveConjugation.getClassifiedConjugations());
		return "adjective";
	}
	
	@PostMapping("/getAdjective")
	public @ResponseBody AdjectiveDTO getVerb(@RequestBody ConjugationSettings settings) {
		return adjectiveService.getAdjective(settings);
	}
	
	@PostMapping("/getConjugation")
	public @ResponseBody ConjugatedWord getConjugation(@RequestBody AdjectiveConjugationSettings settings) {
		return adjectiveService.getConjugation(settings.getAdjective(),settings.getConjugations());
	}

}
