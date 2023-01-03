--회원관리 테이블
create table hmember(
 mem_num number not null,
 mem_id varchar2(12) unique not null,
 mem_auth number(1) default 1 not null,
 constraint hmember_pk primary key (mem_num)
);

--회원상세 테이블
create table hmember_detail(
 mem_num number not null,
 mem_name varchar2(30) not null,
 mem_pw varchar2(12) not null,
 mem_pwq number(1) not null,
 mem_pwa varchar2(50) not null,
 mem_cell varchar2(15) not null,
 mem_regdate date default sysdate not null,
 mem_moddate date,
 constraint hmember_detail_pk primary key (mem_num),
 constraint hmember_detail_fk foreign key (mem_num)
 							  references hmember (mem_num)
);

create sequence hmember_seq;

--비밀번호 질문 저장 테이블   
create table hmember_qna(
 mem_pwq number(1) not null,
 qna_detail varchar2(50) not null,
 constraint hmember_qna_pk primary key (mem_pwq)
);
