-- 게시판 테이블
create table board
(
	idx number, -- 게시글 번호
	author varchar2(50), -- 작성자
	title varchar2(2000), -- 제목
	content varchar2(4000), -- 내용
	hit number, -- 조회수
	postdate date -- 작성일
);

-- 댓글 테이블
create table reply
(
	idx number, -- 댓글 번호
	author varchar2(50), -- 작성자
	content varchar2(4000) -- 내용
);

-- 기본키
alter table board add constraint board_pk primary key(idx);
alter table reply add constraint reply_pk primary key(idx);

-- 외래키 
alter table reply add constraint reply_board_fk foreign key(idx) references board(idx);

-- 시퀀스
create sequence board_seq increment by 1 start with 1 nocycle nocache;
create sequence reply_seq increment by 1 start with 1 nocycle nocache;



