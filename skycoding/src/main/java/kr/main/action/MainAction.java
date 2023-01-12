package kr.main.action;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;
 
public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//강의 목록 메인에 보여지도록함
		CourseDAO courseDAO = CourseDAO.getInstance();
	
		List<CourseVO> courseList = 
				courseDAO.getListCourse(1, 5,null, null,1, "1");//표시 강의만 반환 //String sort값 가져와야됨 null(X)문자이므로 ""사용
		request.setAttribute("courseList", courseList);
		return "/WEB-INF/views/main/main.jsp";
	}

}   