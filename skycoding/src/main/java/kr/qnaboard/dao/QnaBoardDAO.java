package kr.qnaboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.qnaboard.vo.QnaBoardReplyVO;
import kr.qnaboard.vo.QnaBoardVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
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
				QnaBoardVO qnaBoard = new QnaBoardVO();
				qnaBoard.setQna_id(rs.getInt("qna_id"));
				qnaBoard.setQna_title(StringUtil.useNoHtml(rs.getString("qna_title")));
				qnaBoard.setQna_hit(rs.getInt("qna_hit"));
				qnaBoard.setQna_reg_date(rs.getDate("qna_reg_date"));
				qnaBoard.setMem_id(rs.getString("mem_id"));
				
				list.add(qnaBoard);
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
		QnaBoardVO qnaBoard = null;
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
				qnaBoard = new QnaBoardVO();
				qnaBoard.setQna_id(rs.getInt("qna_id")); //컬럼명이라서 문자열로 작성!
				qnaBoard.setQna_title(rs.getString("qna_title"));
				qnaBoard.setQna_content(rs.getString("qna_content"));
				qnaBoard.setQna_hit(rs.getInt("qna_hit"));
				qnaBoard.setQna_reg_date(rs.getDate("qna_reg_date"));
				qnaBoard.setQna_modify_date(rs.getDate("qna_modify_date"));
				qnaBoard.setQna_photo(rs.getString("qna_photo"));
				qnaBoard.setMem_num(rs.getInt("mem_num"));
				qnaBoard.setMem_id(rs.getString("mem_id"));
				qnaBoard.setPhoto(rs.getString("photo"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return qnaBoard;
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
	//파일 삭제
	public void deleteFile(int qna_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성			//빈 문자열을 대입하면 원래있던 데이터를 없애버린다.
			sql = "UPDATE qna_detail SET qna_photo='' WHERE qna_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, qna_id);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글수정
	public void updateBoard(QnaBoardVO qnaBoard)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0; //if문이 있는 데이터 바인딩 시 사용

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//파일 업로드 할 때는 대체가 되지만 안 할 때는 기존 정보 그대로 둬야해서 sql문이 2개.
			//제목이랑 내용은 미리보기가 있어서 그대로 기존데이터가 넘어가는데 파일은 선택하고 안 하고의 차이가 있음

			//전송된 파일 여부 체크
			if(qnaBoard.getQna_photo()!=null) { //수정폼에서 새로운 파일을 업로드했을 경우
				sub_sql += ",qna_photo=?";
			}

			//SQL문 작성
			sql = "UPDATE qna_detail SET qna_title=?,qna_content=?,qna_modify_date=SYSDATE" + sub_sql + 
					" WHERE qna_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt,qnaBoard.getQna_title());
			pstmt.setString(++cnt, qnaBoard.getQna_content());
			if(qnaBoard.getQna_photo()!=null) {
				pstmt.setString(++cnt, qnaBoard.getQna_photo());
			}
			pstmt.setInt(++cnt, qnaBoard.getQna_id()); //if문이 실행 안 되면 Qna_id가 4번이 아니라 3번이라 이런식으로 처리 

			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글삭제(이게 부모글, 댓글이 자식글. 자식글 있을 때 부모글을 지울 수 없도록 설정(FK로))
	public void deleteBoard(int qna_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			//오토커밋 해제
			conn.setAutoCommit(false);

			/* 댓글 만들고 주석 풀 예정
			//댓글 삭제
			sql = "DELETE FROM qnaComment WHERE qna_id=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, qna_id);
			pstmt2.executeUpdate();
			 */

			//부모글 삭제(제약조건 때문에 자식글 먼저 삭제해야 함)
			sql = "DELETE FROM qna_detail WHERE qna_id=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, qna_id);
			pstmt2.executeUpdate();

			//예외 발생 없이 정상적으로 SQL문이 실행
			conn.commit();

		}catch(Exception e) {
			//예외 발생
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	
	//댓글 등록
	public void insertReplyBoard(QnaBoardReplyVO qnaBoardReply)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO qnaComment (qnaComm_id,qnaComm_content,mem_num,qna_id) "
					+ "VALUES(qnaComment_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaBoardReply.getQnaComm_content());
			pstmt.setInt(2, qnaBoardReply.getMem_num());
			pstmt.setInt(3, qnaBoardReply.getQna_id());

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 개수
	public int getReplyBoardCount(int qna_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM qnaComment q JOIN hmember m ON q.mem_num=m.mem_num "
				+ "WHERE q.qna_id=?"; //댓글은 부모글 밑에 딸려있으니까 부모글의 넘버를 구함
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_id);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//댓글 목록
	public List<QnaBoardReplyVO> getListReplyBoard(int start,int end, int qna_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnaBoardReplyVO> list = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM qnaComment q JOIN "
					+ "hmember m USING(mem_num) WHERE "
					+ "q.qna_id=? ORDER BY q.qnaComm_id DESC)a) "
					+ "WHERE rnum>=? AND rnum<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			list = new ArrayList<QnaBoardReplyVO>();
			while(rs.next()) {
				QnaBoardReplyVO reply = new QnaBoardReplyVO();
				reply.setQnaComm_id(rs.getInt("qnaComm_id"));
				reply.setQnaComm_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("qnaComm_reg_date")));
				reply.setQnaComm_content(StringUtil.useBrNoHtml(rs.getString("qnaComm_content")));
				reply.setQna_id(rs.getInt("qna_id"));
				reply.setMem_num(rs.getInt("mem_num"));
				reply.setMem_id(rs.getString("mem_id"));

				list.add(reply); //행이 여러 개니까 생성한 자바빈을 list에 넣음
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
}





