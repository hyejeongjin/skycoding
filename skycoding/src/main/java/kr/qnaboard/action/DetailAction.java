package kr.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.qnaboard.vo.QnaBoardVO;
import kr.controller.Action;
import kr.qnaboard.dao.QnaBoardDAO;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호 반환
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));

		QnaBoardDAO qnaDao = QnaBoardDAO.getInstance();

		//조회수 증가
		qnaDao.updateReadcount(qna_id);

		QnaBoardVO qnaBoard = qnaDao.getBoard(qna_id); //getBoard에 qna_id를 넘겨주면 QnaBoardVO 하나를 반환
		QnaBoardVO pnBoard = qnaDao.prevNext(qna_id);
		
		
		//HTML 태그를 허용하지 않음
		qnaBoard.setQna_title(StringUtil.useNoHtml(qnaBoard.getQna_title()));
		//HTML 태그를 허용하지 않으면서 줄바꿈 처리
		qnaBoard.setQna_content(StringUtil.useBrNoHtml(qnaBoard.getQna_content()));

		request.setAttribute("qnaBoard", qnaBoard);
		request.setAttribute("pnBoard", pnBoard);
		

		return "/WEB-INF/views/board_qna/detail.jsp";
	}

}
