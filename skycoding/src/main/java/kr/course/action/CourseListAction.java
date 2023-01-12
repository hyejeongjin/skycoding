package kr.course.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;
import kr.util.PagingUtil2;


public class CourseListAction implements Action {

	@Override             
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//카테고리 번호 반환        
		String course_cate = request.getParameter("course_cate");
		if(course_cate == null) course_cate = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		//dropdown sort 추가     
		String sort = request.getParameter("sort");
		if(sort == null) sort="1";
		
		//총 강의 목록 개수 구하기
		CourseDAO dao = CourseDAO.getInstance();
		int count = dao.getCourseCount(keyfield, keyword,Integer.parseInt(course_cate));
		
		//페이지처리
		//keyfield,keyword,currentPage,count,rowCount,
		//pageCount,url
		PagingUtil2 page = 
				new PagingUtil2(keyfield,keyword,
						      Integer.parseInt(course_cate),
						          count,8,3,"list.do");
		//dropdown 
		List<CourseVO> courseList = null;
		if(count > 0) {
			courseList= dao.getListCourse(page.getStartRow(),
					                page.getEndRow(),           //dropdown sort 추가 
					                keyfield,keyword, Integer.parseInt(course_cate),sort);
			
			
		}
		request.setAttribute("count", count);
		request.setAttribute("list", courseList);
		request.setAttribute("page", page.getPage());

		
		
		return "/WEB-INF/views/course/list.jsp";
	}

}




