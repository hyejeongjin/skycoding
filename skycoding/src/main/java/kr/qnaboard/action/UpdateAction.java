package kr.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.qnaboard.dao.QnaBoardDAO;
import kr.qnaboard.vo.QnaBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*회원 처리 후 주석 풀 예정
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) { //로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		*/
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request); //파일이 있으니까 멀티파트리퀘스트
		
		int qna_id = Integer.parseInt(multi.getParameter("qna_id"));
		String qna_photo = multi.getFilesystemName("qna_photo");
		
		QnaBoardDAO qnaDao = QnaBoardDAO.getInstance();
		//수정전 데이터 호출
		QnaBoardVO db_board = qnaDao.getBoard(qna_id);
		
		/*회원 처리 후 주석 풀 예정
		if(user_num != db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			FileUtil.removeFile(request, filename);//업로드된 파일이 있으면 파일을 삭제
			return "/WEB-INF/views/common/notice.jsp";
		}
		*/
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		QnaBoardVO qnaBoard = new QnaBoardVO();
		qnaBoard.setQna_id(qna_id);
		qnaBoard.setQna_title(multi.getParameter("qna_title"));
		qnaBoard.setQna_content(multi.getParameter("qna_content"));
		qnaBoard.setQna_photo(qna_photo);
		
		qnaDao.updateBoard(qnaBoard); //만들어 둔 dao의 메서드에 정보 넘김
		
		if(qna_photo!=null) {
			//새파일로 교체할 때 원래 파일은 제거(이거 안 하면 쓰레기가 계속 생김)
			FileUtil.removeFile(request, db_board.getQna_photo());
		}
		return "redirect:/board_qna/detail.do?qna_id="+qna_id;
	}
}
