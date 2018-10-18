package com.softtek.javaweb.repository.jpa.predicate;

import org.apache.commons.lang3.math.NumberUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.softtek.javaweb.domain.model.Order;
import com.softtek.javaweb.repository.jpa.dto.RestSearchCriteria;

public class MyUserPredicate {

	private RestSearchCriteria criteria;
		
	public MyUserPredicate(RestSearchCriteria criteria) {
		this.criteria = criteria;
	}

	public RestSearchCriteria getCriteria() {
		return criteria;
	}
	public void setCriteria(RestSearchCriteria criteria) {
		this.criteria = criteria;
	}

	public BooleanExpression getPredicate() {
		PathBuilder<Order> entityPath = new PathBuilder<>(Order.class, "order");
		
		if (NumberUtils.isCreatable(criteria.getValue().toString())) {
			NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
			int value = Integer.parseInt(criteria.getValue().toString());
			switch (criteria.getOperation()) {
				case ":":
					return path.eq(value);
				case ">":
					return path.goe(value);
				case "<":
					return path.loe(value);
				default:
					break;
			}
		} else {
			StringPath path = entityPath.getString(criteria.getKey());
			if (criteria.getOperation().equalsIgnoreCase(":")) {
				return path.containsIgnoreCase(criteria.getValue().toString());
			}
		}
		
		return null;
	}
}
