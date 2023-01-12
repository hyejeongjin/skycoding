package kr.freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class WriteFormAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		Integer user_auth = (Integer)session.getAttribute("mem_auth");
		
		if(user_num == null || user_auth == 0) { //로그인이 되지 않은 경우
			return "redirect:/hmember/loginForm.do";
		}
		
		//로그인 된 경우
		return "/WEB-INF/views/board_free/writeForm.jsp";
	}
}