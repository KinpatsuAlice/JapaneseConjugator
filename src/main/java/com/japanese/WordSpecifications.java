package com.japanese;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public final class WordSpecifications {
	
	public static Specification<Word> filterByType(List<PartOfSpeech> types) {
		return (root,query,builder) -> {
			query.orderBy(builder.asc(builder.function("RAND", null)));
			return builder.or(types.stream()
					.map(x -> builder.isMember(x,root.get("wordCategories")))
					.toArray(Predicate[]::new));
		};
	}
	
	public static Specification<Word> filterByCommon() {
		return (root,query,builder) ->
			builder.isTrue(root.get("isCommon"));
	}
	
	public static Specification<Word> restrictExpressions() {
		return (root,query,builder) ->
			builder.isNotMember(PartOfSpeech.EXPRESSION,root.get("wordCategories"));
	}
}
