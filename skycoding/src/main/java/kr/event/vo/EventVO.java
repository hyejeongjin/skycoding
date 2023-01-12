package kr.event.vo;

import java.sql.Date;

public class EventVO {
	private int event_id;
	private int mem_num;
	private int event_course_id;
	private int event_attr;
	private String event_deadline;
	private Date event_reg_date;
	private int event_hit;
	private String event_photo;
	private String event_content;
	private String event_detail_content;
	private String event_course_name;
	
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getEvent_course_id() {
		return event_course_id;
	}
	public void setEvent_course_id(int event_course_id) {
		this.event_course_id = event_course_id;
	}
	public int getEvent_attr() {
		return event_attr;
	}
	public void setEvent_attr(int event_attr) {
		this.event_attr = event_attr;
	}
	public String getEvent_deadline() {
		return event_deadline;
	}
	public void setEvent_deadline(String event_deadline) {
		this.event_deadline = event_deadline;
	}
	public Date getEvent_reg_date() {
		return event_reg_date;
	}
	public void setEvent_reg_date(Date event_reg_date) {
		this.event_reg_date = event_reg_date;
	}
	public int getEvent_hit() {
		return event_hit;
	}
	public void setEvent_hit(int event_hit) {
		this.event_hit = event_hit;
	}
	public String getEvent_photo() {
		return event_photo;
	}
	public void setEvent_photo(String event_photo) {
		this.event_photo = event_photo;
	}
	public String getEvent_content() {
		return event_content;
	}
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	public String getEvent_detail_content() {
		return event_detail_content;
	}
	public void setEvent_detail_content(String event_detail_content) {
		this.event_detail_content = event_detail_content;
	}
	public String getEvent_course_name() {
		return event_course_name;
	}
	public void setEvent_course_name(String event_course_name) {
		this.event_course_name = event_course_name;
	}
}