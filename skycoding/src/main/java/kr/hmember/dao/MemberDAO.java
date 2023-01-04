package kr.hmember.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.hmember.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	
	//싱글턴 패턴
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private MemberDAO() {}
	
	//회원가입
	public void insertMember(MemberVO hmember)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0; //시퀀스 번호 저장
		
		try {
			//커넥션 풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			conn.setAutoCommit(false);
			
			sql = "SELECT hmember_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
			}
			
			
			sql = "INSERT INTO hmember (mem_num,mem_id) VALUES (?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, num);//회원번호
			pstmt2.setString(2, hmember.getMem_id());//회원id
			pstmt2.executeUpdate();
			
			
			sql = "INSERT INTO hmember_detail (mem_num,mem_name,mem_pw,"
					+ "mem_pwq,mem_pwa,mem_cell) VALUES (?,?,?,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, num);//회원번호
			pstmt3.setString(2, hmember.getMem_name());
			pstmt3.setString(3, hmember.getMem_pw());
			pstmt3.setInt(4, hmember.getMem_pwq());
			pstmt3.setString(5, hmember.getMem_pwa());
			pstmt3.setString(6, hmember.getMem_cell());
			pstmt3.executeUpdate();
			
			//모두 성공 시 commit
			conn.commit();
		}catch(Exception e) {
			//하나라도 실패 시 rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, conn);
			DBUtil.executeClose(null, pstmt2, conn);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}	
	
	//ID 중복 체크 및 로그인 처리
	public MemberVO checkMember(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO hmember = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			//sql문 작성
			sql = "SELECT * FROM hmember m LEFT OUTER JOIN "
					+ "hmember_detail d ON m.mem_num=d.mem_num "
					+ "WHERE m.mem_id=?";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//?에 데이터 바인딩
			pstmt.setString(1, id);
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return hmember;
	}
	
}








