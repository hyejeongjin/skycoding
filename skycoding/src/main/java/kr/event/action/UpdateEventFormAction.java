package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.StringUtil;

public class UpdateEventFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Integer event_id = Integer.parseInt(request.getParameter("event_id"));
		EventDAO eventDao = EventDAO.getInstance();
		EventVO event = eventDao.getDetailEvent(event_id);
		event.setEvent_content(StringUtil.useNoHtml(event.getEvent_content()));
		event.setEvent_detail_content(StringUtil.useNoHtml(event.getEvent_detail_content()));
		
		request.setAttribute("event", event);
		
		return "/WEB-INF/views/event/updateEventForm.jsp";
	}

}
