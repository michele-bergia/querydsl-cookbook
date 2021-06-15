package com.michelebergia.querydslcookbooksql.service;

import com.michelebergia.querydslcookbooksql.SQLEmployee;
import com.michelebergia.querydslcookbooksql.entity.Employee;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.MySQLTemplates;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.dml.SQLDeleteClause;
import com.querydsl.sql.dml.SQLInsertClause;
import com.querydsl.sql.dml.SQLUpdateClause;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        //region EXAMPLE 1
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
        //endregion

        //region EXAMPLE 2
        log.info("-------------------------------");
        log.info("EXAMPLE - 2 -------------------");
        log.info("-------------------------------");

        employees = sqlQueryFactory.select(sqlEmployee.all())
            .from(sqlEmployee)
            .where(sqlEmployee.firstName.contains("a"))
            .fetch();

        for (Tuple employee : employees) {
            log.info("firstName " + employee.get(1, String.class)); //firstName, 0 index is ID
            log.info("lastName " + employee.get(sqlEmployee.lastName));
            log.info("salary " + employee.get(sqlEmployee.all()[0])); //department code. Order of values based on fields initialization
        }
        //endregion

        //region EXAMPLE 3
        log.info("-------------------------------");
        log.info("EXAMPLE - 3 -------------------");
        log.info("-------------------------------");

        employees = sqlQueryFactory.select(sqlEmployee.all())
            .from(sqlEmployee)
            .where(sqlEmployee.firstName.contains("a"))
            .fetch();

        for (Tuple employee : employees) {
            log.info("firstName " + employee.get(1, String.class)); //firstName, 0 index is ID
            log.info("lastName " + employee.get(sqlEmployee.lastName));
            log.info("salary " + employee.get(sqlEmployee.all()[0])); //department code. Order of values based on fields initialization
        }
        //endregion

        //region EXAMPLE 4
        log.info("-------------------------------");
        log.info("EXAMPLE - 4 -------------------");
        log.info("-------------------------------");

        employees = sqlQueryFactory.select(sqlEmployee.all())
            .from(sqlEmployee)
            .where(sqlEmployee.firstName.contains("a"))
            .fetch();

        for (Tuple employee : employees) {
            log.info("firstName " + employee.get(1, String.class)); //firstName, 0 index is ID
            log.info("lastName " + employee.get(sqlEmployee.lastName));
            log.info("salary " + employee.get(sqlEmployee.all()[0])); //department code. Order of values based on fields initialization
        }
        //endregion

        //region EXAMPLE 5
        log.info("-------------------------------");
        log.info("EXAMPLE - 5 -------------------");
        log.info("-------------------------------");

        employees = sqlQueryFactory.select(sqlEmployee.all())
            .from(sqlEmployee)
            .where(sqlEmployee.firstName.contains("a"))
            .fetch();

        for (Tuple employee : employees) {
            log.info("firstName " + employee.get(1, String.class)); //firstName, 0 index is ID
            log.info("lastName " + employee.get(sqlEmployee.lastName));
            log.info("salary " + employee.get(sqlEmployee.all()[0])); //department code. Order of values based on fields initialization
        }
        //endregion

    }

    @Transactional
    public void examplePersist() throws SQLException {
        SQLTemplates templates = new MySQLTemplates();
        Configuration configuration = new Configuration(templates);

        final SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, dataSource);

        //region EXAMPLE 6
        log.info("-------------------------------");
        log.info("EXAMPLE - 6 -------------------");
        log.info("-------------------------------");

        SQLEmployee sqlEmployee = SQLEmployee.employee;
        new SQLInsertClause(dataSource.getConnection(), configuration, sqlEmployee)
            //OR
//             sqlQueryFactory.insert(sqlEmployee)
            .set(sqlEmployee.firstName, "Mike")
            .set(sqlEmployee.lastName, "Osberg")
            .set(sqlEmployee.salary, 25000)
            .set(sqlEmployee.departmentCode, 21)
            .execute();

        Employee addedEmployee = new SQLQuery<Employee>(dataSource.getConnection(), configuration)
            .select(Projections.bean(Employee.class, sqlEmployee.all()))
            .from(sqlEmployee)
            .where(sqlEmployee.lastName.eq("Osberg"))
            .fetchOne();

        log.info(addedEmployee.toString());
        //endregion
    }

    @Transactional()
    public void exampleUpdate() throws SQLException {
        SQLTemplates templates = new MySQLTemplates();
        Configuration configuration = new Configuration(templates);

        final SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, dataSource);

        //region EXAMPLE 7
        log.info("-------------------------------");
        log.info("EXAMPLE - 7 -------------------");
        log.info("-------------------------------");

        SQLEmployee sqlEmployee = SQLEmployee.employee;
        new SQLUpdateClause(dataSource.getConnection(), configuration, sqlEmployee)
            //OR
//             sqlQueryFactory.update(sqlEmployee)
            .where(sqlEmployee.lastName.eq("Osberg"))
            .set(sqlEmployee.salary, 1)
            .execute();

        Employee addedEmployee = new SQLQuery<Employee>(dataSource.getConnection(), configuration)
            .select(Projections.bean(Employee.class, sqlEmployee.all()))
            .from(sqlEmployee)
            .where(sqlEmployee.lastName.eq("Osberg"))
            .fetchOne();

        log.info(addedEmployee.toString());
        //endregion
    }

    @Transactional()
    public void exampleDelete() throws SQLException {
        SQLTemplates templates = new MySQLTemplates();
        Configuration configuration = new Configuration(templates);

        final SQLQueryFactory sqlQueryFactory = new SQLQueryFactory(configuration, dataSource);

        //region EXAMPLE 8
        log.info("-------------------------------");
        log.info("EXAMPLE - 8 -------------------");
        log.info("-------------------------------");

        SQLEmployee sqlEmployee = SQLEmployee.employee;
        new SQLDeleteClause(dataSource.getConnection(), configuration, sqlEmployee)
            //OR
//             sqlQueryFactory.delete(sqlEmployee)
            .where(sqlEmployee.lastName.eq("Osberg"))
            .execute();

        Employee addedEmployee = new SQLQuery<Employee>(dataSource.getConnection(), configuration)
            .select(Projections.bean(Employee.class, sqlEmployee.all()))
            .from(sqlEmployee)
            .where(sqlEmployee.lastName.eq("Osberg"))
            .fetchOne();

        log.info(addedEmployee == null ? null : addedEmployee.toString());
        //endregion
    }

}
