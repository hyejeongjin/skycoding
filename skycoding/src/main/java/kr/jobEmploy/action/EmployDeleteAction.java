package kr.jobEmploy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.jobEmploy.dao.EmployDAO;
import kr.jobEmploy.vo.EmployVO;
import kr.util.FileUtil;

public class EmployDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num==null){//로그인 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//글번호 반환
		int emp_id = Integer.parseInt(request.getParameter("emp_id"));
		EmployDAO dao = EmployDAO.getInstance();
		EmployVO db_employ = dao.getEmployDetail(emp_id);
		
		//관리자가 아닌 경우
		if(user_auth!=9) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		//관리자인 경우
		dao.deleteEmploy(emp_id);
		//파일 삭제
		FileUtil.removeFile(request,db_employ.getEmp_photo());
		
		return "redirect:/jobEmploy/employList.do";
	}

}
