package com.michelebergia.querydslcookbookadvance;

import com.michelebergia.querydslcookbookadvance.service.QueryService;
import com.michelebergia.querydslcookbookadvance.service.QueryServiceWithBean;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@RequiredArgsConstructor
@Component
public class QuerydslCookbookAdvanceApplication implements ApplicationRunner {

    private final QueryService queryService;
    private final QueryServiceWithBean queryServiceWithBean;

    public static void main(String[] args) {
        SpringApplication.run(QuerydslCookbookAdvanceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        queryService.customTemplatesAndOperatorExample();
//
//        queryServiceWithBean.exampleSubQuery();
//        queryServiceWithBean.exampleSubQueryAndAliases();
//
//        queryService.exampleQueryOtherSchemaMySQL();
//        queryService.exampleQueryOtherSchemaVendorsFirstApproach();
//
//        queryService.exampleProjections();
        queryServiceWithBean.exampleCaseWhen();

    }
}
