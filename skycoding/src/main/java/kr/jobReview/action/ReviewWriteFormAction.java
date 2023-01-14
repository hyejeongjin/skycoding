package kr.jobReview.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class ReviewWriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num==null) {//로그인 안 된 경우
			return "redirect:/hmember/loginForm.do";
		}
		//로그인 된 경우
		return "/WEB-INF/views/jobReview/reviewWriteForm.jsp";
	}

}
