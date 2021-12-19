package com.japanese;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository
	extends JpaRepository<Word, WordId>, JpaSpecificationExecutor<Word> {
	
	@Query(value="SELECT * From Word ORDER BY RAND() LIMIT 1",
			nativeQuery = true)
	Word getRandomVerb();
	
}
