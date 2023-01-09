package kr.jobReview.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.jobReview.dao.ReviewDAO;
import kr.jobReview.vo.ReviewVO;
import kr.util.StringUtil;

public class ReviewUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num==null) {//로그인 안 된 경우
			return "redirect:/member/loginForm.jsp";
		}
		//로그인 된 경우
		int rev_id = Integer.parseInt(request.getParameter("rev_id"));
		ReviewDAO dao = ReviewDAO.getInstance();
		ReviewVO review = dao.getReviewDetail(rev_id);
		
		if(mem_num!=review.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		review.setRev_title(StringUtil.parseQuot(review.getRev_title()));
		request.setAttribute("review", review);
		
		return "/WEB-INF/views/jobReview/reviewUpdateForm.jsp";
	}

}
