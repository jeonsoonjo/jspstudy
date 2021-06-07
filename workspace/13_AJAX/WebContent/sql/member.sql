drop table member;
create table member
(
	no number primary key,
	id varchar2(32),
	name varchar2(32),
	gender varchar2(10),
	address varchar2(100)
);

drop sequence member_seq;
create sequence member_seq increment by 1 start with 1 nocycle nocache;

insert into member values(member_seq.nextval, 'user1', '전순조', '여', '서울');
insert into member values(member_seq.nextval, 'user2', '성봉구', '남', '부산');
insert into member values(member_seq.nextval, 'user3', '전지연', '여', '인천');
insert into member values(member_seq.nextval, 'user4', '윤건우', '남', '울산');
insert into member values(member_seq.nextval, 'user5', '김아토', '여', '대구');
commit;





