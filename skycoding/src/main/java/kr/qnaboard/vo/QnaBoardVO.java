package kr.qnaboard.vo;

import java.sql.Date;

public class QnaBoardVO {
	private int qna_id;
	private int mem_num;
	private String qna_title;
	private String qna_content;
	private Date qna_reg_date;
	private Date qna_modify_date;
	private int qna_hit;
	private String qna_photo;

	//컬럼에는 없는데 사용할 거라 넣음
	private String mem_id; //hmember 속성(아이디)
	private String photo; //hmember_detail 속성(프사)

	public int getQna_id() {
		return qna_id;
	}

	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public Date getQna_reg_date() {
		return qna_reg_date;
	}

	public void setQna_reg_date(Date qna_reg_date) {
		this.qna_reg_date = qna_reg_date;
	}

	public Date getQna_modify_date() {
		return qna_modify_date;
	}

	public void setQna_modify_date(Date qna_modify_date) {
		this.qna_modify_date = qna_modify_date;
	}

	public int getQna_hit() {
		return qna_hit;
	}

	public void setQna_hit(int qna_hit) {
		this.qna_hit = qna_hit;
	}

	public String getQna_photo() {
		return qna_photo;
	}

	public void setQna_photo(String qna_photo) {
		this.qna_photo = qna_photo;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
