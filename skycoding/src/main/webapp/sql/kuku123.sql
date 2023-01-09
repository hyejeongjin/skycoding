CREATE TABLE news (

	news_id	number(8)		NOT NULL,
	news_attr	number(1)	DEFAULT 1	NOT NULL,
	news_title	varchar2(150)		NOT NULL,
	news_content	clob		NOT NULL,
	news_photo	varchar2(150)		NULL,
	news_hit	number default 0		NOT NULL,
	news_reg_date	date DEFAULT SYSDATE	NOT NULL,
	news_modify_date	date NULL,
    constraint news_pk primary key (news_id)
);
create sequence news_seq;