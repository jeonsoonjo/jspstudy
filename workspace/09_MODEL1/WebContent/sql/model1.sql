drop table board;
drop table members;

create table member
(
	no number,
	id varchar(32) not null unique,
	pw varchar2(32) not null,
	name varchar2(50),
	email varchar2(200) not null unique,
	regdate date
);

create table board
(
	idx number,
	author varchar2(50),
	title varchar2(2000) not null,
	content varchar2(4000),
	hit number,
	postdate date
);

alter table member add constraint member_pk primary key(no);
alter table board add constraint board_pk primary key(idx);
alter table board add constraint board_member_fk foreign key(author) references member(id); 

drop sequence member_seq;
drop sequence board_seq;
create sequence member_seq increment by 1 start with 1 nocycle nocache;
create sequence board_seq increment by 1 start with 1 nocycle nocache;



