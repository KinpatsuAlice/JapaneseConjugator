package com.kinpatsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EntityScan("com.japanese")
@EnableJpaRepositories(basePackages="com.japanese")
public class JapaneseConjugationApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(JapaneseConjugationApplication.class, args);
		
	}
	

}
