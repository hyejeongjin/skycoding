package kr.event.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.event.dao.EventDAO;
import kr.event.vo.EventVO;
import kr.util.FileUtil;

public class DeleteEventFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		int event_id = Integer.parseInt(request.getParameter("event_id"));
		EventDAO eventDao = EventDAO.getInstance();
		EventVO event = eventDao.getEvent(event_id);
		
		//해당 글에서 기존 파일 경로를 지움
		eventDao.deleteEventFile(event_id);
		//파일 삭제
		FileUtil.removeFile(request, event.getEvent_photo());
		
		//제대로 지워졌으면 map추가
		mapAjax.put("result", "success");
		
		
		//JSON 데이터 생성
				ObjectMapper mapper = new ObjectMapper();
				String ajaxData = mapper.writeValueAsString(mapAjax);
				
				request.setAttribute("ajaxData", ajaxData);
				
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
