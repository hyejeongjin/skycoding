package kr.jobReview.vo;

import java.sql.Date;

public class ReviewVO {
	private int rev_id;
	private String rev_title;
	private String rev_content;
	private String rev_photo;
	private Date rev_reg_date;
	private Date rev_modify_date;
	private int rev_hit;
	private int mem_num;
	
	private String mem_id;
	private String mem_photo;
	
	//컬럼에는 없는데 이전글,다음글 처리하려고 넣음
	private int next;
	private String next_title;
	private int prev;
	private String prev_title;

	
	
	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public String getNext_title() {
		return next_title;
	}

	public void setNext_title(String next_title) {
		this.next_title = next_title;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public String getPrev_title() {
		return prev_title;
	}

	public void setPrev_title(String prev_title) {
		this.prev_title = prev_title;
	}
	
	
	public int getRev_id() {
		return rev_id;
	}
	public void setRev_id(int rev_id) {
		this.rev_id = rev_id;
	}
	public String getRev_title() {
		return rev_title;
	}
	public void setRev_title(String rev_title) {
		this.rev_title = rev_title;
	}
	public String getRev_content() {
		return rev_content;
	}
	public void setRev_content(String rev_content) {
		this.rev_content = rev_content;
	}
	public String getRev_photo() {
		return rev_photo;
	}
	public void setRev_photo(String rev_photo) {
		this.rev_photo = rev_photo;
	}
	public Date getRev_reg_date() {
		return rev_reg_date;
	}
	public void setRev_reg_date(Date rev_reg_date) {
		this.rev_reg_date = rev_reg_date;
	}
	public Date getRev_modify_date() {
		return rev_modify_date;
	}
	public void setRev_modify_date(Date rev_modify_date) {
		this.rev_modify_date = rev_modify_date;
	}
	public int getRev_hit() {
		return rev_hit;
	}
	public void setRev_hit(int rev_hit) {
		this.rev_hit = rev_hit;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
	
}
