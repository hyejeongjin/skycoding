--이벤트 관리페이지
CREATE TABLE EVENT(
    event_id number,
    mem_num number not null,
    event_attr number(1) not null,
    event_deadline varchar2(10) not null,
    event_hit number not null,
    event_content varchar2(4000) not null,
    event_photo varchar2(150) not null,
    constraint event_pk primary key (event_id),
    constraint event_fk foreign key (mem_num) references MEMBER (mem_num)
);

create sequence event_seq;

--이벤트 상세 페이지
CREATE TABLE EVENT_DETAIL(
	event_id number,
	course_id number not null,
	event_detail_content varchar2(4000) not null,
	constraint event_detail_pk primary key (event_id),
	constraint event_detail_fk foreign key (event_id) REFERENCES EVENT(event_id),
	constraint event_detail_fk2 foreign key (course_id) REFERENCES COURSE (course_id)
);