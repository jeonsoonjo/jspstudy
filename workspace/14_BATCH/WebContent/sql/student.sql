drop table student;
create table student
(
	sno varchar2(5) primary key,
	name varchar2(32),
	kor number(3) check(kor between 0 and 100),
	eng number(3) check(eng between 0 and 100),
	mat number(3) check(mat between 0 and 100),
	ave number(5,2),
	grade char(1)
);

