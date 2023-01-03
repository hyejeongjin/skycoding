-- free_detail Table Create SQL
CREATE TABLE free_detail 
( 
    free_id     NUMBER	NOT NULL,
	free_title	VARCHAR2(150)	NOT NULL,
	free_content	CLOB	NOT NULL,
	free_reg_date	DATE    DEFAULT SYSDATE	    NOT NULL,
	free_modify_date	DATE,
	free_hit	NUMBER	NOT NULL,
	free_photo	VARCHAR2(2)	NOT NULL,
	
    CONSTRAINT PK_free_detail PRIMARY KEY (free_id),
    CONSTRAINT FK_free_detail FOREIGN KEY (free_id) REFERENCES hmember (mem_num)
);

CREATE SEQUENCE free_seq;

-- freeComment Table Create SQL
CREATE TABLE freeComment 
( 
    freeComm_id     NUMBER	NOT NULL,
    free_id     NUMBER	NOT NULL,
    freeComm_content	VARCHAR2(150)	NOT NULL,
    freeComm_reg_date	DATE	DEFAULT SYSDATE NOT NULL,
    CONSTRAINT PK_freeComment PRIMARY KEY (freeComm_id),
    CONSTRAINT FK_freeComment_mem_num FOREIGN KEY (mem_num) REFERENCES hmember (mem_num),
    CONSTRAINT FK_freeComment_free_id FOREIGN KEY (free_id) REFERENCES free_seq (free_id)
);