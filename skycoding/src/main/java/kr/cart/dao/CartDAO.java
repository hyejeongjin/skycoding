package kr.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;


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
			sql = "INSERT INTO course_cart (cart_num,course_id,"
				+ ",mem_num) VALUES ("
				+ "course_cart_seq.nextval,?,?)";
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
}

//학습중 강좌 목록

//학습중 강좌 수정


//학습중 강좌 삭제



