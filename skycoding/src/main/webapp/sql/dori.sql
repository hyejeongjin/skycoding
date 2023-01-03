-- 강의
create table course(
	course_id number not null,
	mem_num number not null,
	course_name varchar2(30) not null,
	course_hit number not null,
	course_content clob not null,
	report_date date default sysdate not null,
	course_tr varchar2(20) not null,
	/*카테고리 변수: 0JAVA,1DB,2HTML,3JS*/
	course_cate number not null,
	course_photo varchar2(150) not null,
	constraint course_pk primary key (mem_id),
	constraint course_fk foreign key (mem_num)
	                     references hmember (mem_num)
	
	
);

create sequence course_seq;

--강의 좋아요
create table course_like(
 like_num number not null,
 course_id number not null,
 mem_num number not null,
 constraint course_like_pk primary key (like_num),
 constraint course_like_fk1 foreign key (course_id)
                           references course (course_id),
 constraint course_like_fk2 foreign key (mem_num)
                           references hmember (mem_num)
);

create sequence courselike_seq;