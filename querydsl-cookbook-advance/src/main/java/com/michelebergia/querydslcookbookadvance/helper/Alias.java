package com.michelebergia.querydslcookbookadvance.helper;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.SimplePath;

public class Alias<T> {

    private final SimplePath<T> simplePath;
    private final Expression<T> expression;

    public Alias(SimpleExpression<T> path, String alias) {
        this.simplePath = Expressions.path(path.getType(), alias);
        this.expression = path.as(alias);
    }

    public SimplePath<T> getPath() {
        return simplePath;
    }

    public Expression<T> getExpression() {
        return expression;
    }
}
