package kr.qnaboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.qnaboard.dao.QnaBoardDAO;
import kr.qnaboard.vo.QnaBoardReplyVO;
import kr.controller.Action;

public class UpdateReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//댓글 번호
		int qnaComm_id = Integer.parseInt(request.getParameter("qnaComm_id"));
		
		QnaBoardDAO dao = QnaBoardDAO.getInstance();
		//작성자 회원번호 구하기
		QnaBoardReplyVO db_reply = dao.getReplyBoard(qnaComm_id);
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		Integer user_auth = (Integer)session.getAttribute("mem_auth");
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		if(user_num == null || user_auth == 0) { //로그인 안 되어있는 경우
			mapAjax.put("result", "logout");
		}else if(user_num!=null && user_num == db_reply.getMem_num()){
			//로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가 일치
			QnaBoardReplyVO reply = new QnaBoardReplyVO();
			reply.setQnaComm_id(qnaComm_id);
			reply.setQnaComm_content(request.getParameter("qnaComm_content"));
			
			//댓글 수정
			dao.updateReplyBoard(reply);
			
			mapAjax.put("result", "success");
		}else { //로그인은 되어있지만 로그인한 회원번호와 작성자 회원번호가 불일치
			mapAjax.put("result", "wrongAccess");
		}
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
