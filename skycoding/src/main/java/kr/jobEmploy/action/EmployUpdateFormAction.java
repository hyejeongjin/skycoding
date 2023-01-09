package kr.jobEmploy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.jobEmploy.dao.EmployDAO;
import kr.jobEmploy.vo.EmployVO;
import kr.util.StringUtil;

public class EmployUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		if(mem_num==null) {//로그인 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		if(mem_auth!=9) {
			return "/WEB-INF/views/common/notice.jsp";
		}else {
			//글번호 반환
			int emp_id = Integer.parseInt(request.getParameter("emp_id"));
			
			EmployDAO dao = EmployDAO.getInstance();
			EmployVO employ = dao.getEmployDetail(emp_id);
			
			//링크를 알아내서 타인이 수정,삭제하는 것을 방지하기 위해 체크
			//로그인한 회원번호와 작성자 회원번호가 불일치
			if(mem_num!=employ.getMem_num()) {
				return "/WEB-INF/views/common/notice.jsp";
			}
			//로그인이 되어 있고 로그인한 회원번호와 작성자 회원번호가 일치
			//제목에 큰 따옴표가 있으면 input 태그에 데이터를 표시할 때 오작동하기 때문에 -> &quot;로 변환
			employ.setEmp_title(StringUtil.parseQuot(employ.getEmp_title()));
			request.setAttribute("employ", employ);
			return "/WEB-INF/views/jobEmploy/employUpdateForm.jsp";
		}
		
	}

}
