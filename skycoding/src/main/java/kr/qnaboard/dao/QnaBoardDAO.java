package kr.qnaboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.qnaboard.vo.QnaBoardVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

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
	
	//총 레코드 수(검색 레코드 수) //목록작업 위해 레코드 필요
public int getBoardCount(String keyfield, String keyword)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";//검색시 사용
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) { //검색할 내용이 있는 경우
				//검색글 개수
				if(keyfield.equals("1")) sub_sql += "WHERE q.qna_title LIKE ?"; //제목 검색
				else if(keyfield.equals("2")) sub_sql += "WHERE m.mem_id LIKE ?"; //작성자 검색
				else if(keyfield.equals("3")) sub_sql += "WHERE q.qna_content LIKE ?"; //내용 검색
				
			}
			
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM qna_detail q JOIN hmember m USING(mem_num) " + sub_sql;
			//PreparedStatement 객체 새성
			pstmt = conn.prepareStatement(sql);

			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, "%" + keyword + "%"); //가변길이라서 % 넣어줌
			}
			
			//SQL문 실행하고 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			if(rs.next()){ //집합함수는 결과행 하나
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//글목록(검색글 목록)
	public List<QnaBoardVO> getListBoard(int start, int end, String keyfield, String keyword)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaBoardVO> list = null;
		String sql = null;
		String sub_sql = "";//검색시 사용(null을 넣으면 나중에 null이 그대로 출력돼서 빈문자열로)
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) { //검색할 내용이 있는 경우
				//검색글 개수
				if(keyfield.equals("1")) sub_sql += "WHERE q.qna_title LIKE ?"; //제목 검색
				else if(keyfield.equals("2")) sub_sql += "WHERE m.mem_id LIKE ?"; //작성자 검색
				else if(keyfield.equals("3")) sub_sql += "WHERE q.qna_content LIKE ?"; //내용 검색
				
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM qna_detail q JOIN "
					+ "hmember m USING(mem_num) " + sub_sql + " ORDER BY q.qna_id DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%" + keyword + "%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문을 실행해서 결과행들을 ResultSet에 담음
			rs = pstmt.executeQuery();
			list = new ArrayList<QnaBoardVO>();
			while(rs.next()) {
				QnaBoardVO board = new QnaBoardVO();
				board.setQna_id(rs.getInt("qna_id"));
				board.setQna_title(StringUtil.useNoHtml(rs.getString("qna_title")));
				board.setQna_hit(rs.getInt("qna_hit"));
				board.setQna_reg_date(rs.getDate("qna_reg_date"));
				board.setMem_id(rs.getString("mem_id"));
				
				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//글상세
		public QnaBoardVO getBoard(int qna_id) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			QnaBoardVO board = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM qna_detail q JOIN hmember m USING(mem_num) JOIN hmember_detail d "
						+ "USING(mem_num) WHERE q.qna_id=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, qna_id);
				//SQL문을 실행해서 결과행을 ResultSet에 담음
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					board = new QnaBoardVO();
					board.setQna_id(rs.getInt("qna_id")); //컬럼명이라서 문자열로 작성!
					board.setQna_title(rs.getString("qna_title"));
					board.setQna_content(rs.getString("qna_content"));
					board.setQna_hit(rs.getInt("qna_hit"));
					board.setQna_reg_date(rs.getDate("qna_reg_date"));
					board.setQna_modify_date(rs.getDate("qna_modify_date"));
					board.setQna_photo(rs.getString("qna_photo"));
					board.setMem_num(rs.getInt("mem_num"));
					board.setMem_id(rs.getString("mem_id"));
					board.setPhoto(rs.getString("photo"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return board;
		}
		
		//조회수 증가
		public void updateReadcount(int qna_id) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;

			try {
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE qna_detail SET qna_hit=qna_hit+1 WHERE qna_id=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, qna_id);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
}





