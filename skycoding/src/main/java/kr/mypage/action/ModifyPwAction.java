package kr.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mypage.dao.MypageDAO;
import kr.mypage.vo.MypageVO;

public class ModifyPwAction implements Action{

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
		//id
		String id = request.getParameter("id");
		//현재비빌번호
		String origin_passwd = 
				request.getParameter("origin_passwd");
		//새비밀번호
		String passwd = request.getParameter("passwd");
		
		//현재 로그인 한 아이디
		String user_id = (String)session.getAttribute("user_id");
		
		MypageDAO dao = MypageDAO.getInstance();
		MypageVO member = dao.checkMember(id);
		boolean check = false;
		
		//사용자가 입력한 아이디가 존재하고 로그인한 아이디와 일치하는지 체크
		if(member!=null && id.equals(user_id)) {
			//비밀번호 일치 여부 체크
			check = member.isCheckedPassword(origin_passwd);
		}
		
		if(check) {//인증 성공
			//비밀번호 변경
			dao.updatePassword(passwd, user_num);
		}
		
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/mypage/modifyPw.jsp";
	}

}
