package kr.qnaboard.dao;

public class QnaBoardDAO {
	//싱글턴 패턴
	private static QnaBoardDAO instance = new QnaBoardDAO();
		
	public static QnaBoardDAO getInstance() {
		return instance;
	}
	private QnaBoardDAO() {}
	
	//글등록
}
