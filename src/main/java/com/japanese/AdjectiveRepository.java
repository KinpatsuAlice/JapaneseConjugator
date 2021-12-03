package com.japanese;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdjectiveRepository
extends JpaRepository<Adjective, Long>, JpaSpecificationExecutor<Adjective> {
	
	@Query(value="SELECT * From Adjective ORDER BY RAND() LIMIT ?1",
			nativeQuery = true)
	List<Adjective> getRandomAdjective(int n);

}
