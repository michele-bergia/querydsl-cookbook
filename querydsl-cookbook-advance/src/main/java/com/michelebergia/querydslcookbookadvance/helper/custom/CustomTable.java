package com.michelebergia.querydslcookbookadvance.helper.custom;

import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.RelationalPathBase;

public class CustomTable extends RelationalPathBase<CustomTable> {

    private final PathBuilder<CustomTable> pathBuilder;

    public CustomTable(String alias, String schema, String table) {
        super(CustomTable.class, alias, schema, table);
        pathBuilder = new PathBuilder<>(CustomTable.class, alias);
    }

    public StringPath getString(String property) {
        return pathBuilder.getString(property);
    }

}


