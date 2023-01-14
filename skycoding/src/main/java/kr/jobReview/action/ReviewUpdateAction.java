package kr.jobReview.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.jobReview.dao.ReviewDAO;
import kr.jobReview.vo.ReviewVO;
import kr.util.FileUtil;

public class ReviewUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		if(mem_num==null) {//로그인 안 된 경우
			return "redirect:/hmember/loginForm.do";
		}
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		int rev_id = Integer.parseInt(multi.getParameter("rev_id"));
		String rev_photo = multi.getFilesystemName("rev_photo");
		
		ReviewDAO dao = ReviewDAO.getInstance();
		ReviewVO db_review = dao.getReviewDetail(rev_id);
		
		if(mem_num!=db_review.getMem_num()) {
			FileUtil.removeFile(request, rev_photo);
			return "/WEB-INF/views/common/notice.jsp";
		}
		ReviewVO review = new ReviewVO();
		review.setRev_id(rev_id);
		review.setRev_title(multi.getParameter("rev_title"));
		review.setRev_content(multi.getParameter("rev_content"));
		review.setRev_photo(rev_photo);
		
		dao.updateReview(review);
		
		return "redirect:/jobReview/reviewDetail.do?rev_id="+rev_id;
	}

}
