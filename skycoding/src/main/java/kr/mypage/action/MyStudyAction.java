package kr.mypage.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mypage.dao.MypageDAO;
import kr.mypage.vo.MycourselistVO;
import kr.mypage.vo.MypageVO;

public class MyStudyAction implements Action{

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

		String sort = request.getParameter("sort"); //정렬 기준
		String keyword = request.getParameter("query"); //강좌명 검색

		if(sort==null) sort="1";
		
		//내가 신청한 강좌 목록
		MypageDAO courseDao = MypageDAO.getInstance();
		List<MycourselistVO> courseList = 
				courseDao.getListCourse(user_num, sort, keyword);
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("member", member);
		request.setAttribute("courseList", courseList);
		
		return "/WEB-INF/views/mypage/myStudy.jsp";
	}

}
