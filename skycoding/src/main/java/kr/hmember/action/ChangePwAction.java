package kr.hmember.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.hmember.dao.MemberDAO;

public class ChangePwAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//데이터 인코딩
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		String newPw = request.getParameter("new_pw");
		String id = request.getParameter("mem_id");
		
		MemberDAO dao = MemberDAO.getInstance();
		newPw = dao.changePassword(newPw, id);
		
		request.setAttribute("newPw", newPw);
		
		return "/WEB-INF/views/hmember/loginForm.jsp";
	}
}
