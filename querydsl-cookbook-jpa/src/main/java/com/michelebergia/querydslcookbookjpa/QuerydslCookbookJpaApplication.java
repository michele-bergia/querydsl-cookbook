package com.michelebergia.querydslcookbookjpa;

import com.michelebergia.querydslcookbookjpa.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@RequiredArgsConstructor
@Component
public class QuerydslCookbookJpaApplication implements ApplicationRunner {

    private final QueryService queryService;

    public static void main(String[] args) {
        SpringApplication.run(QuerydslCookbookJpaApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        queryService.examples();

        queryService.examplePersist();
        queryService.exampleUpdate();
        queryService.exampleDelete();
    }
}
