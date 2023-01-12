package kr.freeboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.freeboard.vo.FreeBoardVO;
import kr.freeboard.vo.FreeBoardReplyVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class FreeBoardDAO {
	//싱글턴 패턴
	private static FreeBoardDAO instance = new FreeBoardDAO();
		
	public static FreeBoardDAO getInstance() {
		return instance;
	}
	private FreeBoardDAO() {}
			 
	//글등록
	public void insertBoard(FreeBoardVO freeboard) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO free_detail (free_id,free_title,free_content,free_photo,mem_num) "
					+ "VALUES (free_detail_seq.nextval,?,?,?,?)";
			//PreparedStatement객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, freeboard.getFree_title());
			pstmt.setString(2, freeboard.getFree_content());
			pstmt.setString(3, freeboard.getFree_photo());
			pstmt.setInt(4, freeboard.getMem_num());
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
				if(keyfield.equals("1")) sub_sql += "WHERE f.free_title LIKE ?"; //제목 검색
				else if(keyfield.equals("2")) sub_sql += "WHERE m.mem_id LIKE ?"; //작성자 검색
				else if(keyfield.equals("3")) sub_sql += "WHERE f.free_content LIKE ?"; //내용 검색
				
			}
			
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM free_detail f JOIN hmember m USING(mem_num) " + sub_sql;
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
	public List<FreeBoardVO> getListBoard(int start, int end, String keyfield, String keyword,
			int free_cate, String sort)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FreeBoardVO> list = null;
		String sql = null;
		String sub_sql = "";//검색시 사용(null을 넣으면 나중에 null이 그대로 출력돼서 빈문자열로)
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) { //검색할 내용이 있는 경우
				//검색글 개수
				if(keyfield.equals("1")) sub_sql += "WHERE f.free_title LIKE ?"; //제목 검색
				else if(keyfield.equals("2")) sub_sql += "WHERE m.mem_id LIKE ?"; //작성자 검색
				else if(keyfield.equals("3")) sub_sql += "WHERE f.free_content LIKE ?"; //내용 검색
				
			}
			
			//dropdown if에 sort 변수 추가
			if(sort.equals("1")) {
				sort = "free_id DESC";
			}else if(sort.equals("2")) {
				sort = "free_hit DESC";
			}else {
				sort = "free_id DESC";
			}
			
			//전체/검색 강의 보기
			//dropdown orderby 옆에 sort 변수 추가
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM free_detail f JOIN "
					+ "hmember m USING(mem_num) " + sub_sql + " ORDER BY " +sort+")a) "
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
			list = new ArrayList<FreeBoardVO>();
			while(rs.next()) {
				FreeBoardVO freeBoard = new FreeBoardVO();
				freeBoard.setFree_id(rs.getInt("free_id"));
				freeBoard.setFree_title(StringUtil.useNoHtml(rs.getString("free_title")));
				freeBoard.setFree_hit(rs.getInt("free_hit"));
				freeBoard.setFree_reg_date(rs.getDate("free_reg_date"));
				freeBoard.setMem_id(rs.getString("mem_id"));
				
				list.add(freeBoard);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//글상세
	public FreeBoardVO getBoard(int free_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardVO freeBoard = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM free_detail f JOIN hmember m USING(mem_num) JOIN hmember_detail d "
					+ "USING(mem_num) WHERE f.free_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, free_id);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();

			if(rs.next()) {
				freeBoard = new FreeBoardVO();
				freeBoard.setFree_id(rs.getInt("free_id")); //컬럼명이라서 문자열로 작성!
				freeBoard.setFree_title(rs.getString("free_title"));
				freeBoard.setFree_content(rs.getString("free_content"));
				freeBoard.setFree_hit(rs.getInt("free_hit"));
				freeBoard.setFree_reg_date(rs.getDate("free_reg_date"));
				freeBoard.setFree_modify_date(rs.getDate("free_modify_date"));
				freeBoard.setFree_photo(rs.getString("free_photo"));
				freeBoard.setMem_num(rs.getInt("mem_num"));
				freeBoard.setMem_id(rs.getString("mem_id"));
				freeBoard.setMem_photo(rs.getString("mem_photo"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return freeBoard;
	}
	
	
	//이전글, 다음글
	public FreeBoardVO prevNext(int free_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardVO pnBoard = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();	
			//SQL문 작성
			sql = "SELECT * FROM (" 
				+ "SELECT free_id,free_title,"
			    + "lag(free_id,1) over(order by free_id) prev,"
			    + "lag(free_title,1) over(order by free_id) prev_title,"
			    + "lead(free_id,1) over(order by free_id) next,"
			    + "lead(free_title,1) over(order by free_id) next_title "
			    + "FROM free_detail) f WHERE f.free_id=?";
					
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, free_id);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();

			if(rs.next()) {
				pnBoard = new FreeBoardVO();
				pnBoard.setFree_id(rs.getInt("free_id"));
				pnBoard.setFree_title(rs.getString("free_title"));
				pnBoard.setPrev(rs.getInt("prev"));
				pnBoard.setPrev_title(rs.getString("prev_title"));
				pnBoard.setNext(rs.getInt("next"));
				pnBoard.setNext_title(rs.getString("next_title"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return pnBoard;
	}
	

	//조회수 증가
	public void updateReadcount(int free_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE free_detail SET free_hit=free_hit+1 WHERE free_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, free_id);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//파일 삭제
	public void deleteFile(int free_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성			//빈 문자열을 대입하면 원래있던 데이터를 없애버린다.
			sql = "UPDATE free_detail SET free_photo='' WHERE free_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, free_id);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글수정
	public void updateBoard(FreeBoardVO freeBoard)throws Exception{
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
			if(freeBoard.getFree_photo()!=null) { //수정폼에서 새로운 파일을 업로드했을 경우
				sub_sql += ",free_photo=?";
			}

			//SQL문 작성
			sql = "UPDATE free_detail SET free_title=?,free_content=?,free_modify_date=SYSDATE" + sub_sql + 
					" WHERE free_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt,freeBoard.getFree_title());
			pstmt.setString(++cnt, freeBoard.getFree_content());
			if(freeBoard.getFree_photo()!=null) {
				pstmt.setString(++cnt, freeBoard.getFree_photo());
			}
			pstmt.setInt(++cnt, freeBoard.getFree_id()); //if문이 실행 안 되면 Free_id가 4번이 아니라 3번이라 이런식으로 처리 

			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글삭제(이게 부모글, 댓글이 자식글. 자식글 있을 때 부모글을 지울 수 없도록 설정(FK로))
	public void deleteBoard(int free_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();

			//오토커밋 해제
			conn.setAutoCommit(false);

			//댓글 삭제
			sql = "DELETE FROM freeComment WHERE free_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_id);
			pstmt.executeUpdate();

			//부모글 삭제(제약조건 때문에 자식글 먼저 삭제해야 함)
			sql = "DELETE FROM free_detail WHERE free_id=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, free_id);
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
	public void insertReplyBoard(FreeBoardReplyVO freeBoardReply)throws Exception{
		System.out.println(freeBoardReply);
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO freeComment (freeComm_id,freeComm_content,mem_num,free_id) "
					+ "VALUES(freeComment_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, freeBoardReply.getFreeComm_content());
			pstmt.setInt(2, freeBoardReply.getMem_num());
			pstmt.setInt(3, freeBoardReply.getFree_id());

			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 개수
	public int getReplyBoardCount(int free_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM freeComment f JOIN hmember m ON f.mem_num=m.mem_num "
				+ "WHERE f.free_id=?"; //댓글은 부모글 밑에 딸려있으니까 부모글의 넘버를 구함
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_id);

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
	public List<FreeBoardReplyVO> getListReplyBoard(int start,int end, int free_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FreeBoardReplyVO> list = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM freeComment f JOIN "
					+ "hmember m USING(mem_num) WHERE "
					+ "f.free_id=? ORDER BY f.freeComm_id DESC)a) "
					+ "WHERE rnum>=? AND rnum<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, free_id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			list = new ArrayList<FreeBoardReplyVO>();
			while(rs.next()) {
				FreeBoardReplyVO reply = new FreeBoardReplyVO();
				reply.setFreeComm_id(rs.getInt("freeComm_id"));
				reply.setFreeComm_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("freeComm_reg_date")));
				reply.setFreeComm_content(StringUtil.useBrNoHtml(rs.getString("freeComm_content")));
				reply.setFree_id(rs.getInt("free_id"));
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
	
	//댓글 상세
	public FreeBoardReplyVO getReplyBoard(int freeComm_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FreeBoardReplyVO reply = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM freeComment WHERE freeComm_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, freeComm_id); //pk를 통해 한 건의 데이터 읽어옴
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply = new FreeBoardReplyVO(); //객체 생성
				reply.setFreeComm_id(rs.getInt("freeComm_id"));
				reply.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return reply;
	}
	
	//댓글 수정
	public void updateReplyBoard(FreeBoardReplyVO reply)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE freeComment SET freeComm_content=? WHERE freeComm_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getFreeComm_content());
			pstmt.setInt(2, reply.getFreeComm_id());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 삭제
	public void deleteReplyBoard(int freeComm_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM freeComment WHERE freeComm_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, freeComm_id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}





