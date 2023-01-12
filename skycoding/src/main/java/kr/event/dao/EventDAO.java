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
	

	//페이징 처리할 이벤트글 목록 가져오기 0:종료  1:진행중
	public List<EventVO> getEventList(int startNum, int endNum, String keyfield, String keyword, int attr)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EventVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			//속성(attr)값에 따라 셀렉되는 레코드를 구별
			sql = "SELECT * FROM (SELECT rownum, e.* FROM (SELECT event.*,c.course_name FROM EVENT event "
					+ "JOIN COURSE c ON event.event_course_id = c.course_id ORDER BY event.event_reg_date DESC) e "
					+ "WHERE e.event_attr = "+attr+") WHERE rownum >=? AND rownum <=?";
			
			pstmt = conn.prepareStatement(sql);
			//페이지 시작점과 끝점 데이터 바인딩
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<EventVO>();
			while(rs.next()) {
				EventVO event = new EventVO();
				
				event.setEvent_id(rs.getInt("event_id"));
				event.setMem_num(rs.getInt("mem_num"));
				event.setEvent_attr(rs.getInt("event_attr"));
				event.setEvent_deadline(rs.getString("event_deadline"));
				event.setEvent_hit(rs.getInt("event_hit"));
				event.setEvent_photo(rs.getString("event_photo"));
				event.setEvent_reg_date(rs.getDate("event_reg_date"));
				event.setEvent_course_id(rs.getInt("event_course_id"));
				event.setEvent_course_name(rs.getString("course_name"));
				
				list.add(event);
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}

	//상세 페이지 날짜(마감일까지 남은 일수) 구하는 메서드(ParseException 발생 가능)
	public Integer getDiffDate(int event_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		SimpleDateFormat format = null;
		String event_getDeadline = null;
		java.util.Date today = null;
		Integer diffDay = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT event_deadline FROM EVENT WHERE event_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, event_id);
			rs = pstmt.executeQuery();
			
			//마감일 값 추출
			if(rs.next()) {
				event_getDeadline = rs.getString("event_deadline");
			}
			
			//현재 날짜를 deadline의 포맷에 맞게 변환
			format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date event_deadline = format.parse(event_getDeadline);
			today = new java.util.Date();
			
			//두 날짜 간의 일수 차이
			long diffSec = (event_deadline.getTime() - today.getTime()) / 1000;
			diffDay = (int)(diffSec / (24*60*60));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return diffDay;
	}
		
}
