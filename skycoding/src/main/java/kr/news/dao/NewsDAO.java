 package kr.news.dao;
import java.sql.Connection;





import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.DBUtil;
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
				    + "news_content,news_attr) VALUES ("
				    + "news_seq.nextval,?,?,?,?)";
				
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
		
				pstmt.setString(1, news.getNews_photo());
				pstmt.setString(2, news.getNews_title());
				pstmt.setString(3, news.getNews_content());
				pstmt.setInt(4, news.getNews_attr());
			
		
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
					if(keyfield.equals("1")) sub_sql += "WHERE news_title LIKE ?";
					else if(keyfield.equals("2")) sub_sql += "WHERE news_content LIKE ?";
				}
				
				      
				//SQL문 작성 
				sql = "SELECT COUNT(*) FROM news " + sub_sql;
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
				             String keyfield, String keyword,String sort)
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
					if(keyfield.equals("1")) sub_sql += "WHERE b.news_title LIKE ?";
					else if(keyfield.equals("2")) sub_sql +=  "WHERE b.news_content LIKE ?";
				
				}
				//dropdown if에 sort 추가
				if(sort.equals("1")) {
					sort = "news_id DESC";
				}else if(sort.equals("2")) {
					sort = "news_hit DESC";
				}else {
					sort = "news_id DESC";
				}
				
				//SQL문 작성 --//dropdown orderby 옆에 sort 변수 추가
				sql= "SELECT * FROM (SELECT a.*, rownum rnum "
						+ "FROM (SELECT * FROM news b " 
						+ sub_sql + " ORDER BY  news_attr ASC,"+sort+", news_id DESC)a) "
						+ "WHERE rnum >= ? AND rnum <= ?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);

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

		
		//글상세
		public NewsVO getNews(int news_id)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			NewsVO news = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM news "
						+ "WHERE news_id=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, news_id);
				//SQL문을 실행해서 결과행을 ResultSet에 담음
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					news = new NewsVO();
					news.setNews_id(rs.getInt("news_id"));
					news.setNews_title(rs.getString("news_title"));
					news.setNews_content(rs.getString("news_content"));
					news.setNews_hit(rs.getInt("news_hit"));
					news.setNews_reg_date(rs.getDate("news_reg_date"));
					news.setNews_modify_date(rs.getDate("news_modify_date"));
					news.setNews_photo(rs.getString("news_photo"));
					
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return news;
		}
		
		//조회수 증가
		public void updateReadcount(int news_id)
				                       throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE news SET news_hit=news_hit+1 WHERE news_id=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setInt(1, news_id);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//파일 삭제
		public void deleteFile(int news_id)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "UPDATE news SET news_photo='' WHERE news_id=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, news_id);
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
	    //글수정
		public void updateNews(NewsVO news)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			String sub_sql = "";
			int cnt = 0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				
				//전송된 파일 여부 체크
				if(news.getNews_photo()!=null) {
					sub_sql += ",news_photo=?";
				}
				
				sql = "UPDATE news SET news_title=?,news_content=?,"
					+ "news_modify_date=SYSDATE" + sub_sql +" WHERE news_id=?";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setString(++cnt, news.getNews_title());
				pstmt.setString(++cnt, news.getNews_content());
				if(news.getNews_photo()!=null) {
					pstmt.setString(++cnt, news.getNews_photo());
				}
				
				pstmt.setInt(++cnt, news.getNews_id());
				
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		 //글삭제
		public void deleteNews(int news_id)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				
				//부모글 삭제
				sql = "DELETE FROM news WHERE news_id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, news_id);
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
		
		}
		
		
}