package kr.hmember.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.hmember.dao.MemberDAO;

public class FindIdAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");

		//전송된 데이터 반환
		String email = request.getParameter("mem_email");
		String cell = request.getParameter("mem_cell");
		
		MemberDAO dao = MemberDAO.getInstance();
		String my_id = dao.findId(email, cell);
		
		request.setAttribute("my_id", my_id);
	
		return "/WEB-INF/views/hmember/findId.jsp";
	}
}
