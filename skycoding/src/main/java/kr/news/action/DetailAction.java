package kr.news.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
				int news_id = Integer.parseInt(
						       request.getParameter("news_id"));
				
				NewsDAO dao = NewsDAO.getInstance();
		//조회수 증가
		dao.updateReadcount(news_id);
		
		NewsVO news = dao.getNews(news_id);
		
		//HTML 태그를 허용하지 않음
	    news.setNews_title(StringUtil.useNoHtml(news.getNews_title()));
		//HTML 태그를 허용하지 않으면서 줄바꿈 처리
		news.setNews_content(
				StringUtil.useBrNoHtml(news.getNews_content()));
	
				request.setAttribute("news", news);
				
				return "/WEB-INF/views/news/detail.jsp";
	}

}
