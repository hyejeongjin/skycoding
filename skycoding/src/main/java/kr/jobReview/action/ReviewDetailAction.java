package kr.jobReview.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.jobReview.dao.ReviewDAO;
import kr.jobReview.vo.ReviewVO;
import kr.util.StringUtil;

public class ReviewDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rev_id = Integer.parseInt(request.getParameter("rev_id"));

		ReviewDAO dao = ReviewDAO.getInstance();
		//조회수 증가
		dao.updateReadCount(rev_id);
		
		//글상세보기
		ReviewVO review = dao.getReviewDetail(rev_id);
		review.setRev_title(StringUtil.useNoHtml(review.getRev_title()));
		review.setRev_content(StringUtil.useBrNoHtml(review.getRev_content()));
		
		request.setAttribute("review", review);
		
		
		return "/WEB-INF/views/jobReview/reviewDetail.jsp";
	}

}
