package com.michelebergia.querydslcookbookadvance.service;

import com.michelebergia.querydslcookbookadvance.SQLDepartment;
import com.michelebergia.querydslcookbookadvance.SQLEmployee;
import com.michelebergia.querydslcookbookadvance.helper.Alias;
import com.michelebergia.querydslcookbookadvance.helper.SubQuery;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class QueryServiceWithBean {

    private final SQLQueryFactory sqlQueryFactory;

    public void exampleSubQuery() {

        final SQLEmployee sqlEmployee = SQLEmployee.employee;
        final SQLDepartment sqlDepartment = SQLDepartment.department;

        log.info("-------------------------------");
        log.info("EXAMPLE - 1 -------------------");
        log.info("-------------------------------");

        //region SubQuery init
        SQLQuery<Integer> departmentCodeQuery = SQLExpressions.select(sqlDepartment.code)
            .from(sqlDepartment);
        //endregion

        //region Initialize the SubQuery helper
        SubQuery<Integer> subQuery = new SubQuery<>(departmentCodeQuery, "dep_query");
        //endregion

        //region Use of SubQuery helper inside the main query
        SQLQuery<String> firstName = sqlQueryFactory.select(sqlEmployee.firstName)
            .from(sqlEmployee)
            .join(subQuery.getSubExpression(), subQuery.getPath())
            .on(sqlEmployee.departmentCode.eq(subQuery.get(sqlDepartment.code)));

        List<String> firstNames = firstName.fetch();
        firstNames.forEach(log::info);
        //endregion

    }

    public void exampleSubQueryAndAliases() {

        final SQLEmployee sqlEmployee = SQLEmployee.employee;
        final SQLDepartment sqlDepartment = SQLDepartment.department;

        log.info("-------------------------------");
        log.info("EXAMPLE - 1 -------------------");
        log.info("-------------------------------");

        //region Init the alias
        final Alias<Integer> departmentCodeAlias = new Alias<>(sqlDepartment.code, "PIPPO");
//        final NumberExpression<Integer> departmentCodeAlias = sqlDepartment.code.as("PIPPO");
        //endregion

        //region SubQuery init
        SQLQuery<Integer> departmentCodeQuery = SQLExpressions.select(departmentCodeAlias.getExpression())
            .from(sqlDepartment);
        //endregion

        //region Initialize the SubQuery helper
        SubQuery<Integer> subQuery = new SubQuery<>(departmentCodeQuery, "dep_query");
        //endregion

        //region Use of SubQuery helper inside the main query
        SQLQuery<String> firstName = sqlQueryFactory.select(sqlEmployee.firstName)
            .from(sqlEmployee)
            .join(subQuery.getSubExpression(), subQuery.getPath())
            .on(sqlEmployee.departmentCode.eq(subQuery.get(departmentCodeAlias.getPath())));

        List<String> firstNames = firstName.fetch();
        firstNames.forEach(log::info);
        //endregion

    }

    public void exampleCaseWhen() {

        final SQLDepartment sqlDepartment = SQLDepartment.department;

        log.info("-------------------------------");
        log.info("EXAMPLE - 1 -------------------");
        log.info("-------------------------------");

        //region CASE-WHEN
        final SimpleExpression<Integer> caseWhen = new CaseBuilder()
            .when(sqlDepartment.code.eq(21))
            .then(Expressions.constant(21))
            .otherwise(Expressions.nullExpression());
        //endregion

        //region Query
        SQLQuery<Integer> caseWhenQuery = sqlQueryFactory.select(caseWhen)
            .from(sqlDepartment);

        List<Integer> departmentCodes = caseWhenQuery.fetch();
        departmentCodes.forEach(departmentCode -> log.info(departmentCode == null ? null : departmentCode.toString()));
        //endregion

    }

}
