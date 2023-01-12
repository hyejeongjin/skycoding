package kr.jobReview.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.jobReview.dao.ReviewDAO;
import kr.jobReview.vo.ReviewVO;
import kr.util.PagingUtil2;

public class ReviewListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//관리자만 글쓰기 버튼 활성화
		HttpSession session = request.getSession();
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		request.setAttribute("mem_auth", mem_auth);
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		 
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		String sort = request.getParameter("sort");
		if(sort==null) sort="1";
		
		//글목록 개수 구하기
		ReviewDAO dao = ReviewDAO.getInstance();
		int count = dao.getReviewCount(keyfield, keyword);
		request.setAttribute("count", count);
		
		//페이지 처리
		//         페이지버튼을 만드는 html 코드 반환
		PagingUtil2 page = new PagingUtil2(keyfield, keyword, Integer.parseInt(pageNum), count, 10,5,"reviewList.do");
		request.setAttribute("page", page.getPage());
		
		//목록구하기
		List<ReviewVO> list = null;
		if(count>0) {
			list = dao.getReviewList(page.getStartRow(), page.getEndRow(), keyfield, keyword,sort);
		}
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/jobReview/reviewList.jsp";
	}
}
