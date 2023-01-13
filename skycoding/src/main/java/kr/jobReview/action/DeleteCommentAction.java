package kr.jobReview.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.jobReview.dao.ReviewDAO;
import kr.jobReview.vo.ReviewCommentVO;

public class DeleteCommentAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int com_id = Integer.parseInt(request.getParameter("com_id"));
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		//작성자 정보 필요
		ReviewDAO dao = ReviewDAO.getInstance();
		ReviewCommentVO db_reviewComment = dao.getReviewComment(com_id);
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num==null) {//로그인이 안 된 경우
			mapAjax.put("result", "logout");
		}else if(mem_num!=null 
				&& mem_num == db_reviewComment.getMem_num()) {
			//로그인이 되어 있고 로그인 한 회원번호와 작성자 회원번호가
			//일치 
			dao.deleteReviewComment(com_id);
			mapAjax.put("result", "success");
		}else {
			//로그인이 되어 있고 회원번호와 작성자 회원번호가 불일치
			mapAjax.put("result","wrongAccess");
		}
		
		//JSON 데이터 반환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
		
	}

}
