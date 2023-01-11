package kr.news.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
 
public class UpdateFormAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = 
				(Integer)session.getAttribute("mem_num");
		if(mem_num == null) {//로그인 되지 않은 경우
			return "redirect:/hmember/loginForm.do";
		}
		
		//로그인 된 경우
		//글번호 반환
				int news_id = Integer.parseInt(
						       request.getParameter("news_id"));
				
				NewsDAO dao = NewsDAO.getInstance();
				NewsVO news = dao.getNews(news_id);
				Integer mem_auth = 
						(Integer)session.getAttribute("mem_auth");
				if(mem_auth<9) {//관리자로 로그인하지 않은 경우
					return "/WEB-INF/views/common/notice.jsp";
				}
				request.setAttribute("news", news);
		
		return  "/WEB-INF/views/news/updateForm.jsp";
	
}
}