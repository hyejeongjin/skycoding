package kr.qnaboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.qnaboard.dao.QnaBoardDAO;
import kr.qnaboard.vo.QnaBoardReplyVO;

public class DeleteReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터(re_num) 인코딩(숫자라서 생략가능)
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int qnaComm_id = Integer.parseInt(request.getParameter("qnaComm_id"));
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		QnaBoardDAO qnaDao = QnaBoardDAO.getInstance();
		//작성자 회원번호 구하기
		QnaBoardReplyVO db_reply = qnaDao.getReplyBoard(qnaComm_id);
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		Integer user_auth = (Integer)session.getAttribute("mem_auth");
		
		if(user_num==null || user_auth == 0) { //로그인이 안 된 경우
			mapAjax.put("result", "logout");
		}else if(user_num!=null && user_num==db_reply.getMem_num()) {
			//로그인 되어있고 회원번호와 댓글작성자 회원번호가 일치하는 경우
			qnaDao.deleteReplyBoard(qnaComm_id);
			mapAjax.put("result", "success");
		}else {
			//로그인이 되어있으나 회원번호와 댓글작성자 회원번호가 불일치하는 경우
			mapAjax.put("result", "wrongAccess");
		}
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
