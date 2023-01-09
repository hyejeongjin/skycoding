 package kr.news.dao;
import java.sql.Connection;




import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class NewsDAO {
	//싱글턴 패턴
		private static NewsDAO instance = new NewsDAO();
		
		public static NewsDAO getInstance() {
			return instance;
		}
		private NewsDAO() {}
		
		//글등록
		public void insertNews(NewsVO news)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "INSERT INTO news (news_id,news_photo,news_title,"
				    + "news_content) VALUES ("
				    + "news_seq.nextval,?,?,?)";
				
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
		
				pstmt.setString(1, news.getNews_photo());
				pstmt.setString(2, news.getNews_title());
				pstmt.setString(3, news.getNews_content());
				
			
		
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//총 레코드수( 검색수)
		public int getNewsCount(String keyfield, 
				                 String keyword)
		                                    throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";//검색시 사용
			int count = 0;
			
			try {
				//커넥셔풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				if(keyword != null && !"".equals(keyword)) {
					//검색글 개수
					if(keyfield.equals("1")) sub_sql += "WHERE b.title LIKE ?";
					else if(keyfield.equals("2")) sub_sql += "WHERE b.content LIKE ?";
				}
				
				
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM news b JOIN hmember USING(mem_num)" + sub_sql;
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				if(keyword !=null && !"".equals(keyword)) {
					pstmt.setString(1, "%" + keyword + "%");
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
		//글목록(검색글 목록)
		public List<NewsVO> getListNews(int start, int end,
				             String keyfield, String keyword)
		                                   throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<NewsVO> list = null;
			String sql = null;
			String sub_sql = "";//검색시 사용
			int cnt = 0;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				
				if(keyword != null && !"".equals(keyword)) {
					//검색글 보기
					if(keyfield.equals("1")) sub_sql += "WHERE b.title LIKE ?";
					else if(keyfield.equals("2")) sub_sql +=  "WHERE b.content LIKE ?";
				
				}
				
				//SQL문 작성
				sql= "SELECT * FROM (SELECT a.*, rownum rnum "
						+ "FROM (SELECT * FROM news  " 
						+ sub_sql + " ORDER BY b.news_num DESC)a) "
						+ "WHERE rnum >= ? AND rnum <= ?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				if(keyword != null && !"".equals(keyword)) {
					pstmt.setString(++cnt, "%" + keyword + "%");
				}
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
				//SQL문을 실행해서 결과행들을 ResultSet에 담음
				rs = pstmt.executeQuery();
				list = new ArrayList<NewsVO>();
				while(rs.next()) {
					NewsVO news = new NewsVO();
					news.setNews_id(rs.getInt("news_id"));
					news.setNews_attr(rs.getInt("news_attr"));
					news.setNews_title(StringUtil.useNoHtml(rs.getString("news_title")));
					news.setNews_hit(rs.getInt("news_hit"));
					news.setNews_reg_date(rs.getDate("news_reg_date"));
					news.setNews_modify_date(rs.getDate("news_modify_date"));
					
					
					list.add(news);
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
}