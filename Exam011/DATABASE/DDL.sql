CREATE TABLE book_tbl_06(
  book_no NUMBER PRIMARY KEY,
  book_name VARCHAR2(50) NOT NULL,
  book_coverimg VARCHAR2(20),
  book_date DATE,
  book_price NUMBER,
  book_publisher VARCHAR2(50),
  book_info VARCHAR2(2000)
);

CREATE TABLE rent_tbl_06(
  rent_no NUMBER PRIMARY KEY,
  book_no NUMBER REFERENCES book_tbl_06(book_no) NOT NULL,
  rent_price NUMBER NOT NULL,
  rent_date DATE NOT NULL,
  rent_status CHAR(1) DEFAULT '0' NOT NULL
);

INSERT INTO book_tbl_06
  VALUES (100
          , '리눅스'
          , '100.jpg'
          , '2015/09/02'
          , 24000
          , '나룩스'
          , '운영체제, DB기초, 네트워크기초, 개발환경구축');

INSERT INTO book_tbl_06
  VALUES (101
          , '자바'
          , '101.jpg'
          , '2016/01/10'
          , 20000
          , '이자바'
          , '프로그래밍 언어');

INSERT INTO book_tbl_06
  VALUES (102
          , '자바웹프로그래밍'
          , '102.jpg'
          , '2016/10/30'
          , 25000
          , '김프로'
          , '개발환경/서버프로그램/배치프로그램');
          
INSERT INTO book_tbl_06
  VALUES (103
          , '오픈소스활용하기'
          , '103.jpg'
          , '2017/09/01'
          , 30000
          , '박오픈'
          , '형상관리, 빌드, 배포');
          
INSERT INTO book_tbl_06
  VALUES (104
          , 'HTML'
          , '104.jpg'
          , '2018/04/04'
          , 10000
          , '홍길동'
          , 'HTML/CSS/JAVASCRIPT/JQUERY');
          
INSERT INTO rent_tbl_06
  VALUES (10001
          , 100
          , 2400
          , '2018/07/02'
          , '1');

INSERT INTO rent_tbl_06
  VALUES (10002
          , 101
          , 2000
          , '2018/07/04'
          , '1');

INSERT INTO rent_tbl_06
  VALUES (10003
          , 100
          , 2400
          , '2018/08/02'
          , '1');

INSERT INTO rent_tbl_06
  VALUES (10004
          , 100
          , 2400
          , '2018/08/12'
          , '1');

INSERT INTO rent_tbl_06
  VALUES (10005
          , 102
          , 2500
          , '2018/08/13'
          , '1');

INSERT INTO rent_tbl_06
  VALUES (10006
          , 103
          , 3000
          , '2018/08/13'
          , '1');

INSERT INTO rent_tbl_06
  VALUES (10007
          , 103
          , 3000
          , '2018/08/20'
          , '0');

INSERT INTO rent_tbl_06
  VALUES (10008
          , 100
          , 2400
          , '2018/09/03'
          , '1');

INSERT INTO rent_tbl_06
  VALUES (10009
          , 100
          , 2400
          , '2018/09/08'
          , '1');

INSERT INTO rent_tbl_06
  VALUES (10010
          , 100
          , 2400
          , '2018/09/14'
          , '0');

INSERT INTO rent_tbl_06
  VALUES (10011
          , 102
          , 2500
          , '2018/09/14'
          , '0');

COMMIT;


