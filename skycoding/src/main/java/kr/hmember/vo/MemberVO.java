package kr.hmember.vo;

import java.sql.Date;

public class MemberVO {
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	private String mem_name;
	private String mem_pw;
	private int mem_pwq;
	private String mem_pwa;
	private String mem_cell;
	private Date mem_reg_date;
	private Date mem_modify_date;
	private String mem_photo;


	//비밀번호 일치 여부 체크
	public boolean isCheckedPassword(String userPasswd) {
		//회원 등급(mem_auth): 0-탈퇴회원, 1-일반회원, 9-관리자
		if(mem_auth > 0 && mem_pw.equals(userPasswd)) {
			//비밀번호 일치 시
			return true;
		}
		return false;
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
	public int getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public int getMem_pwq() {
		return mem_pwq;
	}
	public void setMem_pwq(int mem_pwq) {
		this.mem_pwq = mem_pwq;
	}
	public String getMem_pwa() {
		return mem_pwa;
	}
	public void setMem_pwa(String mem_pwa) {
		this.mem_pwa = mem_pwa;
	}
	public String getMem_cell() {
		return mem_cell;
	}
	public void setMem_cell(String mem_cell) {
		this.mem_cell = mem_cell;
	}
	public Date getMem_reg_date() {
		return mem_reg_date;
	}
	public void setMem_reg_date(Date mem_reg_date) {
		this.mem_reg_date = mem_reg_date;
	}
	public Date getMem_modify_date() {
		return mem_modify_date;
	}
	public void setMem_modify_date(Date mem_modify_date) {
		this.mem_modify_date = mem_modify_date;
	}
	public String getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(String mem_photo) {
		this.mem_photo = mem_photo;
	}
}
