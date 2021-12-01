package com.japanese;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public final class VerbSpecifications {
	
	public static Specification<Verb> filterByVerbType(List<String> filters) {
		return (root,query,builder) -> {
				query.orderBy(builder.asc(builder.function("RAND", null)));
				return builder.or(
				filters.stream()
				.map(value -> builder.equal(root.get("type"), VerbType.valueOf(value).ordinal()))
				.toArray(Predicate[]::new));
		};
	}

}
