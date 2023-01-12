package kr.freeboard.vo;

public class FreeBoardReplyVO {
	private int freeComm_id;
	private String freeComm_content;
	private String freeComm_reg_date;
	private int free_id;
	private int mem_num;
	private String mem_id; //댓글 테이블에는 없고 hmember 테이블에 있음
	
	public int getFreeComm_id() {
		return freeComm_id;
	}
	public void setFreeComm_id(int freeComm_id) {
		this.freeComm_id = freeComm_id;
	}
	public String getFreeComm_content() {
		return freeComm_content;
	}
	public void setFreeComm_content(String freeComm_content) {
		this.freeComm_content = freeComm_content;
	}
	public String getFreeComm_reg_date() {
		return freeComm_reg_date;
	}
	public void setFreeComm_reg_date(String freeComm_reg_date) {
		this.freeComm_reg_date = freeComm_reg_date;
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
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	
}