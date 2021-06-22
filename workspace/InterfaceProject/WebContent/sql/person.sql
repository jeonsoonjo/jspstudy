DROP TABLE PERSON;
CREATE TABLE PERSON
(
	SNO VARCHAR2(6) PRIMARY KEY,
	NAME VARCHAR2(32),
	AGE NUMBER,
	BIRTHDAY VARCHAR2(10),
	REGDATE DATE
);
select * from person;

insert into person values('123456', '브레드', 30, '1988-04-13', sysdate);
insert into person values('456789', '앨리스', 5, '2017-08-01', sysdate);
insert into person values('205581', '김아토', 60, '1958-10-23', sysdate);

