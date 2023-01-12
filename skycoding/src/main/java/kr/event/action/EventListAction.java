package kr.event.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.PagingUtil2;

public class EventListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		EventDAO eventDao = EventDAO.getInstance();
		
		//진행중 이벤트 추출
		List<EventVO> eventList = eventDao.getEventList(1, 2, null, null, 1);
		
		//종료된 이벤트 추출
		List<EventVO> eventList2 = eventDao.getEventList(1, 2, null, null, 0);
		
		//진행중인 이벤트의 deadline과 현재 날짜 일수 계산
		List<Integer> diffDayList = new ArrayList<>();
		for(EventVO event : eventList) {
			diffDayList.add(eventDao.getDiffDate(event.getEvent_id()));
		}
		
		request.setAttribute("list", eventList);
		request.setAttribute("list2", eventList2);
		request.setAttribute("diffDay", diffDayList);
		
		return "/WEB-INF/views/event/eventList.jsp";
	}
}