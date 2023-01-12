package kr.freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.freeboard.vo.FreeBoardVO;
import kr.freeboard.dao.FreeBoardDAO;
import kr.util.FileUtil;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		Integer user_auth = (Integer)session.getAttribute("mem_auth");
		
		if(user_num == null || user_auth == 0) { //로그인이 되지 않은 경우
			return "redirect:/hmember/loginForm.do";
		}
		
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		FreeBoardVO freeboard = new FreeBoardVO();
		freeboard.setFree_title(multi.getParameter("free_title"));
		freeboard.setFree_content(multi.getParameter("free_content"));
		freeboard.setFree_photo(multi.getFilesystemName("free_photo"));
		freeboard.setMem_num(user_num);


		FreeBoardDAO dao = FreeBoardDAO.getInstance();
		dao.insertBoard(freeboard);
		
		//일단 refresh써봄. 잘 안 되면 나중에 return으로 write.jsp랑 연결할 예정
		//response.addHeader("Refresh", "2;url=list.do");
		
		//refresh 안 할 거면 이걸로 처리
		return "/WEB-INF/views/board_free/write.jsp";
		
	}

}
