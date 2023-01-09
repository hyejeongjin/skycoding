package kr.news.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.controller.Action;
import kr.util.PagingUtil;


public class ListAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		return "/WEB-INF/views/news/list.jsp";
	}

}
