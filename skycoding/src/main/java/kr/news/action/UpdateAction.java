package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = 
				(Integer)session.getAttribute("mem_num");
		if(mem_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		MultipartRequest multi = 
				            FileUtil.createFile(request);
		int news_id = Integer.parseInt(
				          multi.getParameter("news_id"));
		String news_photo = multi.getFilesystemName("news_photo");
		
		NewsDAO dao = NewsDAO.getInstance();
		//수정전 데이터 호출
		NewsVO db_news = dao.getNews(news_id);
		
		Integer mem_auth = 
				(Integer)session.getAttribute("mem_auth");
		if(mem_auth<9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//관리자 로그인
		NewsVO news = new NewsVO();
		news.setNews_id(news_id);//id(번호)를 표시해야 저장이됨
		news.setNews_title(multi.getParameter("news_title"));
		news.setNews_content(multi.getParameter("news_content"));
		news.setNews_photo(multi.getFilesystemName("news_photo"));
	
		
		dao.updateNews(news);
		
		if(news_photo!=null) {
			//새파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, 
					             db_news.getNews_photo());
		}
		
		return "redirect:/news/detail.do?news_id="+news_id;
	}

}
