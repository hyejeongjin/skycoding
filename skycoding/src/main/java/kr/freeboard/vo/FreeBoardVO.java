package kr.freeboard.vo;

import java.sql.Date;

public class FreeBoardVO {
	private int free_id;
	private int mem_num; //hmember 속성(회원번호)
	private String free_title;
	private String free_content;
	private Date free_reg_date;
	private Date free_modify_date;
	private int free_hit;
	private String free_photo;
	
	//컬럼에는 없는데 사용하려고 넣음
	private String mem_id; //hmember 속성(아이디)
	private String mem_photo; //hmember_detail 속성(프사)
	
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

	public int getFree_id() {
		return free_id;
	}

	public void setFree_id(int free_id) {
		this.free_id = free_id;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getFree_title() {
		return free_title;
	}

	public void setFree_title(String free_title) {
		this.free_title = free_title;
	}

	public String getFree_content() {
		return free_content;
	}

	public void setFree_content(String free_content) {
		this.free_content = free_content;
	}

	public Date getFree_reg_date() {
		return free_reg_date;
	}

	public void setFree_reg_date(Date free_reg_date) {
		this.free_reg_date = free_reg_date;
	}

	public Date getFree_modify_date() {
		return free_modify_date;
	}

	public void setFree_modify_date(Date free_modify_date) {
		this.free_modify_date = free_modify_date;
	}

	public int getFree_hit() {
		return free_hit;
	}

	public void setFree_hit(int free_hit) {
		this.free_hit = free_hit;
	}

	public String getFree_photo() {
		return free_photo;
	}

	public void setFree_photo(String free_photo) {
		this.free_photo = free_photo;
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
	