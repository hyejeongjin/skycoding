package kr.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import kr.course.vo.CourseFavVO;
import kr.course.vo.CourseVO;
import kr.util.DBUtil;

public class CourseDAO {
	//싱글턴패턴
	private static CourseDAO instance = new CourseDAO();
	
	public static CourseDAO getInstance() {
		return instance;
	}
	private CourseDAO() {}
	
	
	//관리자 - 강의등록
	public void insertCourse(CourseVO course)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO course (course_id,mem_num,course_name,"
				+ "course_content,course_tr,course_cate,course_photo) "
				+ "VALUES (course_seq.nextval,?,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, course.getMem_num());
			pstmt.setString(2, course.getCourse_name());
			pstmt.setString(3, course.getCourse_content());
			pstmt.setString(4, course.getCourse_tr());
			pstmt.setInt(5,course.getCourse_cate());
			pstmt.setString(6,course.getCourse_photo());
			
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//관리자/사용자 - 전체 강의 개수/검색 강의 개수
	public int getCourseCount(String keyfield,String keyword,int course_cate)
            throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = ""; //연결 용도, 조건체크에 만족하지 않으면 빈문자열 형태로 처리
		int count = 0;
	try {
		//커넥션풀로부터 커넥션을 할당
		conn = DBUtil.getConnection();
		
		if(keyword != null && !"".equals(keyword)) {
			
			
			//검색 처리
			sub_sql += "AND course_name LIKE ?";
		}
		
		//검색글 개수
		sql = "SELECT COUNT(*) FROM course WHERE course_cate=? " + sub_sql;
		//PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, course_cate);
		//keyword가 비어있지 않고 null이 아니면 검색처리
		if(keyword != null && !"".equals(keyword)) {
			pstmt.setString(2, "%"+keyword+"%");
		}
		//SQL문을 실행하고 결과행을 ResultSet 담음
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
	
	//관리자/사용자 - 검색 강의 목록 , 전체 강의 목록 (최신순)
	public List<CourseVO> getListCourse(int start, int end, 
			String keyfield, String keyword,
			int course_cate, String sort) throws Exception{
        
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CourseVO> list = null;
		String sql = null;
		String sub_sql = "";//검색시 사용
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				//검색 처리
				sub_sql += "AND course_name LIKE ?";
			}
			
			if(sort.equals("1")) {
				sort = "course_id DESC";
			}else if(sort.equals("2")) {
				sort = "course_hit DESC";
			}else if(sort.equals("3")) {
				sort = "like_count DESC NULLS LAST";
			}else {
				sort = "course_id DESC";
			}
			
			//전체/검색 강의 보기
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM ("
					+ "SELECT * FROM course LEFT OUTER JOIN (SELECT course_id, COUNT(*) like_count FROM course_like group by course_id) USING(course_id) WHERE course_cate = ? " + sub_sql
					+ " ORDER BY "+sort+")a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(++cnt, course_cate);
			//검색 부분이 들어가므로 cnt가 필요 
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문을 실행해서 결과행들을 ResultSet 담음
			rs = pstmt.executeQuery();
			list = new ArrayList<CourseVO>();
			while(rs.next()) {
				CourseVO course = new CourseVO();
				course.setCourse_id(rs.getInt("course_id"));
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_hit(rs.getInt("course_hit"));
				course.setCourse_content(rs.getString("course_content"));
				course.setReport_date(rs.getDate("report_date"));
				course.setCourse_tr(rs.getString("course_tr"));
				course.setCourse_cate(rs.getInt("course_cate"));
				course.setCourse_photo(rs.getString("course_photo"));
				course.setLike_count(rs.getInt("like_count"));
				
				list.add(course);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	//관리자/사용자 - 강의상세
    public CourseVO getCourse(int course_id)throws Exception{
    	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CourseVO course = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM course WHERE course_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, course_id);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			if(rs.next()) {
				course = new CourseVO();
				
				course.setCourse_id(rs.getInt("course_id"));
				course.setCourse_name(rs.getString("course_name"));
				course.setCourse_hit(rs.getInt("course_hit"));
				course.setCourse_content(rs.getString("course_content"));
				course.setReport_date(rs.getDate("report_date"));
				course.setCourse_tr(rs.getString("course_tr"));
				course.setCourse_cate(rs.getInt("course_cate"));
				course.setCourse_photo(rs.getString("course_photo"));
				
				
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return course;
		
    
}
	//조회수증가
    public void UpdateReadCount(int course_id)throws Exception {
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = null;
	
	try{
		//커넥션풀로부터 커넥션 할당
		conn = DBUtil.getConnection();
		//SQL문 작성
		sql = "UPDATE course SET course_hit=course_hit+1 WHERE course_id=?";
		//preparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setInt(1, course_id);
		//SQL문 실행
		pstmt.executeUpdate();
	  }catch(Exception e) {
		throw new Exception(e);
	}finally {
		DBUtil.executeClose(null, pstmt, conn);
	}
    
	
    }

   //좋아요 등록
   public void insertFav(CourseFavVO favVO)
   								throws Exception{
	   Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO course_like (like_num,"
					+ "course_id,mem_num) VALUES ("
					+ "courselike_seq.nextval,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			//?에 데이터 바인딩
			pstmt.setInt(1, favVO.getCourse_id());
			pstmt.setInt(2, favVO.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
   }

	//좋아요 개수
	public int selectFavCount(int course_id)
	                                  throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM course_like WHERE course_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, course_id);
			//SQL문을 실행하고 결과행을 ResultSet에 담음
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
	
	//회원이 게시물을 호출했을 때 좋아요 선택 여부 표시
		public CourseFavVO selectFav(CourseFavVO favVO)
				                         throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CourseFavVO fav = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM course_like WHERE course_id=? AND mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, favVO.getCourse_id());
				pstmt.setInt(2, favVO.getMem_num());
				//SQL문을 실행해서 결과행을 ResultSet에 담음
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					fav = new CourseFavVO();
					fav.setLike_num(rs.getInt("like_num"));
					fav.setCourse_id(rs.getInt("course_id"));
					fav.setMem_num(rs.getInt("mem_num"));
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return fav;
		}
	//좋아요 삭제
    public void deleteFav(int like_num)throws Exception {
    	Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM course_like WHERE like_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, like_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
    }
    
	
	
}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

