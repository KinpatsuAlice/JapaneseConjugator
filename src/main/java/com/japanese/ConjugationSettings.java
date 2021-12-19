package com.japanese;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.domain.Specification;

import static com.japanese.WordSpecifications.*;

public class ConjugationSettings {
	
	private String typeFilters;
	private boolean noExpression;
	private boolean onlyCommon;
	
	
	public ConjugationSettings(String typeFilters, boolean noExpression, boolean onlyCommon) {
		super();
		this.typeFilters = typeFilters;
		this.noExpression = noExpression;
		this.onlyCommon = onlyCommon;
	}
	
	public Specification<Word> getSpecifications() {
		String[] filters = typeFilters.split(":");
		List<PartOfSpeech> pos = Stream.of(filters)
		.map(PartOfSpeech::valueOf)
		.toList();
		Specification<Word> specs = filterByType(pos);
		if(onlyCommon)
			specs = specs.and(filterByCommon());
		if(noExpression)
			specs = specs.and(restrictExpressions());
		return specs;
	}

	public String getTypeFilters() {
		return typeFilters;
	}

	public void setTypeFilters(String typeFilters) {
		this.typeFilters = typeFilters;
	}

	public boolean isNoExpression() {
		return noExpression;
	}

	public boolean isOnlyCommon() {
		return onlyCommon;
	}

}
