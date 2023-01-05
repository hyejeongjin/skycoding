package kr.qnaboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.qnaboard.vo.QnaBoardVO;
import kr.util.DBUtil;

public class QnaBoardDAO {
	//싱글턴 패턴
	private static QnaBoardDAO instance = new QnaBoardDAO();
		
	public static QnaBoardDAO getInstance() {
		return instance;
	}
	private QnaBoardDAO() {}
			 
	//글등록
	public void insertBoard(QnaBoardVO qnaboard) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO qna_detail (qna_id,qna_title,qna_content,qna_photo,mem_num) "
					+ "VALUES (qna_detail_seq.nextval,?,?,?,?)";
			//PreparedStatement객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, qnaboard.getQna_title());
			pstmt.setString(2, qnaboard.getQna_content());
			pstmt.setString(3, qnaboard.getQna_photo());
			pstmt.setInt(4, qnaboard.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
