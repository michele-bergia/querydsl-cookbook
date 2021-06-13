package com.michelebergia.querydslcookbookadvance.service;

import static com.michelebergia.querydslcookbookadvance.config.CustomMySQLTemplate.repeat;

import com.michelebergia.querydslcookbookadvance.SQLCity;
import com.michelebergia.querydslcookbookadvance.SQLEmployee;
import com.michelebergia.querydslcookbookadvance.entity.Employee;
import com.michelebergia.querydslcookbookadvance.helper.custom.CustomTable;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class QueryService {

    //region SQLQueryFactory as Bean
    private final SQLQueryFactory sqlQueryFactory;
    //endregion

    private final DataSource dataSource;

    public void customTemplatesAndOperatorExample() {

        //region CONFIG
        SQLTemplates templates = new MySQLTemplates();
//        SQLTemplates templates = new CustomMySQLTemplate();
        Configuration configuration = new Configuration(templates);

        final SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, dataSource);
        //endregion

        //region EXAMPLES
        final SQLEmployee sqlEmployee = SQLEmployee.employee;

        log.info("-------------------------------");
        log.info("EXAMPLE - 1 -------------------");
        log.info("-------------------------------");

        SQLQuery<String> sqlRepeatedFirstName = sqlQueryFactory.select(repeat(sqlEmployee.firstName, Expressions.constant(3)))
            .from(sqlEmployee)
            .where(sqlEmployee.firstName.contains("a"));
        List<String> repeatedFirstNames = sqlRepeatedFirstName.fetch();
        repeatedFirstNames.forEach(log::info);

        //endregion
    }

    public void exampleQueryOtherSchemaMySQL() {

        //region CONFIG
        SQLTemplates templates = MySQLTemplates.builder()
            .printSchema() //IMPORTANT!
            .build();
        Configuration configuration = new Configuration(templates);

        final SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, dataSource);
        //endregion

        //region Query with SQLCity
        log.info("-------------------------------");
        log.info("SQLCity - Cities --------------");
        log.info("-------------------------------");

        final SQLCity sqlCity = SQLCity.city;

        SQLQuery<String> cityQuery = sqlQueryFactory.select(sqlCity.name)
            .from(sqlCity);
        List<String> cityNames = cityQuery.fetch();
        cityNames.forEach(log::info);
        //endregion

        //region Query with SQLEmployee
        log.info("-------------------------------");
        log.info("SQLEmployee - Salaries --------");
        log.info("-------------------------------");

        final SQLEmployee sqlEmployee = SQLEmployee.employee;

        SQLQuery<Integer> salaryQuery = sqlQueryFactory.select(sqlEmployee.salary)
            .from(sqlEmployee);
        List<Integer> salaries = salaryQuery.fetch();
        salaries.forEach(salary -> log.info(salary.toString()));
        //endregion

    }

    public void exampleQueryOtherSchemaVendorsFirstApproach() {
        //region CONFIG
        SQLTemplates templates = MySQLTemplates.builder()
            .printSchema() //IMPORTANT!
            .build();
        Configuration configuration = new Configuration(templates);

        final SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, dataSource);
        //endregion

        //region Query with SQLCity
        log.info("-------------------------------");
        log.info("SQLCity - Cities --------------");
        log.info("-------------------------------");

        final CustomTable cityTable = new CustomTable("PIPPO", "demo_2", "CITY");

        SQLQuery<String> cityQuery = sqlQueryFactory.select(cityTable.getString("NAME"))
            .from(cityTable);
        List<String> cityNames = cityQuery.fetch();
        cityNames.forEach(log::info);
        //endregion

        //region Query with SQLEmployee
        log.info("-------------------------------");
        log.info("SQLEmployee - Salaries --------");
        log.info("-------------------------------");

        final SQLEmployee sqlEmployee = SQLEmployee.employee;

        SQLQuery<Integer> salaryQuery = sqlQueryFactory.select(sqlEmployee.salary)
            .from(sqlEmployee);
        List<Integer> salaries = salaryQuery.fetch();
        salaries.forEach(salary -> log.info(salary.toString()));
        //endregion
    }

    private void printData(Iterable<Employee> employees) {
        employees.forEach(employee -> log.info(employee.toString()));
    }

}
