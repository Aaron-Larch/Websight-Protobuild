rem Creates user student with required privileges
rem Should be run from system or another user with the DBA role

create user student identified by student;
grant create session to student;
grant alter session to student;
grant create table to student;
grant create view to student;

alter user student default tablespace USERS;
alter user student temporary tablespace TEMP;
alter user student quota unlimited on USERS;

