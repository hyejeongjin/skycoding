package kr.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.qnaboard.dao.QnaBoardDAO;
import kr.qnaboard.vo.QnaBoardVO;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		Integer user_auth = (Integer)session.getAttribute("mem_auth");
		
		if(user_num == null) { //로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인 된 경우
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		
		QnaBoardDAO qnaDao = QnaBoardDAO.getInstance();
		QnaBoardVO db_board = qnaDao.getBoard(qna_id);
		
		if(user_num != db_board.getMem_num() && user_auth != 9) {
			
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		qnaDao.deleteBoard(qna_id);
		//파일 삭제(메서드 가보면 알겠지만, null일 때와 아닐 때 다 처리해줌)
		FileUtil.removeFile(request, db_board.getQna_photo());
		
		return "redirect:/board_qna/list.do";
		
	}
}
