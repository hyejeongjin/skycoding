--커뮤니티 질문글 상세
create table qna_detail(
 qna_id number not null,
 mem_num number not null,
 qna_title varchar2(150) not null,
 qna_content clob not null,
 qna_reg_date date default sysdate not null,
 qna_modify_date date,
 qna_hit number default 0 not null,
 qna_photo varchar2(150),
 constraint qna_detail_pk primary key (qna_id),
 constraint qna_detail_fk foreign key (mem_num) references hmember (mem_num)
);
create sequence qna_detail_seq;
  
--커뮤니티 질문글 댓글
create table qnaComment(
 qnaComm_id number not null,
 mem_num number not null,
 qna_id number not null,
 qnaComm_content varchar2(150) not null,
 qnaComm_reg_date date default sysdate not null,
 qnaComm_modify_date date,
 constraint qnaComment_pk primary key (qnaComm_id),
 constraint qnaComment_fk foreign key (qna_id) references qna_detail (qna_id),
 constraint qnaComment_fk2 foreign key (mem_num) references hmember (mem_num)
);
create sequence qnaComment_seq;