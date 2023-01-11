package kr.hmember.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
               + "mem_pwq,mem_pwa,mem_cell,mem_email) VALUES (?,?,?,?,?,?,?)";
         pstmt3 = conn.prepareStatement(sql);
         pstmt3.setInt(1, num);//회원번호
         pstmt3.setString(2, hmember.getMem_name());
         pstmt3.setString(3, hmember.getMem_pw());
         pstmt3.setInt(4, hmember.getMem_pwq());
         pstmt3.setString(5, hmember.getMem_pwa());
         pstmt3.setString(6, hmember.getMem_cell());
         pstmt3.setString(7, hmember.getMem_email());
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

         //SQL문을 실행해서 결과행을 ResultSet에 담음
         rs = pstmt.executeQuery();
         if(rs.next()) {
            hmember = new MemberVO();
            hmember.setMem_num(rs.getInt("mem_num"));
            hmember.setMem_id(rs.getString("mem_id"));
            hmember.setMem_auth(rs.getInt("mem_auth"));
            hmember.setMem_pw(rs.getString("mem_pw"));
            hmember.setMem_cell(rs.getString("mem_cell"));//회원탈퇴 시 사용할 것
            hmember.setMem_email(rs.getString("mem_email"));
            hmember.setMem_photo(rs.getString("mem_photo"));
         }
      }catch(Exception e) {
         throw new Exception(e);
      }finally {
         DBUtil.executeClose(rs, pstmt, conn);
      }
      return hmember;
   }

   //비밀번호 질문
   public List<MemberVO> getGnaList() throws Exception{

      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<MemberVO> list = null;
      String sql = null;

      try {
         //커넥션풀로부터 커넥션 할당
         conn = DBUtil.getConnection();

         //SQL문 작성
         sql = "SELECT * FROM hmember_qna";

         //PreparedStatement 객체 생성
         pstmt = conn.prepareStatement(sql);

         //SQL문을 실행해서 결과행들을 ResultSet에 담음
         rs = pstmt.executeQuery();
         list = new ArrayList<MemberVO>();
         while(rs.next()) {
            MemberVO qna = new MemberVO();
            qna.setMem_pwq(rs.getInt("mem_pwq"));
            qna.setQna_detail(rs.getString("qna_detail"));

            list.add(qna);
         }

      }catch(Exception e) {
         throw new Exception(e);
      }finally {
         DBUtil.executeClose(rs, pstmt, conn);
      }

      return list;
   }

   //아이디 찾기
   public String findId(String email, String cell) throws Exception{
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      String myId = null;

      try {
         //커넥션풀로부터 커넥션 할당
         conn = DBUtil.getConnection();

         //sql문 작성
         sql = "SELECT mem_id FROM hmember m LEFT OUTER JOIN "
               + "hmember_detail d ON m.mem_num=d.mem_num "
               + "WHERE d.mem_email=? AND d.mem_cell=?";

         //PreparedStatement 객체 생성
         pstmt = conn.prepareStatement(sql);

         //?에 데이터 바인딩
         pstmt.setString(1, email);
         pstmt.setString(2, cell);

         //SQL문을 실행해서 결과행을 ResultSet에 담음
         rs = pstmt.executeQuery();
         if(rs.next()) {
            myId = rs.getString("mem_id");
         }
      }catch(Exception e) {
         throw new Exception(e);
      }finally {
         DBUtil.executeClose(rs, pstmt, conn);
      }
      return myId;
   }

   //비밀번호 찾기
   public String findPw(String id, String pwa) throws Exception{
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      String myPw = null;

      try {
         //커넥션풀로부터 커넥션 할당
         conn = DBUtil.getConnection();

         //sql문 작성
         sql = "SELECT * FROM hmember JOIN hmember_detail USING(mem_num) "
               + "JOIN hmember_qna USING(mem_pwq) WHERE mem_id=? AND mem_pwa=?";

         //PreparedStatement 객체 생성
         pstmt = conn.prepareStatement(sql);

         //?에 데이터 바인딩
         pstmt.setString(1, id);
         pstmt.setString(2, pwa);

         //SQL문을 실행해서 결과행을 ResultSet에 담음
         rs = pstmt.executeQuery();
         if(rs.next()) {
            myPw = rs.getString("mem_pw");
         }
      }catch(Exception e) {
         throw new Exception(e);
      }finally {
         DBUtil.executeClose(rs, pstmt, conn);
      }
      return myPw;
   }

   public String findQuest(String id) throws Exception{
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = null;
      String msg = null;

      try {
         //커넥션풀로부터 커넥션 할당
         conn = DBUtil.getConnection();

         //sql문 작성
         sql = "SELECT * FROM hmember JOIN hmember_detail USING(mem_num) JOIN hmember_qna USING(mem_pwq) WHERE mem_id=?";

         //PreparedStatement 객체 생성
         pstmt = conn.prepareStatement(sql);

         //?에 데이터 바인딩
         pstmt.setString(1, id);

         //SQL문을 실행해서 결과행을 ResultSet에 담음
         rs = pstmt.executeQuery();
         if(rs.next()) {
            msg = rs.getString("qna_detail");
         }
      }catch(Exception e) {
         throw new Exception(e);
      }finally {
         DBUtil.executeClose(rs, pstmt, conn);
      }
      return msg;
   }
}

