package kr.cart.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;

public class CheckApplicationAction implements Action {

	@Override   
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");

		//전송된 데이터 반환
		int course_id = Integer.parseInt(request.getParameter("course_id"));

		CartDAO dao = CartDAO.getInstance();
		CartVO cart = dao.checkCart(course_id,mem_num);

		Map<String, String> mapAjax = new HashMap<String, String>();

		if(cart == null) {//수강신청 미중복
			mapAjax.put("result", "courseNotFound");
		}else { //수강신청 중복
			mapAjax.put("result", "courseDuplicated");
		}

		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}






}
