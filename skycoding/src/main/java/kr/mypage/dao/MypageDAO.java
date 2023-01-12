package kr.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.mypage.vo.MypageVO;
import kr.mypage.vo.MycourselikeVO;
import kr.util.DBUtil;

public class MypageDAO {
	//싱글턴 패턴
	private static MypageDAO instance = new MypageDAO();
	
	public static MypageDAO getInstance() {
		return instance;
	}
	private MypageDAO() {}

	//회원가입
	public void insertMember(MypageVO member)
			                            throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0; //시퀀스 번호 저장
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//오토 커밋 해제
			conn.setAutoCommit(false);
			
			//회원번호(mem_num) 생성
			sql = "SELECT hmember_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			sql = "INSERT INTO hmember (mem_num,mem_id) VALUES"
				+ " (?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, num);//시퀀스(회원번호)
			pstmt2.setString(2, member.getId());//id
			pstmt2.executeUpdate();
			
			sql = "INSERT INTO hmember_detail (mem_num,mem_name,"
				+ "mem_pw,mem_email,mem_cell) "
				+ "VALUES (?,?,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, num);//회원번호
			pstmt3.setString(2, member.getName());
			pstmt3.setString(3, member.getPasswd());
			pstmt3.setString(4, member.getEmail());
			pstmt3.setString(5, member.getPhone());
			pstmt3.executeUpdate();
			
			//SQL 실행시 모두 성공하면 commit
			conn.commit();
		}catch(Exception e) {
			//SQL문 실행시 하나라도 실패하면 rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
	}
	//ID 중복 체크 및 로그인 처리
	public MypageVO checkMember(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MypageVO member = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//hmember와 hmember_detail 조인시 hmember의 누락된
			//데이터가 보여야 id 중복 체크 가능함
			sql = "SELECT * FROM hmember m LEFT OUTER JOIN "
				+ "hmember_detail d ON m.mem_num=d.mem_num "
				+ "WHERE m.mem_id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, id);
			
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MypageVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("mem_id"));
				member.setAuth(rs.getInt("mem_auth"));
				member.setPasswd(rs.getString("mem_pw"));
				member.setPhoto(rs.getString("mem_photo"));
				member.setEmail(rs.getString("mem_email"));//회원탈퇴시 필요
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	   
		//회원상세 정보
		public MypageVO getMember(int mem_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MypageVO member = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM hmember m JOIN hmember_detail d "
					+ "ON m.mem_num=d.mem_num WHERE m.mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, mem_num);
				
				//SQL문을 실행해서 결과행을 ResultSet에 담음
				rs = pstmt.executeQuery();
				if(rs.next()) {
					member = new MypageVO();
					member.setMem_num(rs.getInt("mem_num"));
					member.setId(rs.getString("mem_id"));
					member.setAuth(rs.getInt("mem_auth"));
					member.setPasswd(rs.getString("mem_pw"));
					member.setName(rs.getString("mem_name"));
					member.setPhone(rs.getString("mem_cell"));
					member.setEmail(rs.getString("mem_email"));
					member.setPhoto(rs.getString("mem_photo"));
					member.setReg_date(rs.getDate("mem_reg_date"));//가입일
					member.setModify_date(rs.getDate("mem_modify_date"));//수정일
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return member;
		}
		//회원정보 수정
		public void updateMember(MypageVO member)
				                             throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE hmember_detail SET mem_name=?,mem_cell=?,"
					+ "mem_email=?,"
					+ "mem_modify_date=SYSDATE WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setString(1, member.getName());
				pstmt.setString(2, member.getPhone());
				pstmt.setString(3, member.getEmail());
				pstmt.setInt(4, member.getMem_num());
				
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//비밀번호 수정
		public void updatePassword(String passwd, int mem_num)
		                                       throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE hmember_detail SET mem_pw=? "
					+ "WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setString(1, passwd);//새비밀번호
				pstmt.setInt(2, mem_num);//회원번호
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//프로필 사진 수정
		public void updateMyPhoto(String photo, int mem_num)
		                                    throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE hmember_detail SET mem_photo=? WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setString(1, photo);
				pstmt.setInt(2, mem_num);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//회원탈퇴(회원정보 삭제)
		public void deleteMember(int mem_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			PreparedStatement pstmt2 = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//auto commit 해제
				conn.setAutoCommit(false);
				
				//hmember의 auth 값 변경
				sql = "UPDATE hmember SET mem_auth=0 WHERE mem_num=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				//SQL문 실행
				pstmt.executeUpdate();
				
				//hmember_detail의 레코드 삭제
				sql = "DELETE FROM hmember_detail WHERE mem_num=?";
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, mem_num);
				pstmt2.executeUpdate();
				
				//모든 SQL문의 실행이 성공하면 commit
				conn.commit();
			}catch(Exception e) {
				//SQL문 실행시 하나라도 실패하면 롤백
				conn.rollback();
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt2, null);
				DBUtil.executeClose(null, pstmt, conn);
			}
		}		
		
		//내가 선택한 좋아요 목록
		public List<MycourselikeVO> getListCourseFav(int start,
				                        int end,int mem_num)
				            		   throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<MycourselikeVO> list = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM course c JOIN hmember m "
					+ "USING(mem_num) JOIN course_like l USING(course_id) "
					+ "WHERE l.mem_num=? ORDER BY course_id DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
				//PrepardStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, mem_num);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				//SQL문을 실행해서 결과행들을 ResultSet에 담음
				rs = pstmt.executeQuery();
				list = new ArrayList<MycourselikeVO>();
				while(rs.next()) {
					MycourselikeVO course_like = new MycourselikeVO();
					course_like.setCourse_id(rs.getInt("course_id"));
					course_like.setCourse_name(rs.getString("course_name"));
					course_like.setReport_date(rs.getDate("report_date"));
					course_like.setCourse_photo(rs.getString("course_photo"));
					course_like.setMem_num(rs.getInt("mem_num"));
					
					list.add(course_like);
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
}
