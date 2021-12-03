package com.japanese;

import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public final class AdjectiveSpecifications {
	
	public static Specification<Adjective> filterByAdjectiveType(List<AdjectiveType> filters) {
		return (root,query,builder) -> {
				query.orderBy(builder.asc(builder.function("RAND", null)));
				return builder.or(
						filters.stream()
						.map(value -> builder.equal(root.get("type"),value.ordinal()))
						.toArray(Predicate[]::new));
		};
	}
	
	public static Specification<Adjective> filterByCommon() {
		return (root,query,builder) ->
			builder.isTrue(root.get("isCommon"));
	}
	
	public static Specification<Adjective> restrictExpressions() {
		return (root,query,builder) ->
			builder.isFalse(root.get("isExpression"));
	}

}
