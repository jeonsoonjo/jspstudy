drop table board1;
drop table member1_log;
drop table member1;

-- 회원 테이블
create table member1
(
	no number, -- 회원번호, pk
	id varchar2(30) not null unique, -- 아이디
	pw varchar2(30) not null, -- 비번
	name varchar2(50), -- 이름
	email varchar2(200) not null unique, -- 이메일
	regdate date -- 가입일
);

-- 회원 접속(로그인, 로그아웃 시간 기록) 테이블
create table member1_log
(
	no number, -- 회원번호, pk
	id varchar2(30), -- 아이디, (회원)아이디 참조 fk
	login date,
	logout date
);

-- 게시판 테이블
create table board1
(
	idx number, -- 게시글 번호, pk
	writer varchar2(50), -- 작성자, (회원)아이디 참조 fk
	title varchar2(2000) not null, -- 제목
	content varchar2(4000), -- 내용
	hit number, -- 조회수
	ip varchar2(20), -- ip
	filename varchar2(300), -- 파일명
	state number, -- 상태(정상: 0, 삭제: -1)
	postdate date, -- 작성일
	lastmodified date -- 최종 수정일
);

-- 댓글 테이블
create table reply1
(
	idx number, -- 댓글 번호, pk
	writer varchar2(50), -- 작성자
	content varchar2(4000), -- 내용
	ip varchar2(20), -- ip
	board1_idx number, -- 원글의 번호, (게시판)게시글 번호 참조 fk
	postdate date -- 작성일
);

-- 기본키(PK)
alter table member1 add constraint member1_pk primary key(no);
alter table member1_log add constraint member1_log_pk primary key(no);
alter table board1 add constraint board1_pk primary key(idx);
alter table reply1 add constraint reply1_pk primary key(idx);

-- 외래키(FK)
alter table member1_log add constraint member1_log_fk foreign key(id) references member1(id);
alter table board1 add constraint board1_member1_fk foreign key(writer) references member1(id);
alter table reply1 add constraint reply1_board1_fk foreign key(board1_idx) references board1(idx);

-- 시퀀스
drop sequence member1_seq;
drop sequence member1_log_seq;
drop sequence board1_seq;
drop seqeence reply1_seq;

create sequence member1_seq increment by 1 start with 1 nocycle nocache;
create sequence member1_log_seq increment by 1 start with 1 nocycle nocache;
create sequence board1_seq increment by 1 start with 1 nocycle nocache;
create sequence reply1_seq increment by 1 start with 1 nocycle nocache;

-- 데이터 입력
insert into member1 values(member_seq.nextval, 'admin', '1111', '관리자', 'admin@naver.com', sysdate);
insert into member1 values(member_seq.nextval, 'soonjo', '1111', '전순조', 'soonjo@naver.com', sysdate);
insert into member1 values(member_seq.nextval, 'bongbong', '1111', '봉봉봉', 'bongbong@naver.com', sysdate);
insert into member1 values(member_seq.nextval, 'ykw', '1111', '윤건우', 'ykw@naver.com', sysdate);
insert into member1 values(member_seq.nextval, 'jsj', '1111', 'jsj', 'jsj@naver.com', sysdate);
insert into member1 values(member_seq.nextval, 'kimesoon', '1111', '김이순', 'kimesoon@naver.com', sysdate);








