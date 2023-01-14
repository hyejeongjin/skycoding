package kr.jobEmploy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class EmployWriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");//session이 문자열로 반환
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");//session이 문자열로 반환
		if(mem_num==null) {//로그인 되지 않은 경우
			return "redirect:/hmember/loginForm.do";
		}
		//로그인 된 경우
		//관리자가 아닌 경우
		if(mem_auth!=9) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		//관리자인 경우
		return "/WEB-INF/views/jobEmploy/employWriteForm.jsp";
	}

}
