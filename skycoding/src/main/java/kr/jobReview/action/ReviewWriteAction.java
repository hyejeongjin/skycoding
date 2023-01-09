package kr.jobReview.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.jobReview.dao.ReviewDAO;
import kr.jobReview.vo.ReviewVO;
import kr.util.FileUtil;

public class ReviewWriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		
		//전송된 데이터 반환
		String rev_title = multi.getParameter("title");
		String rev_photo = multi.getFilesystemName("photo");
		String rev_content = multi.getParameter("content");
		
		ReviewVO review = new ReviewVO();
		review.setMem_num(user_num);
		review.setRev_title(rev_title);
		review.setRev_photo(rev_photo);
		review.setRev_content(rev_content);
		
		ReviewDAO dao = ReviewDAO.getInstance();
		dao.insertReview(review);
		
		return "/WEB-INF/views/jobReview/reviewWrite.jsp";
	}

}
