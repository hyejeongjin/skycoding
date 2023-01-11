package kr.news.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import org.codehaus.jackson.map.ObjectMapper;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;
 
public class DeleteFileAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = 
				            new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer mem_num = 
				(Integer)session.getAttribute("mem_num");
		if(mem_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			int news_id = Integer.parseInt(
				       request.getParameter("news_id"));
			NewsDAO dao = NewsDAO.getInstance();
			NewsVO db_news = dao.getNews(news_id);
			Integer mem_auth = 
					(Integer)session.getAttribute("mem_auth");
			if(mem_auth<9) {//관리자로 로그인하지 않은 경우
				mapAjax.put("result", "wrongAccess");
			}else {
				//관리자 로그인
				dao.deleteFile(news_id);
				//파일 삭제
				FileUtil.removeFile(request, 
						          db_news.getNews_photo());
				mapAjax.put("result", "success");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}


}
