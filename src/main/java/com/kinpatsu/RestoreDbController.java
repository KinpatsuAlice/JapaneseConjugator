package com.kinpatsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/restore")
public class RestoreDbController {
	
	@Autowired
	RestoreDbService restoreDbService;
	
	@GetMapping
	public String restoreDatabase() {
		restoreDbService.restoreDatabase();
		return "restored";
	}

}
