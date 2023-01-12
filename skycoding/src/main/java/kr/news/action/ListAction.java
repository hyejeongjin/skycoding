package kr.news.action;

import java.util.List;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.controller.Action;
import kr.util.PagingUtil2;


public class ListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null)pageNum = "1";
	
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		
		
		NewsDAO dao = NewsDAO.getInstance();
		int count = dao.getNewsCount(keyfield, keyword);
		  
		//페이지 처리
		//keyfield,keyword,currentPage,count,rowCount,
		//pageCount,url
		PagingUtil2 page = 
				new PagingUtil2(keyfield,keyword,
						      Integer.parseInt(pageNum),
						          count,10,3,"list.do");//count,한페이지 보이는갯수,한 화면에 보여지는 페이지 번호수(ex--<123>... <456>)
		List<NewsVO> list = null;
		if(count > 0) {
			list = dao.getListNews(page.getStartRow(),
					                page.getEndRow(),
					                keyfield,keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/news/list.jsp";
	}

}
