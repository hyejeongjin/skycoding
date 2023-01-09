package kr.course.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;


public class CourseHtmlListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//카테고리 번호 반환
		int course_cate = Integer.parseInt(request.getParameter("course_cate"));
		
		CourseDAO courseDAO = CourseDAO.getInstance();
		
		List<CourseVO> courseList =
				courseDAO.getListCourse(1, 16, null, null, course_cate);
		
		//CourseVO courseList = dao.courseList(course_cate);
		
		request.setAttribute("courseList", courseList);
		
		
		
		return "/WEB-INF/views/course/htmlList.jsp";
	}

}


