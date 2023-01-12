package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class RegisterEventAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//세션에서 user_num 얻어오기
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		MultipartRequest multi = FileUtil.createFile(request);
		EventVO eventVo = new EventVO();
		eventVo.setMem_num(user_num);
		eventVo.setEvent_course_id(Integer.parseInt(multi.getParameter("course_id")));
		eventVo.setEvent_attr(Integer.parseInt(multi.getParameter("attr")));
		eventVo.setEvent_deadline(multi.getParameter("deadline"));
		eventVo.setEvent_photo(multi.getFilesystemName("photo"));
		eventVo.setEvent_content(multi.getParameter("content"));
		eventVo.setEvent_detail_content(multi.getParameter("detail_content"));
		
		//DB에 추가
		EventDAO eventDao = EventDAO.getInstance();
		eventDao.registerEvent(eventVo);
		
		request.setAttribute("eventVo", eventVo);
		request.setAttribute("user_num", user_num);
		
		return "/WEB-INF/views/event/registerEvent.jsp";
	}

}
