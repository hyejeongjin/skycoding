package kr.mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.mypage.dao.MypageDAO;
import kr.mypage.vo.MypageVO;

public class ModifyProfileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//자바빈(VO)을 생성, 전송된 데이터를 저장
		MypageVO member = new MypageVO();
		member.setMem_num(user_num);//회원번호
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		
		MypageDAO dao = MypageDAO.getInstance();
		dao.updateMember(member);
		
		return "/WEB-INF/views/mypage/modifyProfile.jsp";
	}

}
