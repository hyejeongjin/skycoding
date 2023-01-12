package kr.freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.freeboard.dao.FreeBoardDAO;
import kr.freeboard.vo.FreeBoardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		Integer user_auth = (Integer)session.getAttribute("mem_auth");
		
		if(user_num == null || user_auth == 0) { //로그인 되지 않은 경우
			return "redirect:/hmember/loginForm.do";
		}
		
		//로그인 된 경우
		//글번호 반환
		int free_id = Integer.parseInt(request.getParameter("free_id"));

		FreeBoardDAO freeDao = FreeBoardDAO.getInstance();
		FreeBoardVO freeBoard = freeDao.getBoard(free_id);
		
		if(user_num != freeBoard.getMem_num()) { //로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인이 되어 있고 로그인한 회원번호와 작성자 회원번호가 일치
		//제목에 큰따옴표가 있으면 input 태그에 데이터를 표시할 때 오동작이 일어나기 때문에 "를 &quot;로 변환
		freeBoard.setFree_title(StringUtil.parseQuot(freeBoard.getFree_title()));

		request.setAttribute("freeBoard", freeBoard);

		return "/WEB-INF/views/board_free/updateForm.jsp";
	}

}
