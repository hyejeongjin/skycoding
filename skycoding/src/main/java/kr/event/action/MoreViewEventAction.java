package kr.event.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.PagingUtil2;

public class MoreViewEventAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		String sort = request.getParameter("sort");
		if(sort == null) sort="1";
		
		int attr = Integer.parseInt(request.getParameter("attr"));
		
		EventDAO eventDao = EventDAO.getInstance();
		int count = eventDao.getTotalEvent(attr);
		
		PagingUtil2 page = new PagingUtil2(keyfield, keyword, Integer.parseInt(pageNum), count, 3, 4, "moreViewEvent.do","&attr="+attr);
		List<EventVO> eventList = null;
		if(count > 0) {
			eventList = eventDao.getEventList(page.getStartRow(), page.getEndRow(), keyfield, keyword, attr, sort);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", eventList);
		request.setAttribute("page", page.getPage());
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("attr", attr);
		
		return "/WEB-INF/views/event/moreViewEvent.jsp";
	}
	
}
