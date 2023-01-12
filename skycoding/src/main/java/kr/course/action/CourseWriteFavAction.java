package kr.course.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.course.dao.CourseDAO;
import kr.course.vo.CourseFavVO;

public class CourseWriteFavAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax =  
				new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("mem_num");
		if(user_num==null) {//로그인이 되지않는 경우
			mapAjax.put("result", "logout");
		}else {
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			//전송된 데이터 반환
			int course_id = Integer.parseInt(
					    request.getParameter("course_id"));
			
			CourseFavVO favVO = new CourseFavVO();
			favVO.setCourse_id(course_id);
			favVO.setMem_num(user_num);
		    
			CourseDAO dao = CourseDAO.getInstance();
			//좋아요 등록 여부 체크
			CourseFavVO db_fav = dao.selectFav(favVO);
			if(db_fav!=null) {//좋아요 등록 O
				//좋아요 삭제
				dao.deleteFav(db_fav.getLike_num());
				mapAjax.put("result", "success");
				mapAjax.put("status", "noFav");
				mapAjax.put("count", dao.selectFavCount(course_id));
			}else {//좋아요 등록 X
				//좋아요 등록
				dao.insertFav(favVO);
				mapAjax.put("result", "success");
				mapAjax.put("status", "yesFav");
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
