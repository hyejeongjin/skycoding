package kr.freeboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.freeboard.dao.FreeBoardDAO;
import kr.freeboard.vo.FreeBoardVO;
import kr.util.PagingUtil2;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		Integer user_auth = (Integer)session.getAttribute("mem_auth");
		
		if(user_num == null || user_auth == 0) { //로그인이 되지 않은 경우
			return "redirect:/hmember/loginForm.do";
		}
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1"; //첫페이지에서
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		FreeBoardDAO dao = FreeBoardDAO.getInstance();
		int count = dao.getBoardCount(keyfield, keyword);
		
		//페이지 처리
		//keyfield,keyword,currentPage,count,rowCount,pageCount,url 순으로 작성
		//keyfield,keyword를 넣는 이유 : 얘네를 유지한 상태로 페이지 처리를 해야 하기 때문에
		//rowCount:한 페이지 당 보여줄 게시물 개수, PageCount:아래쪽에 보이는 페이지 개수 
		PagingUtil2 page = new PagingUtil2(keyfield,keyword,
										 Integer.parseInt(pageNum),
										 count,20,10,"list.do");
		
		List<FreeBoardVO> list= null;
		if(count > 0) {
			list = dao.getListBoard(page.getStartRow(),
									page.getEndRow(),
									keyfield,keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		
		return "/WEB-INF/views/board_free/list.jsp";
	}

}
