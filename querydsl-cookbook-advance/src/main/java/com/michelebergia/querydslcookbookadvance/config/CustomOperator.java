package com.michelebergia.querydslcookbookadvance.config;

import com.querydsl.core.types.Operator;

public enum CustomOperator implements Operator {
    REPEAT(String.class);

    private final Class<?> type;

    CustomOperator(Class<?> type) {
        this.type = type;
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
