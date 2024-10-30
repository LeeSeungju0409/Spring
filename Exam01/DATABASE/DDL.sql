CREATE TABLE book_tbl_06 ( book_no          NUMBER          NOT NULL    primary key
                         , book_name        VARCHAR2(50)    NOT NULL
                         , book_coverimg    VARCHAR2(20)
                         , book_date        DATE
                         , book_price       NUMBER
                         , book_publisher   VARCHAR2(50)
                         , book_info        VARCHAR2(2000)
                         );



INSERT INTO book_tbl_06 (book_no
                         , book_name
                         , book_coverimg
                         , book_date
                         , book_price
                         , book_publisher
                         , book_info
                         )
VALUES                  ( 100
                        , '������'
                        , '100.jpg'
                        , '15/09/02'
                        , 24000
                        , '���轺'
                        , '�ü��, DB����,��Ʈ��ũ����, ����ȯ�汸��'
                        );

INSERT INTO book_tbl_06 (book_no
                         , book_name
                         , book_coverimg
                         , book_date
                         , book_price
                         , book_publisher
                         , book_info
                         )
VALUES                  ( 101
                        , '�ڹ�'
                        , '101.jpg'
                        , '16/01/10'
                        , 20000
                        , '���ڹ�'
                        , '���α׷��־��'
                        );
                        
INSERT INTO book_tbl_06 (book_no
                         , book_name
                         , book_coverimg
                         , book_date
                         , book_price
                         , book_publisher
                         , book_info
                         )
VALUES                  ( 102
                        , '�ڹ������α׷���'
                        , '102.jpg'
                        , '16/10/30'
                        , 25000
                        , '������'
                        , '����ȯ��/�������α׷�/��ġ���α׷�'
                        );
                        
INSERT INTO book_tbl_06 (book_no
                         , book_name
                         , book_coverimg
                         , book_date
                         , book_price
                         , book_publisher
                         , book_info
                         )
VALUES                  ( 103
                        , '���¼ҽ�Ȱ���ϱ�'
                        , '103.jpg'
                        , '17/09/01'
                        , 30000
                        , '�ڿ���'
                        , '�������, ����, ����'
                        );
                        
INSERT INTO book_tbl_06 (book_no
                         , book_name
                         , book_coverimg
                         , book_date
                         , book_price
                         , book_publisher
                         , book_info
                         )
VALUES                  ( 104
                        , 'HTML'
                        , '104.jpg'
                        , '18/04/04'
                        , 10000
                        , 'ȫ�浿'
                        , 'HTML/CSS/JAVASCRIPT/JQUERY'
                        );      
                        
                        
UPDATE book_tbl_06
SET book_info = '���α׷��� ���'
WHERE book_no = 101;

SELECT *
FROM book_tbl_06;



CREATE TABLE rent_tbl_06( rent_no NUMBER NOT NULL PRIMARY KEY
                        , book_no NUMBER NOT NULL
                        , rent_price  NUMBER NOT NULL
                        , rent_date DATE NOT NULL
                        , rent_status CHAR(1) DEFAULT 0 NOT NULL);
                        
SELECT *
FROM rent_tbl_06;


INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10001
                        , 100
                        , 2400
                        , '18/07/02'
                        , 1
                        );
                        

INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10002
                        , 101
                        , 2000
                        , '18/07/04'
                        , 1
                        );   
                        
                        

INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10003
                        , 100
                        , 2400
                        , '18/08/02'
                        , 1
                        );
                        
                        

INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10004
                        , 100
                        , 2400
                        , '18/08/12'
                        , 1
                        );
                        
                        
                        
INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10005
                        , 102
                        , 2500
                        , '18/08/13'
                        , 1
                        );
                        
                        
INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10006
                        , 103
                        , 3000
                        , '18/08/13'
                        , 1
                        );       
                        
                        
INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10007
                        , 103
                        , 3000
                        , '18/08/20'
                        , 0
                        );         
                        
INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10008
                        , 100
                        , 2400
                        , '18/09/03'
                        , 1
                        );            
                        
INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10009
                        , 100
                        , 2400
                        , '18/09/08'
                        , 1
                        );      
                        
                        
                        
INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10010
                        , 100
                        , 2400
                        , '18/09/14'
                        , 0
                        );                          
                        
                        
INSERT INTO rent_tbl_06 ( rent_no
                        , book_no
                        , rent_price
                        , rent_date
                        , rent_status
                        ) 
VALUES                  ( 10001
                        , 102
                        , 2500
                        , '18/09/14'
                        , 0
                        );    
                        

SELECT *
FROM rent_tbl_06;

SELECT book_no
    , book_name
    , book_coverimg
    , book_date
    , book_price
    , book_publisher
    , book_info
FROM book_tbl_06
ORDER BY book_no;


SELECT rent_no
       , book_no
       , rent_price
       , rent_date
       , rent_status
FROM rent_tbl_06
ORDER BY book_no;
                        
COMMIT;