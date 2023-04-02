CREATE TABLE board(
     seq NUMBER NOT NULL,               -- 글번호   (시퀀스 객체 이용)
     id VARCHAR2(20) NOT NULL,          -- 아이디   (session: memId)
     name VARCHAR2(40) NOT NULL,        -- 이름	    (session: memName)
     email VARCHAR2(40),                -- 이메일   (session: memEmail)
     subject VARCHAR2(255) NOT NULL,    -- 제목     (getParameter)
     content VARCHAR2(4000) NOT NULL,   -- 내용     (getParameter)

     ref NUMBER NOT NULL,               -- 그룹번호 (SEQ와 같은 번호)
     lev NUMBER DEFAULT 0 NOT NULL,     -- 단계
     step NUMBER DEFAULT 0 NOT NULL,    -- 글순서
     pseq NUMBER DEFAULT 0 NOT NULL,    -- 원글번호
     reply NUMBER DEFAULT 0 NOT NULL,   -- 답변수
     hit NUMBER DEFAULT 0,              -- 조회수
     logtime DATE DEFAULT SYSDATE
 );

시퀀스 객체
CREATE SEQUENCE seq_board  NOCACHE NOCYCLE;