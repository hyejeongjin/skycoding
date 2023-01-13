package kr.jobReview.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.jobReview.dao.ReviewDAO;
import kr.jobReview.vo.ReviewCommentVO;
import kr.util.PagingUtil;

public class ListCommentAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리(글번호만 전송되기 때문에 생략 가능)
		request.setCharacterEncoding("utf-8");

		//페이지처리
		//reviewDetail.jsp에서 댓글 첫페이지는 pageNum = 1로 지정
		//다음글 버튼 누를때마다 pageNum이 자동으로 +1 돼서 request로 보내짐
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum="1";
		
		int rev_id = Integer.parseInt(request.getParameter("rev_id"));
		
		ReviewDAO dao = ReviewDAO.getInstance();
		int count = dao.getReviewCommentCount(rev_id);
		//ajax 방식으로 목록을 표시하기 때문에 PagingUtil은 페이지수 목적이 아니라 
		//목록 데이터 처리를 위한 rownum 번호를 구하는 것이 목적
		//currentPage,count,rowCount,pageCount,url
		int rowCount = 3;
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum), count, rowCount,1,null);
		
		//댓글 목록 처리
		List<ReviewCommentVO> list = null;
		if(count>0) {
			list = dao.getListReviewComment(page.getStartRow(), page.getEndRow(), rev_id);
		}else {
			list = Collections.emptyList();//빈 List 생성(빈 배열로 전송)
		}
		
		//mem_num값 보내기
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		//count,rowCount는 detail.jsp에서 총 페이지수를 구할 때 사용
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		//로그인한 사람이 작성자인지 체크하기 위해서 전송
		mapAjax.put("mem_num", mem_num);

		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
