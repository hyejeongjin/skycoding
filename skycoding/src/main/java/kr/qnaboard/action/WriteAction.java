package kr.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.qnaboard.dao.QnaBoardDAO;
import kr.qnaboard.vo.QnaBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class WriteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * 로그인처리 예정
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) { //로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		 */
		
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		QnaBoardVO qnaboard = new QnaBoardVO();
		qnaboard.setQna_title(multi.getParameter("qna_title"));
		qnaboard.setQna_content(multi.getParameter("qna_content"));
		qnaboard.setQna_photo(multi.getFilesystemName("qna_photo"));
		//qnaboard.setMem_num(user_num); //로그인 처리 완료하면 주석 풀 예정


		QnaBoardDAO dao = QnaBoardDAO.getInstance();
		dao.insertBoard(qnaboard);
		
		//일단 refresh처리했는데 잘 안 되면 나중에 return으로 write.jsp랑 연결할 예정
		response.addHeader("Refresh", "2;url=list.do");
		
		return null;
		
		//refresh 안 할 거면 이걸로 처리
		//return "/WEB-INF/views/board_qna/write.jsp";
		
	}

}
