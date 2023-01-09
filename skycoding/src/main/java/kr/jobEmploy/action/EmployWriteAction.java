package kr.jobEmploy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.jobEmploy.dao.EmployDAO;
import kr.jobEmploy.vo.EmployVO;
import kr.util.FileUtil;

public class EmployWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num==null) {//로그인 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우
		//관리자가 아닌 경우
		if(user_auth!=9) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		//관리자인 경우
		MultipartRequest multi = FileUtil.createFile(request);
		
		EmployVO employ = new EmployVO();
		employ.setEmp_title(multi.getParameter("title"));
		employ.setEmp_content(multi.getParameter("content"));
		employ.setEmp_photo(multi.getFilesystemName("photo"));
		employ.setMem_num(user_num);
		
		EmployDAO dao = EmployDAO.getInstance();
		dao.insertEmploy(employ);
		
		return "/WEB-INF/views/jobEmploy/employWrite.jsp";
	}

}
