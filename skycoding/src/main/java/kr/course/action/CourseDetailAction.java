package kr.course.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;
import kr.util.StringUtil;

public class CourseDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//강의 번호 반환
		int course_id = Integer.parseInt(
				   request.getParameter("course_id"));
	
		CourseDAO dao = CourseDAO.getInstance();
		
		//조회수 증가
        dao.UpdateReadCount(course_id);
        
		CourseVO course = dao.getCourse(course_id);
		
		
		
		//줄바꿈 처리
		course.setCourse_content(
				StringUtil.useBrHtml(course.getCourse_content()));
		
		request.setAttribute("course", course);
				
		return "/WEB-INF/views/course/detail.jsp";
	}

}
