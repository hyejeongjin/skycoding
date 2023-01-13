package kr.mypage.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mypage.dao.MypageDAO;
import kr.mypage.vo.MycourselistVO;
import kr.util.PagingUtil2;

public class MyStudyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1"; //메인에서 list.do를 호출할 때
		String keyword = request.getParameter("query"); //강좌명 검색
		String sort = request.getParameter("sort"); //정렬 기준
		if(sort==null) sort="1";
		
		HttpSession session = request.getSession();
		Integer user_num = 
				 (Integer)session.getAttribute("mem_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우(혜정님 MemberDAO, MemberVo 가져옴)
		MypageDAO dao = MypageDAO.getInstance();
		int count = dao.getCoursecartCount(user_num, keyword);

		
		PagingUtil2 page = new PagingUtil2(null, keyword, Integer.parseInt(pageNum), count, 3, 3, "myStudy.do");
		
		//내가 신청한 강좌 목록
		MypageDAO courseDao = MypageDAO.getInstance();
		List<MycourselistVO> courseList = 
				courseDao.getListCourse(page.getStartRow(), page.getEndRow(), user_num, sort, keyword);
		
		request.setAttribute("count", count);
		request.setAttribute("courseList", courseList);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/mypage/myStudy.jsp";
	}

}
