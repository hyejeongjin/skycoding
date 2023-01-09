package kr.jobEmploy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.jobEmploy.dao.EmployDAO;
import kr.jobEmploy.vo.EmployVO;
import kr.util.StringUtil;

public class EmployDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//관리자만 수정,삭제 버튼 보이게 하기위해 auth값 전달
		HttpSession session = request.getSession();
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		request.setAttribute("user_auth", user_auth);
		
		//글 번호 반환
		int emp_id = Integer.parseInt(request.getParameter("emp_id"));
		
		EmployDAO dao = EmployDAO.getInstance();
		//조회수 증가
		dao.UpdateReadCount(emp_id);
		
		EmployVO employ = dao.getEmployDetail(emp_id);
		
		//HTML태그를 허용하지 않음
		employ.setEmp_title(StringUtil.useNoHtml(employ.getEmp_title()));
		//HTML태그를 허용하지 않으면서 줄바꿈 처리
		employ.setEmp_content(StringUtil.useBrNoHtml(employ.getEmp_content()));
		
		request.setAttribute("employ", employ);
		
		return "/WEB-INF/views/jobEmploy/employDetail.jsp";
	}

}
