package kr.news.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;
import kr.controller.Action;

public class DeleteAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = 
				(Integer)session.getAttribute("mem_num");
		if(mem_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		int news_id = Integer.parseInt(
		          request.getParameter("news_id"));
		//로그인 된 경우
		NewsDAO dao = NewsDAO.getInstance();
		//수정전 데이터 호출
		NewsVO db_news = dao.getNews(news_id);
		
		Integer mem_auth = 
				(Integer)session.getAttribute("mem_auth");
		if(mem_auth<9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//관리자 로그인
		dao.deleteNews(news_id);
		//파일 삭제
				FileUtil.removeFile(request, db_news.getNews_photo());
				
				return "redirect:/news/list.do";
	
		
		
	}
}
