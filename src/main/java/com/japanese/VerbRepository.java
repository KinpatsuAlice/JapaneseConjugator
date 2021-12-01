package com.japanese;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VerbRepository
			extends JpaRepository<Verb, Long>, JpaSpecificationExecutor<Verb> {
	
	@Query(value="SELECT * From Verb ORDER BY RAND() LIMIT ?1",
			nativeQuery = true)
	List<Verb> getRandomVerb(int n);

}
