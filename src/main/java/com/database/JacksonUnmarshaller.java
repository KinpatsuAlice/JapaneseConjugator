package com.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.japanese.Verb;
import com.japanese.VerbRepository;
import com.japanese.VerbType;

public class JacksonUnmarshaller {
	
	
	public static Word getWord()
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Word word = mapper.readValue(new File("src\\main\\resources\\static\\test.json"), Word.class);
		return word;
	}
	
	public static Jmdict getDictionary()
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Jmdict dictionary = mapper.readValue(new File("src\\main\\resources\\static\\jmdict-eng-3.1.0.json"), Jmdict.class);
		return dictionary;
	}

	public static void main(String[] args) {

	}
	
	

}
