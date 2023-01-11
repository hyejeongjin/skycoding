package kr.jobReview.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.jobReview.vo.ReviewCommentVO;
import kr.jobReview.vo.ReviewVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class ReviewDAO {
	//싱글턴 패턴
	private static ReviewDAO instance = new ReviewDAO();
	public static ReviewDAO getInstance() {
		return instance;
	}
	private ReviewDAO() {}
	
	//글등록
	public void insertReview(ReviewVO review)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		 
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO job_review (rev_id,mem_num,rev_title,rev_photo,rev_content) VALUES "
					+ "(job_review_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review.getMem_num());
			pstmt.setString(2, review.getRev_title());
			pstmt.setString(3, review.getRev_photo());
			pstmt.setString(4, review.getRev_content());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//총 레코드 수(검색 레코드수)
	public int getReviewCount(String keyfield,String keyword)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			if(keyword!=null && !keyword.equals("")) {
				if(keyfield.equals("1")) sub_sql += "WHERE j.rev_title Like ?";//제목
				if(keyfield.equals("2")) sub_sql += "WHERE j.rev_content Like ?";//내용
				if(keyfield.equals("3")) sub_sql += "WHERE h.mem_id Like ?";//작성자
			}
			sql = "SELECT COUNT(*) FROM job_review j JOIN hmember h USING(mem_num) "+sub_sql;
			pstmt = conn.prepareStatement(sql);
			
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, "%"+keyword+"%");
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return count;
	}
	
	//검색글 목록
	                                    //테이블에서 가져올 열 번호 
	public List<ReviewVO> getReviewList(int start,int end,String keyfield,String keyword)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewVO> list = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			if(keyword!=null&&!keyword.equals("")) {
				if(keyfield.equals("1")) sub_sql += "WHERE j.rev_title Like ?";//제목
				else if(keyfield.equals("2")) sub_sql += "WHERE j.rev_content Like ?";//내용
				else if(keyfield.equals("3")) sub_sql += "WHERE h.mem_id Like ?";//작성자
			}
			
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT * FROM job_review j "
					+ "JOIN hmember h USING(mem_num) "+sub_sql+" ORDER BY j.rev_id DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			
			if(keyword!=null&&!keyword.equals("")) {
				pstmt.setString(++count, "%"+keyword+"%");
			}
			pstmt.setInt(++count, start);
			pstmt.setInt(++count, end);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<ReviewVO>();
			while(rs.next()) {
				ReviewVO review = new ReviewVO();
				review.setRev_id(rs.getInt("rev_id"));
				review.setRev_title(StringUtil.useNoHtml(rs.getString("rev_title")));
				review.setMem_id(rs.getString("mem_id"));
				review.setRev_reg_date(rs.getDate("rev_reg_date"));
				review.setRev_hit(rs.getInt("rev_hit"));
				
				list.add(review);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//글상세
	public ReviewVO getReviewDetail(int rev_id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewVO review = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM job_review j JOIN hmember h USING(mem_num) "
					+ "JOIN hmember_detail d USING(mem_num) WHERE j.rev_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				review = new ReviewVO();
				review.setRev_id(rs.getInt("rev_id"));
				review.setRev_title(rs.getString("rev_title"));
				review.setRev_content(rs.getString("rev_content"));
				review.setRev_photo(rs.getString("rev_photo"));
				review.setRev_reg_date(rs.getDate("rev_reg_date"));
				review.setRev_modify_date(rs.getDate("rev_modify_date"));
				review.setRev_hit(rs.getInt("rev_hit"));
				review.setMem_num(rs.getInt("mem_num"));
				review.setMem_id(rs.getString("mem_id"));
				review.setMem_photo(rs.getString("mem_photo"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return review;
	}
	
	//조회수 증가
	public void updateReadCount(int rev_id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE job_review SET rev_hit=rev_hit+1 WHERE rev_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//파일 삭제
	public void deleteFile(int rev_id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE job_review SET rev_photo='' WHERE rev_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_id);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글수정
	public void updateReview(ReviewVO review)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			//파일이 이미 저장되어 있는 경우, 파일을 선택안했을 때 null값으로 업데이트하면 기존 파일이 사라짐
			if(review.getRev_photo()!=null) {
				sub_sql += ",rev_photo=?";
			}
			
			sql = "UPDATE job_review SET rev_title=?,rev_content=?,rev_modify_date=SYSDATE"
					+ sub_sql+" WHERE rev_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, review.getRev_title());
			pstmt.setString(++cnt, review.getRev_content());
			if(review.getRev_photo()!=null) {
				pstmt.setString(++cnt, review.getRev_photo());
			}
			pstmt.setInt(++cnt, review.getRev_id());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글삭제
	public void deleteReview(int rev_id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			//오토커밋 해제
			conn.setAutoCommit(false);
			
			//댓글 삭제
			sql = "DELETE FROM job_review_comment WHERE rev_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_id);
			pstmt.executeUpdate();
			
			//부모글 삭제
			sql = "DELETE FROM job_review WHERE rev_id=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, rev_id);
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
	public void insertReviewComment(ReviewCommentVO reviewComment)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO job_review_comment (com_id,com_content,mem_num,rev_id) VALUES "
					+ "(job_review_comment_seq.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewComment.getCom_content());
			pstmt.setInt(2, reviewComment.getMem_num());
			pstmt.setInt(3, reviewComment.getRev_id());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 개수
	public int getReviewCommentCount(int rev_id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		 
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM job_review_comment j JOIN hmember h USING(mem_num) "
					+ "WHERE j.rev_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rev_id);
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
	public List<ReviewCommentVO> getListReviewComment(int start, int end, int rev_id)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewCommentVO> list = null;
		String sql = null;
		
		try {
			//커넥션풀로부텈 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT * FROM job_review_comment j "
					+ "JOIN hmember h USING(mem_num) WHERE j.rev_id=? ORDER BY j.com_id DESC)a) WHERE rnum>=? AND rnum<=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, rev_id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//SQL문 실행해서 결과행들을 rs에 담음
			rs = pstmt.executeQuery();
			list = new ArrayList<ReviewCommentVO>();
			while(rs.next()) {
				ReviewCommentVO reviewComment = new ReviewCommentVO();
				reviewComment.setCom_id(rs.getInt("com_id"));
				reviewComment.setCom_content(StringUtil.useBrNoHtml(rs.getString("com_content")));
				reviewComment.setCom_reg_date(rs.getString("com_reg_date"));
				//수정일은 not null이 아니기때문에 조건 체크
				if(rs.getString("com_modify_date")!=null) {
					reviewComment.setCom_modify_date(rs.getString("com_modify_date"));
				}
				reviewComment.setMem_num(rs.getInt("mem_num"));
				reviewComment.setMem_id(rs.getString("mem_id"));
				reviewComment.setRev_id(rs.getInt("rev_id"));
				
				list.add(reviewComment);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	
	//댓글 상세
	//작성자회원번호를 알아내기 위해(로그인한 사람과 일치하는지 확인)
	public ReviewCommentVO getReviewComment(int com_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewCommentVO reviewComment = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT *FROM job_review_comment WHERE com_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reviewComment = new ReviewCommentVO();
				reviewComment.setCom_id(rs.getInt("com_id"));
				reviewComment.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return reviewComment;
	}
	
	//댓글 수정
	public void updateReviewComment(ReviewCommentVO reviewComment)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE job_review_comment SET com_content=? WHERE com_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reviewComment.getCom_content());
			pstmt.setInt(2, reviewComment.getCom_id());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 삭제
	
	
	
	
	
	
	
}
