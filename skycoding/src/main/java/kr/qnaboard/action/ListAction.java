package kr.qnaboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.qnaboard.vo.QnaBoardVO;
import kr.controller.Action;
import kr.qnaboard.dao.QnaBoardDAO;
import kr.util.PagingUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1"; //첫페이지에서
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		QnaBoardDAO dao = QnaBoardDAO.getInstance();
		int count = dao.getBoardCount(keyfield, keyword);
		
		//페이지 처리
		//keyfield,keyword,currentPage,count,rowCount,pageCount,url 순으로 작성
		//keyfield,keyword를 넣는 이유 : 얘네를 유지한 상태로 페이지 처리를 해야 하기 때문에
		//rowCount:한 페이지 당 보여줄 게시물 개수, PageCount:아래쪽에 보이는 페이지 개수 
		PagingUtil page = new PagingUtil(keyfield,keyword,
										 Integer.parseInt(pageNum),
										 count,20,10,"list.do");
		
		List<QnaBoardVO> list= null;
		if(count > 0) {
			list = dao.getListBoard(page.getStartRow(),
									page.getEndRow(),
									keyfield,keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		
		return "/WEB-INF/views/board_qna/list.jsp";
	}

}
