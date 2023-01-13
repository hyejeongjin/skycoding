package kr.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.cart.vo.CartVO;
import kr.util.DBUtil;

public class CartDAO {
	private static CartDAO instance = new CartDAO();

	public static CartDAO getInstance() {
		return instance;
	}
	private CartDAO() {}

	//학습중 강좌 등록
	public void insertCart(CartVO cart)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO course_cart (cart_num,course_id,mem_num) "
					+ "VALUES (course_cart_seq.nextval,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, cart.getCourse_id());
			pstmt.setInt(2, cart.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}


	//학습중 강좌 중복 체크 및 수강신청 처리 
	public CartVO checkCart(int course_id, int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartVO cart = null;
		String sql = null;

		try {

			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();

			//sql문 작성
			sql = "SELECT * FROM course_cart WHERE course_id=? AND mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			//?에 데이터 바인딩
			pstmt.setInt(1, course_id);
			pstmt.setInt(2, mem_num);

			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cart = new CartVO();
				cart.setCart_num(rs.getInt("cart_num"));
				cart.setCourse_id(rs.getInt("course_id"));
				cart.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}

		return cart;
	}

}

//학습중 강좌 목록

//학습중 강좌 수정


//학습중 강좌 삭제



