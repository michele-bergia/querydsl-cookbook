package com.michelebergia.querydslcookbookadvance.config;

import static com.michelebergia.querydslcookbookadvance.config.CustomOperator.REPEAT;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.sql.MySQLTemplates;

/**
 * List of MySQL functions https://www.techonthenet.com/mysql/functions/index.php
 */
public class CustomMySQLTemplate extends MySQLTemplates {

    public CustomMySQLTemplate() {
        super();
        add(REPEAT, "REPEAT({0},{1})");
    }

    /**
     * The MySQL REPEAT function repeats a string a specified number of times.
     *
     * @param string The string to repeat.
     * @param number The number of times to repeat the string.
     * @return The MYSQL function for REPEAT
     */
    public static StringExpression repeat(Expression<String> string, Expression<Number> number) {
        return Expressions.stringOperation(REPEAT, string, number);
    }

}
