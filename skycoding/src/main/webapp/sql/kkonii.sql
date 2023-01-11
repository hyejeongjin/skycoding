--이벤트 관리페이지
CREATE TABLE EVENT(
    event_id number,
    mem_num number not null,
    event_course_id number not null,
    event_attr number(1) not null,
    event_deadline varchar2(10) not null,
    event_reg_date date default SYSDATE not null,
    event_hit number(5) default 0 not null,
    event_photo varchar2(150) not null,
    event_content varchar2(4000) not null,
    event_detail_content varchar2(4000) not null,
    constraint event_pk primary key (event_id),
    constraint event_fk1 foreign key (mem_num) references HMEMBER (mem_num),
    constraint event_fk2 foreign key (event_course_id) references COURSE (course_id)
);

--event_id 시퀀스 
CREATE SEQUENCE EVENT_SEQ;