DROP TABLE STAFF;
CREATE TABLE STAFF
(
    SNO VARCHAR2(5) PRIMARY KEY,
    NAME VARCHAR2(32),
    DEPT VARCHAR2(20),
    REGDATE DATE
);

INSERT INTO STAFF VALUES ('11111', '김사장', '기획부', SYSDATE);
COMMIT

