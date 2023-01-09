package kr.course.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


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
	public int getCourseCount(String keyfield,String keyword)
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
			if(keyfield.equals("1")) sub_sql += "AND course_name LIKE ?";
		}
		
		//검색글 개수
		sql = "SELECT COUNT(*) FROM course " + sub_sql;
		//PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
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
	
	//관리자/사용자 - 검색 강의 목록 , 전체 강의 목록 따로?
	public List<CourseVO> getListCourse(int start, int end, 
			String keyfield, String keyword,
			int course_cate) throws Exception{
        
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
				if(keyfield.equals("1")) sub_sql += "AND course_name LIKE ?";
			}
			
			//전체/검색 강의 보기
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM ("
					+ "SELECT * FROM course WHERE course_cate > ? " + sub_sql
					+ " ORDER BY course_id DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, course_cate);
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
				
				list.add(course);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
}
	//관리자/사용자 - 강의상세
	//조회수증가
	//좋아요 등록
	//좋아요 개수
	//회원이 강의를 호출했을 때 좋아요 선택 여부 표시
	//좋아요 삭제
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

