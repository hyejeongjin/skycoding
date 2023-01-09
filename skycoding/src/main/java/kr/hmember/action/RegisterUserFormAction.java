package kr.hmember.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.hmember.dao.MemberDAO;
import kr.hmember.vo.MemberVO;

public class RegisterUserFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MemberDAO dao = MemberDAO.getInstance();
		List<MemberVO> qnaList = dao.getGnaList();
		
		request.setAttribute("qnaList", qnaList);
		
		return "/WEB-INF/views/member/registerUserForm.jsp";
	}

}
