-- free_detail Table Create SQL
CREATE TABLE free_detail 
( 
	mem_num	NUMBER	NOT NULL,
    free_id     NUMBER	NOT NULL,
	free_title	VARCHAR2(150)	NOT NULL,
	free_content	CLOB	NOT NULL,
	free_reg_date	DATE    DEFAULT SYSDATE	    NOT NULL,
	free_modify_date	DATE,
	free_hit	NUMBER	DEFAULT 0 NOT NULL,
	free_photo	VARCHAR2(150),
	free_status NUMBER NOT NULL,
	
    CONSTRAINT PK_free_detail PRIMARY KEY (free_id),
    CONSTRAINT FK_free_detail FOREIGN KEY (mem_num) REFERENCES hmember (mem_num)
);

CREATE SEQUENCE free_detail_seq;

-- freeComment Table Create SQL
CREATE TABLE freeComment
( 
	mem_num	NUMBER	NOT NULL,
    freeComm_id     NUMBER	NOT NULL,
    free_id     NUMBER	NOT NULL,
    freeComm_content	VARCHAR2(150)	NOT NULL,
    freeComm_reg_date	DATE	DEFAULT SYSDATE NOT NULL,
    freeComm_modify_date	DATE,
    CONSTRAINT PK_freeComment PRIMARY KEY (freeComm_id),
    CONSTRAINT FK_freeComment_mem_num FOREIGN KEY (mem_num) REFERENCES hmember (mem_num),
    CONSTRAINT FK_freeComment_free_id FOREIGN KEY (free_id) REFERENCES free_detail (free_id)
);

CREATE SEQUENCE freeComment_seq;

--학습중강좌
create table course_cart(
 cart_num number,
 course_id number not null,
 reg_date date default sysdate not null,
 mem_num number not null,
 constraint cart_pk primary key (cart_num),
 constraint cart_item_fk1 foreign key (course_id)
                          references course (course_id),
 constraint cart_item_fk2 foreign key (mem_num)
                          references hmember (mem_num)
);
create sequence course_cart_seq;