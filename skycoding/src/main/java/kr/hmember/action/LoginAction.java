package kr.hmember.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.hmember.dao.MemberDAO;
import kr.hmember.vo.MemberVO;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("UTF-8");

		//전송된 데이터 반환
		String id = request.getParameter("mem_id");
		String passwd = request.getParameter("mem_pw");

		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;

		if(member!=null) {
			//비밀번호 일치 여부 체크
			check = member.isCheckedPassword(passwd);

			//로그인 실패 시 auth 체크용
			request.setAttribute("auth", member.getMem_auth());
		}
		if(check) {//인증 성공
			//로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("mem_num", member.getMem_num());
			session.setAttribute("mem_id", member.getMem_id());
			session.setAttribute("mem_auth", member.getMem_auth());

			//인증 성공 시 호출
			return "redirect:/main/main.do";
		}

		//인증 실패 시 호출
		return "/WEB-INF/views/hmember/login.jsp";
	}

}
