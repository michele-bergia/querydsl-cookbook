--liquibase formatted sql

--changeset mike:data


insert into DEPARTMENT (CODE, NAME)
values (21, 'Marloro');
insert into DEPARTMENT (CODE, NAME)
values (34, 'Tesla');
insert into DEPARTMENT (CODE, NAME)
values (57, 'Apple');

insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('Theressa', 'Longmate', 64520, 21);
insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('April', 'Heed', 27281, 34);
insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('Damita', 'Hammersley', 17990, 21);
insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('Nico', 'Philippe', 28581, 34);
insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('Gordon', 'Oke', 74401, 21);
insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('Davy', 'Tsar', 69304, 21);
insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('Webster', 'Barrs', 50316, 21);
insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('Munmro', 'Enterle', 29270, 34);
insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('Cissy', 'Smowton', 35227, 21);
insert into EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_CODE)
values ('Peggi', 'Athow', 55983, 57);


