package kr.jobEmploy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.controller.Action;
import kr.jobEmploy.dao.EmployDAO;
import kr.jobEmploy.vo.EmployVO;
import kr.util.PagingUtil2;

public class EmployListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1"; //메인에서 list.do를 호출할 때
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		//총 글 목록 개수 구하기
		EmployDAO dao = EmployDAO.getInstance();
		int count = dao.getEmployCount(keyfield, keyword);
 
		//페이지처리
		//keyfield,keyword,currentPage,count,rowCount,pageCount,url
		PagingUtil2 page = new PagingUtil2(keyfield, keyword, Integer.parseInt(pageNum), count, 8, 3, "employList.do");
		
		//목록 구하기
		List<EmployVO> list = null;
		if(count>0) {
			list = dao.getEmployList(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		
		return "/WEB-INF/views/jobEmploy/employList.jsp";
	}

}
