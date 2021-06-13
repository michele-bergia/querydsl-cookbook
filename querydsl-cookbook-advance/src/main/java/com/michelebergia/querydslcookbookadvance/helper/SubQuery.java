package com.michelebergia.querydslcookbookadvance.helper;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;

public class SubQuery<T> {

    private final PathBuilder<T> pathBuilder;
    private final Expression<T> expression;

    public SubQuery(Expression<T> expression, String alias) {
        this.pathBuilder = new PathBuilder<>(expression.getType(), alias);
        this.expression = expression;
    }

    public SubQueryExpression<T> getSubExpression() {
        return (SubQueryExpression<T>) expression;
    }

    public Path<T> getPath() {
        return pathBuilder;
    }

    public <E extends Number & Comparable<?>> NumberPath<E> get(NumberPath<E> numberPath) {
        return pathBuilder.get(numberPath);
    }

    public <E> SimplePath<E> get(Path<E> path) {
        return pathBuilder.get(path);
    }

    public StringPath get(StringPath stringPath) {
        return pathBuilder.get(stringPath);
    }

}
