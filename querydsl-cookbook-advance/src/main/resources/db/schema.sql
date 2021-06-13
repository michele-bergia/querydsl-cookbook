--liquibase formatted sql

--changeset mike:schema


CREATE TABLE demo.DEPARTMENT
(
    ID   INT PRIMARY KEY AUTO_INCREMENT,
    CODE INT UNIQUE NOT NULL,
    NAME VARCHAR(100) NOT NULL
);

CREATE TABLE demo.EMPLOYEE
(
    ID              INT PRIMARY KEY AUTO_INCREMENT,
    FIRST_NAME      VARCHAR(50),
    LAST_NAME       VARCHAR(50),
    SALARY          INT,
    DEPARTMENT_CODE INT NOT NULL,
    FOREIGN KEY (DEPARTMENT_CODE) REFERENCES DEPARTMENT (CODE)
);

