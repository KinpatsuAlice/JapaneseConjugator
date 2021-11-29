package com.kinpatsu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adjective")
public class AdjectiveController {
	
	@GetMapping
	public String showConjugations() {
		return "adjective";
	}

}
