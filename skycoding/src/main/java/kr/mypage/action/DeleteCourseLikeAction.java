package kr.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mypage.dao.MypageDAO;

public class DeleteCourseLikeAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("mem_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.jsp";
		}
		
		//로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String[] cboxs = request.getParameterValues("cbox");
		
		MypageDAO dao = MypageDAO.getInstance();
		
		for(int i=0; i<cboxs.length; i++)
			dao.deleteLike(Integer.parseInt(cboxs[i]), user_num);
		
		return "/WEB-INF/views/mypage/deleteBookmark.jsp";
	}

}
