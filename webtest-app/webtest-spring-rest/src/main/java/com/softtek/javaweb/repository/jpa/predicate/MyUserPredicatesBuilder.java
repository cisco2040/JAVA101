package com.softtek.javaweb.repository.jpa.predicate;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.softtek.javaweb.repository.jpa.dto.RestSearchCriteria;

public class MyUserPredicatesBuilder {
    private List<RestSearchCriteria> params;
 
    public MyUserPredicatesBuilder() {
        params = new ArrayList<>();
    }
 
    public MyUserPredicatesBuilder with(String key, String operation, Object value) {   
        params.add(new RestSearchCriteria(key, operation, value));
        return this;
    }
 
    public BooleanExpression build() {
        if (params.isEmpty()) {
            return null;
        }
 
        List<BooleanExpression> predicates = new ArrayList<>();
        MyUserPredicate predicate;
        
        for (RestSearchCriteria param : params) {
            predicate = new MyUserPredicate(param);
            BooleanExpression exp = predicate.getPredicate();
            if (exp != null) {
                predicates.add(exp);
            }
        }
 
        BooleanExpression result = predicates.get(0);
        
        for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        
        return result;
    }
}