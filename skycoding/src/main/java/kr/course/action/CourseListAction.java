package kr.course.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;
import kr.util.PagingUtil;


public class CourseListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//카테고리 번호 반환
		String course_cate = request.getParameter("course_cate");
		if(course_cate == null) course_cate = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		CourseDAO dao = CourseDAO.getInstance();
		int count = dao.getCourseCount(keyfield, keyword,Integer.parseInt(course_cate));
		
		//페이지처리
		//keyfield,keyword,currentPage,count,rowCount,
		//pageCount,url
		PagingUtil page = 
				new PagingUtil(keyfield,keyword,
						      Integer.parseInt(course_cate),
						          count,20,10,"list.do");
		
		List<CourseVO> courseList = null;
		if(count > 0) {
			courseList= dao.getListCourse(page.getStartRow(),
					                page.getEndRow(),
					                keyfield,keyword, Integer.parseInt(course_cate));
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", courseList);
		request.setAttribute("page", page.getPage());
		
		
		return "/WEB-INF/views/course/list.jsp";
	}

}


