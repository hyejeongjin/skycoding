package kr.qnaboard.vo;

public class QnaBoardReplyVO {
	private int qnaComm_id;
	private String qnaComm_content;
	private String qnaComm_reg_date;
	private int qna_id;
	private int mem_num;
	private String mem_id; //댓글 테이블에는 없고 hmember 테이블에 있음
	
	public int getQnaComm_id() {
		return qnaComm_id;
	}
	public void setQnaComm_id(int qnaComm_id) {
		this.qnaComm_id = qnaComm_id;
	}
	public String getQnaComm_content() {
		return qnaComm_content;
	}
	public void setQnaComm_content(String qnaComm_content) {
		this.qnaComm_content = qnaComm_content;
	}
	public String getQnaComm_reg_date() {
		return qnaComm_reg_date;
	}
	public void setQnaComm_reg_date(String qnaComm_reg_date) {
		this.qnaComm_reg_date = qnaComm_reg_date;
	}
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
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
}