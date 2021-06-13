package com.michelebergia.querydslcookbooksql.repository;

import com.michelebergia.querydslcookbooksql.entity.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllBySalaryGreaterThanEqualOrderByFirstName(int salary);

}
