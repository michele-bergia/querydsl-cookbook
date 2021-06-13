package com.michelebergia.querydslcookbooksql.service;

import com.michelebergia.querydslcookbooksql.SQLEmployee;
import com.michelebergia.querydslcookbooksql.entity.Employee;
import com.querydsl.core.Tuple;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.MySQLTemplates;
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

    private final DataSource dataSource;

    public void examples() {

        //region CONFIG
        SQLTemplates templates = new MySQLTemplates();
        Configuration configuration = new Configuration(templates);

        final SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, dataSource);
        //endregion

        //region EXAMPLES
        final SQLEmployee sqlEmployee = SQLEmployee.employee;

        log.info("-------------------------------");
        log.info("EXAMPLE - 1 -------------------");
        log.info("-------------------------------");

        List<Tuple> employees = sqlQueryFactory.select(sqlEmployee.all())
            .from(sqlEmployee)
            .where(sqlEmployee.firstName.contains("a"))
            .fetch();

        for (Tuple employee : employees) {
            log.info("firstName " + employee.get(1, String.class)); //firstName, 0 index is ID
            log.info("lastName " + employee.get(sqlEmployee.lastName));
            log.info("salary " + employee.get(sqlEmployee.all()[0])); //department code. Order of values based on fields initialization
        }

        log.info("-------------------------------");
        log.info("EXAMPLE - 2 -------------------");
        log.info("-------------------------------");

//        employees = employeeRepository.findAll(
//            sqlEmployee.firstName.contains("a").and(sqlEmployee.salary.between(40000, 100000))
//        );
//        printData(employees);

        log.info("-------------------------------");
        log.info("EXAMPLE - 3 -------------------");
        log.info("-------------------------------");

//        employees = employeeRepository.findAll(
//            sqlEmployee.department.code.eq(21L)
//        );
//        printData(employees);

        log.info("-------------------------------");
        log.info("EXAMPLE - 4 -------------------");
        log.info("-------------------------------");

//        employees = employeeRepository.findAll(
//            new OrderSpecifier<>(Order.ASC, sqlEmployee.firstName)
//        );
//        printData(employees);

        log.info("-------------------------------");
        log.info("EXAMPLE - 5 -------------------");
        log.info("-------------------------------");

        //...

        //endregion
    }

    private void printData(Iterable<Employee> employees) {
        employees.forEach(employee -> log.info(employee.toString()));
    }

}
