package kr.main.action;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseVO;
import kr.freeboard.dao.FreeBoardDAO;
import kr.freeboard.vo.FreeBoardVO;
import kr.hmember.dao.MemberDAO;
import kr.util.StringUtil;
 
public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//강의 목록 메인에 보여지도록함
		CourseDAO courseDAO = CourseDAO.getInstance();
	  
		List<CourseVO> courseList = 
				courseDAO.getListCourse(1, 3,null, null,1, "1");//표시 강의만 반환 //String sort값 가져와야됨 null(X)문자이므로 ""사용
		List<CourseVO> courseList2 = 
				courseDAO.getListCourse(1, 3,null, null,2, "1");
		List<CourseVO> courseList3 = 
				courseDAO.getListCourse(1, 3,null, null,3, "1");
		List<CourseVO> courseList4 = 
				courseDAO.getListCourse(1, 3,null, null,4, "1");
		
		request.setAttribute("courseList", courseList);
		request.setAttribute("courseList2", courseList2);
		request.setAttribute("courseList3", courseList3);
		request.setAttribute("courseList4", courseList4);
		
		//회원 전체수 구하기
		MemberDAO memberDAO = MemberDAO.getInstance();
		DecimalFormat dec = new DecimalFormat("###,###");
		String count = dec.format(memberDAO.getMemberCount());
		request.setAttribute("count", count);
		
		//자유게시글이 메인에 보여지도록 함
		FreeBoardDAO boardDAO = FreeBoardDAO.getInstance();
		List<FreeBoardVO> boardList = boardDAO.getListBoard(1, 5, null, null, 0, "1");
		request.setAttribute("boardList", boardList);
		
		return "/WEB-INF/views/main/main.jsp";
	}

}           