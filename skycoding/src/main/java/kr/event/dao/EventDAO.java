package kr.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.event.vo.EventVO;
import kr.util.DBUtil;

public class EventDAO {
	
	private static EventDAO instance = new EventDAO();
	public static EventDAO getInstance() {
		return instance;
	}
	private EventDAO() {}
	
	//이벤트 등록
	public void registerEvent(EventVO event) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "INSERT INTO EVENT(event_id,mem_num,event_course_id,event_attr,"
				+ "event_deadline,event_photo,event_content,event_detail_content) "
				+ "VALUES (event_seq.nextval,?,?,?,?,?,?,?)";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//데이터 바인딩
			pstmt.setInt(1, event.getMem_num());
			pstmt.setInt(2, event.getEvent_course_id());
			pstmt.setInt(3, event.getEvent_attr());
			pstmt.setString(4, event.getEvent_deadline());
			pstmt.setString(5, event.getEvent_photo());
			pstmt.setString(6, event.getEvent_content());
			pstmt.setString(7, event.getEvent_detail_content());
			
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//기존에 등록된 course_id, course_name 얻어오기
	public Map<String, String> getCourse(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> courseMap = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT e.event_course_id, c.course_name "
					+ "FROM EVENT e JOIN COURSE c ON e.event_course_id = c.course_id "
					+ "WHERE e.mem_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			
			rs = pstmt.executeQuery();
			//course_name과 event_course_id 값을 담을 HashMap생성
			courseMap = new HashMap<String, String>();
			while(rs.next()) {
				courseMap.put(rs.getString("course_name"), Integer.toString(rs.getInt("event_course_id")));
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		//courseMap 반환
		return courseMap;
	}
}
