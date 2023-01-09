package kr.jobReview.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.jobReview.dao.ReviewDAO;
import kr.jobReview.vo.ReviewVO;

public class ReviewDeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num==null) {//로그인이 안 된 경우
			mapAjax.put("result","logout");
		}else {//로그인 된 경우
			int rev_id = Integer.parseInt(request.getParameter("rev_id"));
			ReviewDAO dao = ReviewDAO.getInstance();
			ReviewVO review = dao.getReviewDetail(rev_id);
			
			if(mem_num!=review.getMem_num()) {
				mapAjax.put("result", "wrongAccess");
			}else {
				dao.deleteFile(rev_id);//테이블에서 파일명만 삭제
				mapAjax.put("result", "success");
			}
		}
			//JSON 데이터 생성
			ObjectMapper mapper = new ObjectMapper();
			String ajaxData = mapper.writeValueAsString(mapAjax);
			request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
