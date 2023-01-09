package kr.course.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;

import kr.util.FileUtil;

public class CourseWriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth = 
				(Integer)session.getAttribute("user_auth");
		if(user_auth<9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		//관리자로 로그인한 경우
		MultipartRequest multi = 
		        FileUtil.createFile(request);
		CourseVO course = new CourseVO();
		course.setMem_num(user_num);
		course.setCourse_name(multi.getParameter("course_name"));
		course.setCourse_content(multi.getParameter("course_content"));
		course.setCourse_tr(multi.getParameter("course_tr"));
		course.setCourse_cate(Integer.parseInt(
				multi.getParameter("course_cate")));
		course.setCourse_photo(multi.getFilesystemName("course_photo"));

		CourseDAO dao = CourseDAO.getInstance();
		dao.insertCourse(course);

		return "/WEB-INF/views/course/write.jsp";
	}

}
