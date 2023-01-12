package kr.course.vo;

import java.sql.Date;

public class CourseVO {
	//컬럼명과 일치하게 만들어주기 , 데이터와 맵핑
	private int course_id;
	private int mem_num;
	private String course_name;
	private int course_hit;
	private String course_content;
	private Date report_date;
	private String course_tr;
	private int course_cate;
	private String course_photo;
	private int like_count;
	
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
	public int getCourse_hit() {
		return course_hit;
	}
	public void setCourse_hit(int course_hit) {
		this.course_hit = course_hit;
	}
	public String getCourse_content() {
		return course_content;
	}
	public void setCourse_content(String course_content) {
		this.course_content = course_content;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public String getCourse_tr() {
		return course_tr;
	}
	public void setCourse_tr(String course_tr) {
		this.course_tr = course_tr;
	}
	public int getCourse_cate() {
		return course_cate;
	}
	public void setCourse_cate(int course_cate) {
		this.course_cate = course_cate;
	}
	public String getCourse_photo() {
		return course_photo;
	}
	public void setCourse_photo(String course_photo) {
		this.course_photo = course_photo;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	
}

