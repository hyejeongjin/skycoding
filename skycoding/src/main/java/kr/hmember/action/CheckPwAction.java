package kr.hmember.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.hmember.dao.MemberDAO;
public class CheckPwAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		String id = request.getParameter("mem_id");
		
		MemberDAO dao = MemberDAO.getInstance();
		String msg = dao.findQuest(id);
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		if(msg == null) {//찾기 실패
			mapAjax.put("result", "failure");
		}else { //찾기 성공
			mapAjax.put("result", "success");
			mapAjax.put("qna_detail", msg);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
