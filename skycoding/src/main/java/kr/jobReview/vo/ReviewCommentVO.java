package kr.jobReview.vo;

import java.sql.Date;

public class ReviewCommentVO {
	private int com_id;
	private String com_content;
	private String com_reg_date;
	private String com_modify_date;
	private int mem_num;
	private String mem_id;
	private int rev_id;
	
	
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public String getCom_content() {
		return com_content;
	}
	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}
	public String getCom_reg_date() {
		return com_reg_date;
	}
	public void setCom_reg_date(String com_reg_date) {
		this.com_reg_date = com_reg_date;
	}
	public String getCom_modify_date() {
		return com_modify_date;
	}
	public void setCom_modify_date(String com_modify_date) {
		this.com_modify_date = com_modify_date;
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
	public int getRev_id() {
		return rev_id;
	}
	public void setRev_id(int rev_id) {
		this.rev_id = rev_id;
	}
	
}
