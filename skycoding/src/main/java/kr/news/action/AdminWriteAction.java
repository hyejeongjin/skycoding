package kr.news.action;
import javax.servlet.http.HttpServletRequest;



import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;



import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;

public class AdminWriteAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = 
				(Integer)session.getAttribute("mem_num");
		if(mem_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		Integer mem_auth = 
				(Integer)session.getAttribute("mem_auth");
		if(mem_auth<9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//관리자로 로그인한 경우
		MultipartRequest multi = 
				        FileUtil.createFile(request);
		NewsVO news = new NewsVO();
		news.setNews_title(multi.getParameter("news_title"));
		news.setNews_content(multi.getParameter("news_content"));
		news.setNews_photo(multi.getFilesystemName("news_photo"));
		news.setNews_attr(Integer.parseInt(
	            multi.getParameter("news_attr")));
	
	
		NewsDAO dao = NewsDAO.getInstance();
		dao.insertNews(news);

        
		return "/WEB-INF/views/news/admin_write.jsp";
	}

}
