package com.michelebergia.querydslcookbookcompetitors.service;

import com.michelebergia.querydslcookbookcompetitors.entity.Employee;
import com.michelebergia.querydslcookbookcompetitors.entity.QEmployee;
import com.michelebergia.querydslcookbookcompetitors.repository.EmployeeRepository;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class QueryService {

    private final EmployeeRepository employeeRepository;

    private final EntityManager entityManager;


    public void simpleSelectAll() {
        //region JPQL
        long startTime = System.currentTimeMillis();

        List<Employee> employeeJPQL = entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        log.info("JPQL: {}", System.currentTimeMillis() - startTime);
        printData(employeeJPQL);
        //endregion

        //region QueryDSL
        startTime = System.currentTimeMillis();

        final JPAQuery<Employee> employeeJPAQuery = new JPAQuery<>(entityManager);
        List<Employee> employeesQueryDSL = employeeJPAQuery.from(QEmployee.employee).fetch();

        log.info("QueryDSL: {}", System.currentTimeMillis() - startTime);
        printData(employeesQueryDSL);
        //endregion

        //region Spring Data
        startTime = System.currentTimeMillis();

        List<Employee> employeeSpringData = employeeRepository.findAll();

        log.info("Spring Data: {}", System.currentTimeMillis() - startTime);
        printData(employeeSpringData);
        //endregion

        //region JPA Criteria
        startTime = System.currentTimeMillis();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root);
        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        List<Employee> employeeJPACriteria = query.getResultList();

        log.info("JPA Criteria: {}", System.currentTimeMillis() - startTime);
        printData(employeeJPACriteria);
        //endregion

    }

    public void simpleOrderingFirstNameWhereSalaryIsHigherOrEqualsThen50k() {
        //region JPQL
        long startTime = System.currentTimeMillis();

        List<Employee> employeeJPQL = entityManager
            .createQuery("SELECT e FROM Employee e WHERE e.salary > 50000 ORDER BY e.firstName", Employee.class).getResultList();

        log.info("JPQL: {}", System.currentTimeMillis() - startTime);
        printData(employeeJPQL);
        //endregion

        //region QueryDSL
        startTime = System.currentTimeMillis();

        final JPAQuery<Employee> employeeJPAQuery = new JPAQuery<>(entityManager);
        QEmployee qEmployee = QEmployee.employee;
        List<Employee> employeesQueryDSL1 = employeeJPAQuery
            .from(qEmployee)
            .where(qEmployee.salary.goe(50000))
            .orderBy(qEmployee.firstName.asc())
            .fetch();

        log.info("QueryDSL: {}", System.currentTimeMillis() - startTime);
        printData(employeesQueryDSL1);
        //endregion

        //region Spring Data
        startTime = System.currentTimeMillis();

        List<Employee> employeeSpringData = employeeRepository.findAllBySalaryGreaterThanEqualOrderByFirstName(50000);

        log.info("Spring Data: {}", System.currentTimeMillis() - startTime);
        printData(employeeSpringData);
        //endregion

        //region JPA Criteria
        startTime = System.currentTimeMillis();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery
            .select(employeeRoot)
            .where(criteriaBuilder.greaterThanOrEqualTo(employeeRoot.get("salary"), 50000))
            .orderBy(criteriaBuilder.asc(employeeRoot.get("firstName")));
        TypedQuery<Employee> query = entityManager.createQuery(criteriaQuery);
        List<Employee> employeeJPACriteria = query.getResultList();

        log.info("JPA Criteria: {}", System.currentTimeMillis() - startTime);
        printData(employeeJPACriteria);
        //endregion

    }

    private void printData(List<Employee> employees) {
        employees.forEach(employee -> log.info(employee.toString()));
    }

}
