create table job_review (
	rev_id number(10) not null,
	mem_num	number not null,
	rev_title varchar2(150)	not null,
	rev_content clob not null,
	rev_photo varchar2(150),
	rev_hit number(10) not null,
	rev_reg_date date default sysdate not null,
	rev_modify_date date,
	constraint job_review_pk primary key(rev_id),
	constraint job_review_fk foreign key(mem_num) references hmember(mem_num)
);
create sequence job_review_seq;

create table job_employ (
	emp_id number(10) not null,
	mem_num	number not null,
	emp_title varchar2(150)	not null,
	emp_content	clob not null,
	emp_photo varchar2(150),
	emp_hit number(10) not null,
	emp_reg_date date default sysdate not null,
	emp_modify_date date,
	constraint job_employ_pk primary key(emp_id),
	constraint job_employ_fk foreign key(mem_num) references hmember(mem_num)
);
create sequence job_employ_seq;

create table job_review_comment (
	com_id number(10) not null,
	mem_num	number not null,
	rev_id number(10) not null,
	com_content varchar(150) not null,
	com_reg_date date default sysdate not null,
	com_modify_date date,
	constraint job_review_comment_pk primary key(com_id),
	constraint job_review_comment_fk foreign key(mem_num) references hmember(mem_num),
	constraint job_review_comment_fk2 foreign key(rev_id) references job_review(rev_id)
);
create sequence job_review_comment_seq;