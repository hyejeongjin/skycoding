package kr.mypage.vo;

import java.sql.Date;

public class MypageVO {
	private int mem_num;
	private String mem_id;
	private int mem_auth;
	private String mem_name;
	private String mem_pw;
	private String mem_cell;
	private String mem_email;
	private Date mem_reg_date;
	private Date mem_modify_date;
	private String mem_photo;
	
	//비밀번호 일치 여부 체크
	public boolean isCheckedPassword(String userPasswd) {
		//회원 등급(auth) : 0탈퇴회원,1정지회원,2일반회원,9관리자
		if(mem_auth >= 1 && mem_pw.equals(userPasswd)) {
			//비밀번호 일치
			return true;
		}
		//비밀번호 불일치
		return false;
	}
	
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return mem_id;
	}
	public void setId(String id) {
		this.mem_id = id;
	}
	public int getAuth() {
		return mem_auth;
	}
	public void setAuth(int auth) {
		this.mem_auth = auth;
	}
	public String getName() {
		return mem_name;
	}
	public void setName(String name) {
		this.mem_name = name;
	}
	public String getPasswd() {
		return mem_pw;
	}
	public void setPasswd(String passwd) {
		this.mem_pw = passwd;
	}
	public String getEmail() {
		return mem_email;
	}
	public void setEmail(String email) {
		this.mem_email = email;
	}
	public String getPhone() {
		return mem_cell;
	}
	public void setPhone(String phone) {
		this.mem_cell = phone;
	}
	/*
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	*/
	public String getPhoto() {
		return mem_photo;
	}
	public void setPhoto(String photo) {
		this.mem_photo = photo;
	}
	public Date getReg_date() {
		return mem_reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.mem_reg_date = reg_date;
	}
	public Date getModify_date() {
		return mem_modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.mem_modify_date = modify_date;
	}
}
