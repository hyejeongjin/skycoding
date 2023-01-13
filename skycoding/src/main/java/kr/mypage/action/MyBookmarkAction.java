package kr.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mypage.vo.MypageVO;
import kr.util.PagingUtil2;
import kr.mypage.dao.MypageDAO;
import kr.mypage.vo.MycourselikeVO;

import java.util.List;

public class MyBookmarkAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1"; //메인에서 list.do를 호출할 때
		String keyword = request.getParameter("query"); //강좌명 검색
		String sort = request.getParameter("sort"); //정렬 기준
		
		HttpSession session = request.getSession();
		Integer user_num = 
				 (Integer)session.getAttribute("mem_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우(혜정님 MemberDAO, MemberVo 가져옴) 
		MypageDAO dao = MypageDAO.getInstance();
		int count = dao.getCourselikeCount(user_num, null);

		PagingUtil2 page = new PagingUtil2(null, keyword, Integer.parseInt(pageNum), count, 3, 3, "myBookmark.do");
		
		//내가 선택한 강좌 좋아요 목록
		MypageDAO courselikeDao = MypageDAO.getInstance();
		List<MycourselikeVO> courselikeList = 
				courselikeDao.getListCourseFav(page.getStartRow(), page.getEndRow(), user_num, sort, keyword);
		
		request.setAttribute("count", count);
		request.setAttribute("courselikeList", courselikeList);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/mypage/myBookmark.jsp";
	}

}
