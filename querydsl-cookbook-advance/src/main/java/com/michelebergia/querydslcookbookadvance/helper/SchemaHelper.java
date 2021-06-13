package com.michelebergia.querydslcookbookadvance.helper;

import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.sql.RelationalPathBase;

public class SchemaHelper extends RelationalPathBase<SchemaHelper> {

    private final PathBuilder<SchemaHelper> pathBuilder;

    public SchemaHelper(String variable, String schema, String table) {
        super(SchemaHelper.class, variable, schema, table);
        pathBuilder = new PathBuilder<>(SchemaHelper.class, variable);
    }
}
