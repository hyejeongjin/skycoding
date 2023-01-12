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

public class UpdateCommentAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//댓글번호
		int com_id = Integer.parseInt(request.getParameter("com_id"));
		
		ReviewDAO dao = ReviewDAO.getInstance();
		//작성자의 회원번호 구하기
		ReviewCommentVO db_reviewComment = dao.getReviewComment(com_id);
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		if(mem_num==null) {
			mapAjax.put("result", "logout");
		}else if(mem_num!=null && mem_num==db_reviewComment.getMem_num()) {
			ReviewCommentVO reviewComment = new ReviewCommentVO();
			reviewComment.setCom_id(com_id);
			reviewComment.setCom_content(request.getParameter("com_content"));
			dao.updateReviewComment(reviewComment);
			
			mapAjax.put("result", "success");
		}else {
			mapAjax.put("result", "wrongAccess");
		}
		//JSON 데이터 반환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
