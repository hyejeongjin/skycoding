package kr.freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.freeboard.vo.FreeBoardVO;
import kr.controller.Action;
import kr.freeboard.dao.FreeBoardDAO;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
		int free_id = Integer.parseInt(request.getParameter("free_id"));

		FreeBoardDAO freeDao = FreeBoardDAO.getInstance();

		//조회수 증가
		freeDao.updateReadcount(free_id);

		FreeBoardVO freeBoard = freeDao.getBoard(free_id); //getBoard에 free_id를 넘겨주면 FreeBoardVO 하나를 반환
		FreeBoardVO pnBoard = freeDao.prevNext(free_id);
		
		
		//HTML 태그를 허용하지 않음
		freeBoard.setFree_title(StringUtil.useNoHtml(freeBoard.getFree_title()));
		//HTML 태그를 허용하지 않으면서 줄바꿈 처리
		freeBoard.setFree_content(StringUtil.useBrNoHtml(freeBoard.getFree_content()));

		request.setAttribute("freeBoard", freeBoard);
		request.setAttribute("pnBoard", pnBoard);
		

		return "/WEB-INF/views/board_free/detail.jsp";
	}

}
