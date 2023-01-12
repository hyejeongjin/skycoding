package kr.event.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.event.dao.EventDAO;

public class RegisterEventFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		//강의 course_id 목록을 넘겨받아야함
		HttpSession session = request.getSession();
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		//관리자로 로그인했을 경우에만 
		if(user_num == 62 && user_auth == 9) {
			//관리자가 등록한 강의의 강의 고유번호, 강의 이름 얻어오기
			EventDAO dao = EventDAO.getInstance();
			Map<String, String> courseMap = dao.getCourse(user_num);
			
			request.setAttribute("courseMap", courseMap);
		}
		
		return "/WEB-INF/views/event/registerEventForm.jsp";
	}

}
