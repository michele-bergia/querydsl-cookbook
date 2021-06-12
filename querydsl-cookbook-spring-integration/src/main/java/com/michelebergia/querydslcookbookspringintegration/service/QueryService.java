package com.michelebergia.querydslcookbookspringintegration.service;

import com.michelebergia.querydslcookbookspringintegration.entity.Employee;
import com.michelebergia.querydslcookbookspringintegration.entity.QEmployee;
import com.michelebergia.querydslcookbookspringintegration.repository.EmployeeRepository;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueryService {

    private final EmployeeRepository employeeRepository;

    public void examples() {

        final QEmployee qEmployee = QEmployee.employee;

        log.info("-------------------------------");
        log.info("EXAMPLE - 1 -------------------");
        log.info("-------------------------------");


        Iterable<Employee> employees = employeeRepository.findAll(
            qEmployee.firstName.contains("a")
        );
        printData(employees);

        log.info("-------------------------------");
        log.info("EXAMPLE - 2 -------------------");
        log.info("-------------------------------");

        employees = employeeRepository.findAll(
            qEmployee.firstName.contains("a").and(qEmployee.salary.between(40000, 100000))
        );
        printData(employees);

        log.info("-------------------------------");
        log.info("EXAMPLE - 3 -------------------");
        log.info("-------------------------------");

        employees = employeeRepository.findAll(
            qEmployee.department.code.eq(21L)
        );
        printData(employees);

        log.info("-------------------------------");
        log.info("EXAMPLE - 4 -------------------");
        log.info("-------------------------------");

        employees = employeeRepository.findAll(
            new OrderSpecifier<>(Order.ASC, qEmployee.firstName)
        );
        printData(employees);

        log.info("-------------------------------");
        log.info("EXAMPLE - 5 -------------------");
        log.info("-------------------------------");

        //...

    }

    private void printData(Iterable<Employee> employees) {
        employees.forEach(employee -> log.info(employee.toString()));
    }


}
