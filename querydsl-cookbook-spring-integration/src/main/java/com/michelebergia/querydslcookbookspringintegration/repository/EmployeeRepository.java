package com.michelebergia.querydslcookbookspringintegration.repository;

import com.michelebergia.querydslcookbookspringintegration.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {

    List<Employee> findAllBySalaryGreaterThanEqualOrderByFirstName(int salary);

}
