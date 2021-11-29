package com.japanese;

import java.util.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerbRepository
			extends CrudRepository<Verb, Long> {
	
	@Query(value="SELECT * From Verb ORDER BY RAND() LIMIT ?1",
			nativeQuery = true)
	List<Verb> getRandomVerb(int n);

}
