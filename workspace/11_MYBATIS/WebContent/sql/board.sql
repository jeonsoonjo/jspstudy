drop table reply;
drop table board;

create table board
(
	no number primary key, -- 게시글 번호 pk
	author varchar2(32), -- 작성자
	title varchar2(1000) not null, -- 제목
	content varchar2(4000), -- 내용
	ip varchar2(20), -- 작성자 ip
	hit number, -- 조회수
	postdate date, -- 작성일
	lastmodified date, -- 최종수정일
	state number, -- 정상여부(정상: 0, 삭제: -1)
	groupno number, -- 게시글의 그룹 여부(어떤 게시글과 거기에 달린 댓글들은 모두 같은 그룹)
	groupord number, -- 그룹 내 표시 순서
	depth number -- 게시글/댓글 여부(게시글: 0, 댓글: 1 이상)
);

drop sequence board_seq;
create sequence board_seq increment by 1 start with 1 nocycle nocache;


