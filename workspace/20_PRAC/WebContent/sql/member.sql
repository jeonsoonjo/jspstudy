drop table member;
create table member
(
	no number primary key,
	id varchar2(32) not null unique,
	pw varchar2(32)	not null,
	name varchar2(32),
	email varchar2(100),
	phone varchar2(15),
	regdate timestamp
);

drop sequence member_seq;
create sequence member_seq increment by 1 start with 1 nocycle nocache;

insert into MEMBER values(member_seq.nextval, 'admin', '1111', '관리자', 'admin@gmail.com', '010-1111-2222', systimestamp);
commit


