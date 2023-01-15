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
	
	//event_id 조회해서 하나의 이벤트 글 가져오기
	public EventVO getEvent(int event_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		EventVO event = null;
		try {
			//커넥션 할당
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "SELECT * FROM EVENT WHERE event_id = ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//데이터 바인딩
			pstmt.setInt(1, event_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				event = new EventVO();
				
				event.setEvent_id(rs.getInt("event_id"));
				event.setMem_num(rs.getInt("mem_num"));
				event.setEvent_course_id(rs.getInt("event_course_id"));
				event.setEvent_attr(rs.getInt("event_attr"));
				event.setEvent_deadline(rs.getString("event_deadline"));
				event.setEvent_reg_date(rs.getDate("event_reg_date"));
				event.setEvent_hit(rs.getInt("event_hit"));
				event.setEvent_photo(rs.getString("event_photo"));
				event.setEvent_content(rs.getString("event_content"));
				event.setEvent_detail_content(rs.getString("event_detail_content"));
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return event;
	}

	//진행중 또는 종료된 이벤트 게시글의 수를 구하는 메소드
	public int getTotalEvent(int attr)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			if(attr == 1) {							//진행 이벤트일 경우 추가될 sql문
				sub_sql += "WHERE event_attr =1 AND EXTRACT(YEAR FROM event_reg_date) > 2000";
			}else if(attr == 0){					//종료 이벤트일 겨이우 추가될 sql문
				sub_sql += "WHERE event_attr =0 AND EXTRACT(YEAR FROM event_reg_date) > 2000";
			}
			sql = "SELECT COUNT(*) FROM EVENT " + sub_sql;
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//페이징 처리할 이벤트글 목록 가져오기 0:종료  1:진행중
	public List<EventVO> getEventList(int startNum, int endNum, String keyfield, String keyword, int attr, String sort)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EventVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			//dropdown sort변수 추가
			if(sort.equals("1")) {
				sort = "event.event_reg_date DESC";
			}else if(sort.equals("2")) {
				sort = "event.event_hit DESC";
			}else {
				sort = "event.event_deadline ASC";
			}
			//속성(attr)값에 따라 셀렉되는 레코드를 구별
			//+ reg_date기준 추가 -> 2000년 전에 등록한 글은 표시되지 않게
			sql = "SELECT * FROM (SELECT rownum rnum, e.* FROM (SELECT event.*,c.course_name FROM EVENT event "
					+ "JOIN COURSE c ON event.event_course_id = c.course_id "
					+ "WHERE EXTRACT(YEAR FROM event.event_reg_date) > 2000 "
					+ "ORDER BY " + sort + ") e "
					+ "WHERE e.event_attr = "+attr+") WHERE rnum >=? AND rnum <=?";
			
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

	//이벤트 게시글의 
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
	//상세 페이지 - 해당 이벤트 아이디, 속성, 글 강의제목, 등록날짜, 요약내용, 세부내용 불러오기
	public EventVO getDetailEvent(int event_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		EventVO event = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT e.event_id, e.event_course_id, e.event_reg_date, e.event_photo, e.event_deadline, "
					+ "e.event_attr, e.event_detail_content, e.event_content, c.course_name "
					+ "FROM EVENT e JOIN COURSE c ON e.event_course_id = c.course_id "
					+ "WHERE e.event_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, event_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				event = new EventVO();
				
				event.setEvent_id(rs.getInt("event_id"));
				event.setEvent_course_id(rs.getInt("event_course_id"));
				event.setEvent_attr(rs.getInt("event_attr"));
				event.setEvent_deadline(rs.getString("event_deadline"));;
				event.setEvent_reg_date(rs.getDate("event_reg_date"));
				event.setEvent_photo(rs.getString("event_photo"));
				event.setEvent_content(rs.getString("event_content"));
				event.setEvent_detail_content(rs.getString("event_detail_content"));
				event.setEvent_course_name(rs.getString("course_name"));
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return event;
	}
	//조회수 증가
	public void addEventView(int event_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "UPDATE EVENT SET event_hit=event_hit+1 WHERE event_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, event_id);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//이벤트 등록글 수정
		public void updateEvent(EventVO event)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				sql = "UPDATE EVENT SET event_attr=?,event_deadline=?,"
						+ "event_photo=?,event_content=?,event_detail_content=? "
						+ "WHERE event_id = ?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, event.getEvent_attr());
				pstmt.setString(2, event.getEvent_deadline());
				pstmt.setString(3, event.getEvent_photo());
				pstmt.setString(4, event.getEvent_content());
				pstmt.setString(5, event.getEvent_detail_content());
				pstmt.setInt(6, event.getEvent_id());
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
		//등록글 삭제
		public void deleteEvent(int event_id)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				
				sql = "DELETE FROM EVENT WHERE event_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, event_id);
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}

		//이벤트 등록 파일 삭제
		public void deleteEventFile(int event_id)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				conn = DBUtil.getConnection();
				
				sql = "UPDATE EVENT SET event_photo='' WHERE event_id=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, event_id);
				pstmt.executeUpdate();
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		
}
