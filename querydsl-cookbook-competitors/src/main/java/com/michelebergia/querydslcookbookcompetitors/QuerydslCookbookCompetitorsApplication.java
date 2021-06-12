package com.michelebergia.querydslcookbookcompetitors;

import com.michelebergia.querydslcookbookcompetitors.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@RequiredArgsConstructor
@Slf4j
public class QuerydslCookbookCompetitorsApplication implements ApplicationRunner {

    private final QueryService queryService;

    public static void main(String[] args) {
        SpringApplication.run(QuerydslCookbookCompetitorsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("------------------------------------------------------------------------");
        log.info("SIMPLE SELECT ----------------------------------------------------------");
        log.info("------------------------------------------------------------------------");
        queryService.simpleSelectAll();
        log.info("------------------------------------------------------------------------");
        log.info("SIMPLE ORDERING BY FIRST NAME AND SALARY IS HIGHER THEN 50K ------------");
        log.info("------------------------------------------------------------------------");
        queryService.simpleOrderingFirstNameWhereSalaryIsHigherOrEqualsThen50k();
    }
}
