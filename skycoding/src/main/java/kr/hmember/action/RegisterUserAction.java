package kr.hmember.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.hmember.dao.MemberDAO;
import kr.hmember.vo.MemberVO;

public class RegisterUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 저장
		MemberVO hmember = new MemberVO();
		hmember.setMem_id(request.getParameter("mem_id"));
		hmember.setMem_name(request.getParameter("mem_name"));
		hmember.setMem_pw(request.getParameter("mem_pw"));
		hmember.setMem_pwq(Integer.parseInt(request.getParameter("mem_pwq")));
		hmember.setMem_pwa(request.getParameter("mem_pwa"));
		hmember.setMem_cell(request.getParameter("mem_cell"));
		hmember.setMem_email(request.getParameter("mem_email"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(hmember);
		
		
		List<MemberVO> qnaList = null;
		request.setAttribute("qnaList", qnaList);
		
		//경로 지정
		return "/WEB-INF/views/member/registerUser.jsp";
	}

}
