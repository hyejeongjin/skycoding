package kr.course.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.course.dao.CourseDAO;
import kr.course.vo.CourseFavVO;
import kr.controller.Action;

public class CourseGetFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int course_id = Integer.parseInt(request.getParameter("course_id"));
		
		Map<String,Object> mapAjax =
				           new HashMap<String,Object>();
		//로그인이 된 경우
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("mem_num");
		CourseDAO dao = CourseDAO.getInstance();
		
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("status", "noFav");
			mapAjax.put("count", dao.selectFavCount(course_id));
		}else {//로그인 된 경우
			CourseFavVO favVO = new CourseFavVO();
			favVO.setCourse_id(course_id);
			favVO.setMem_num(user_num);
			//좋아요 선택 여부 체크
			CourseFavVO CourseFav = dao.selectFav(favVO);
			if(CourseFav!=null) {//좋아요가 등록되어 있음
				mapAjax.put("status", "yesFav");
				mapAjax.put("count", dao.selectFavCount(course_id));
			}else {//좋아요가 등록되지 않음
				mapAjax.put("status", "noFav");
				mapAjax.put("count", dao.selectFavCount(course_id));
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = 
				    mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
