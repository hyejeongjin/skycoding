package kr.mypage.vo;

import java.sql.Date;

public class MycourselikeVO {
	private int course_id;
	private int mem_num;
	private String course_name;
	private Date report_date;
	private String course_photo;
	
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public String getCourse_photo() {
		return course_photo;
	}
	public void setCourse_photo(String course_photo) {
		this.course_photo = course_photo;
	}
}
