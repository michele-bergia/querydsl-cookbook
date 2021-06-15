package com.michelebergia.querydslcookbookjpa.service;

import static java.util.Collections.singletonList;

import com.michelebergia.querydslcookbookjpa.entity.Employee;
import com.michelebergia.querydslcookbookjpa.entity.QDepartment;
import com.michelebergia.querydslcookbookjpa.entity.QEmployee;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class QueryService {

    private final EntityManager entityManager;

    public void examples() {

        final QEmployee qEmployee = QEmployee.employee;
        final QDepartment qDepartment = QDepartment.department;

        //region EXAMPLE 1
        log.info("-------------------------------");
        log.info("EXAMPLE - 1 -------------------");
        log.info("-------------------------------");

        List<Employee> employees = new JPAQuery<>(entityManager).select(qEmployee)
            .from(qEmployee)
            .where(qEmployee.firstName.contains("a"))
            .fetch();
        printData(employees);
        //endregion

        //region EXAMPLE 2
        log.info("-------------------------------");
        log.info("EXAMPLE - 2 -------------------");
        log.info("-------------------------------");

        employees = new JPAQuery<>(entityManager).select(qEmployee)
            .from(qEmployee)
            .where(qEmployee.department.code.eq(21L))
            .fetch();
        printData(employees);
        //endregion

        //region EXAMPLE 3
        log.info("-------------------------------");
        log.info("EXAMPLE - 3 -------------------");
        log.info("-------------------------------");

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.andAnyOf( // ... OR ... OR ...
            qEmployee.firstName.endsWith("a"),
            qEmployee.lastName.endsWith("a"),
            qEmployee.department.code.eq(21L));
        employees = new JPAQuery<>(entityManager).select(qEmployee)
            .from(qEmployee)
            .where(booleanBuilder)
            .fetch();
        printData(employees);
        //endregion

        //region EXAMPLE 4
        log.info("-------------------------------");
        log.info("EXAMPLE - 4 -------------------");
        log.info("-------------------------------");

        booleanBuilder = new BooleanBuilder();
        booleanBuilder.orAllOf( // ... AND ... AND ...
            qEmployee.firstName.endsWith("a"),
            qEmployee.lastName.endsWith("a"),
            qEmployee.department.code.eq(21L));
        employees = new JPAQuery<>(entityManager).select(qEmployee)
            .from(qEmployee)
            .where(booleanBuilder)
            .fetch();
        printData(employees);
        //endregion

        //region EXAMPLE 5
        log.info("-------------------------------");
        log.info("EXAMPLE - 5 -------------------");
        log.info("-------------------------------");

        List<String> departmentNames = new JPAQuery<>(entityManager).select(qDepartment.name)
            .from(qEmployee)
            .fetch();
        printData(departmentNames);
        //endregion

    }

    @Transactional
    public void examplePersist(){
        //region EXAMPLE 6
        log.info("-------------------------------");
        log.info("EXAMPLE - 6 -------------------");
        log.info("-------------------------------");

        //JPAInsertClause just introduced and it's buggy

//        new JPAInsertClause(entityManager, qEmployee)
//            .set(qEmployee.firstName, "Mike")
//            .set(qEmployee.lastName, "Osberg")
//            .set(qEmployee.salary, 25000)
//            .execute();
//
//        Employee addedEmployee = new JPAQuery<>(entityManager).select(qEmployee)
//            .from(qEmployee)
//            .where(qEmployee.lastName.eq("Osberg"))
//            .fetchOne();
//        printData(singletonList(addedEmployee));
        //endregion
    }

    @Transactional()
    public void exampleUpdate() {
        final QEmployee qEmployee = QEmployee.employee;

        //region EXAMPLE 7
        log.info("-------------------------------");
        log.info("EXAMPLE - 7 -------------------");
        log.info("-------------------------------");

        new JPAUpdateClause(entityManager, qEmployee)
            .where(qEmployee.lastName.eq("Longmate"))
            .set(qEmployee.salary, 1)
            .execute();

        Employee addedEmployee = new JPAQuery<>(entityManager).select(qEmployee)
            .from(qEmployee)
            .where(qEmployee.lastName.eq("Longmate"))
            .fetchOne();
        printData(singletonList(addedEmployee));
        //endregion
    }

    @Transactional()
    public void exampleDelete() {
        final QEmployee qEmployee = QEmployee.employee;

        //region EXAMPLE 8
        log.info("-------------------------------");
        log.info("EXAMPLE - 8 -------------------");
        log.info("-------------------------------");

        new JPADeleteClause(entityManager, qEmployee)
            .where(qEmployee.lastName.eq("Longmate"))
            .execute();

        Employee addedEmployee = new JPAQuery<>(entityManager).select(qEmployee)
            .from(qEmployee)
            .where(qEmployee.lastName.eq("Longmate"))
            .fetchOne();
        printData(singletonList(addedEmployee));
        //endregion
    }

    private <T> void printData(List<T> data) {
        data.forEach(elem -> log.info(elem == null ? null : elem.toString()));
    }

}
