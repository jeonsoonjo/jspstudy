drop table reply;
drop table board;
drop table member_log;
drop table member;

-- 회원 테이블
create table member
(
	no number, -- 회원 번호 pk
	id varchar(32) not null unique, -- 아이디
	pw varchar2(32) not null, -- 비번
	name varchar2(50), -- 이름
	email varchar2(200) not null unique, -- 이메일
	regdate date -- 가입일
);

-- 회원 접속 기록(로그인, 로그아웃 시간 기록) 테이블
create table member_log
(
	no number, -- 회원 번호 pk
	id varchar(32), -- 아이디
	login date,
	logout date
);

-- 게시판 테이블
create table board
(
	idx number, -- 게시글 번호
	author varchar2(50), -- 작성자
	title varchar2(2000) not null, -- 제목
	content varchar2(4000), -- 내용
	hit number, -- 조회수
	ip varchar2(20), -- 작성자 ip
	filename varchar2(300), -- 파일명
	state number, -- 상태(정상: 0, 삭제: -1)
	postdate date, -- 작성일
	lastmodified date -- 최종 수정일
);

-- 댓글 테이블
create table reply
(
	idx number, -- 댓글 번호
	author varchar2(50), -- 작성자
	content varchar2(4000), -- 내용
	ip varchar2(20), -- 작성자 ip
	board_idx number, -- 원글의 번호
	postdate date -- 작성일
);

-- 기본키
alter table member add constraint member_pk primary key(no);
alter table member_log add constraint member_log_pk primary key(no);
alter table board add constraint board_pk primary key(idx);
alter table reply add constraint reply_pk primary key(idx);

-- 외래키
alter table member_log add constraint member_log_fk foreign key(id) references member(id);
alter table board add constraint board_member_fk foreign key(author) references member(id); 
alter table reply add constraint reply_board_fk foreign key(board_idx) references board(idx);

-- 시퀀스
drop sequence member_seq;
drop sequence member_log_seq;
drop sequence board_seq;
drop sequence reply_seq;
create sequence member_seq increment by 1 start with 1 nocycle nocache;
create sequence member_log_seq increment by 1 start with 1 nocycle nocache;
create sequence board_seq increment by 1 start with 1 nocycle nocache;
create sequence reply_seq increment by 1 start with 1 nocycle nocache;

-- 데이터 입력
insert into member values(member_seq.nextval, 'admin', '1111', '관리자', 'admin@naver.com', sysdate);
insert into member values(member_seq.nextval, 'soonjo', '1111', '전순조', 'soonjo@naver.com', sysdate);
insert into member values(member_seq.nextval, 'bongbong', '1111', '봉봉봉', 'bongbong@naver.com', sysdate);
insert into member values(member_seq.nextval, 'ykw', '1111', '윤건우', 'ykw@naver.com', sysdate);
insert into member values(member_seq.nextval, 'jsj', '1111', 'jsj', 'jsj@naver.com', sysdate);
insert into member values(member_seq.nextval, 'jjy', '1111', '전지연', 'jjy@naver.com', sysdate);












