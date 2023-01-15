package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class UpdateEventAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		MultipartRequest multi = FileUtil.createFile(request);
		
		Integer event_id = Integer.parseInt(multi.getParameter("event_id"));
		String event_photo = multi.getFilesystemName("photo");
		
		EventDAO dao = EventDAO.getInstance();
		EventVO db_event = dao.getEvent(event_id);
		
		EventVO event = new EventVO();
		event.setEvent_id(event_id);
		event.setEvent_attr(Integer.parseInt(multi.getParameter("attr")));
		event.setEvent_deadline(multi.getParameter("deadline"));
		event.setEvent_photo(event_photo);
		event.setEvent_content(multi.getParameter("content"));
		event.setEvent_detail_content(multi.getParameter("detail_content"));
		
		dao.updateEvent(event);
		
		if(event_photo != null) {
			//새로운 파일을 선택했을 때 기존에 있던 파일 경로는 삭제
			FileUtil.removeFile(request, db_event.getEvent_photo());
		}
		request.setAttribute("event_id", event_id);
		
		return "/WEB-INF/views/event/updateEvent.jsp";
	}

}
