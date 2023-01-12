package kr.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mypage.vo.MypageVO;
import kr.mypage.dao.MypageDAO;
import kr.mypage.vo.MycourselikeVO;

import java.util.List;

public class MyBookmarkAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = 
				 (Integer)session.getAttribute("mem_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우(혜정님 MemberDAO, MemberVo 가져옴) 
		MypageDAO dao = MypageDAO.getInstance();
		MypageVO member = dao.getMember(user_num);
		
		//내가 선택한 강좌 좋아요 목록
		MypageDAO courselikeDao = MypageDAO.getInstance();
		List<MycourselikeVO> courselikeList = 
				courselikeDao.getListCourseFav(user_num);
		
		request.setAttribute("member", member);
		request.setAttribute("courselikeList", courselikeList);
		
		return "/WEB-INF/views/mypage/myBookmark.jsp";
	}

}
