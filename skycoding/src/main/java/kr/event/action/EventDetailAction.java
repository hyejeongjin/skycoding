package kr.event.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.StringUtil;

public class EventDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		Integer event_id = Integer.parseInt(request.getParameter("event_id"));
		EventDAO dao = EventDAO.getInstance();
		
		//누르는 순간 조회수 증가
		dao.addEventView(event_id);
		
		//event_id에 해당하는 게시글 - 마감일까지 남은 일수 추출
		EventVO event = dao.getDetailEvent(event_id);
		Integer event_diffDay = dao.getDiffDate(event_id);
		
		//요약내용, 세부내용 StringUtil처리
		event.setEvent_content(StringUtil.useNoHtml(event.getEvent_content()));
		event.setEvent_detail_content(StringUtil.useBrNoHtml(event.getEvent_detail_content()));
		
		request.setAttribute("event", event);
		request.setAttribute("event_diffDay", event_diffDay);
		
		return "/WEB-INF/views/event/eventDetail.jsp";
	}

}
