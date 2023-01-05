package kr.qnaboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		 */
		
		MultipartRequest multi = FileUtil.createFile(request);
		QnaBoardVO qnaboard = new QnaBoardVO();
		qnaboard.setQna_title(multi.getParameter("title"));
		qnaboard.setQna_content(multi.getParameter("content"));
		qnaboard.setQna_photo(multi.getFilesystemName("filename"));
		//qnaboard.setMem_num(user_num); //로그인 처리 완료하면 주석 풀 예정


		QnaBoardDAO dao = QnaBoardDAO.getInstance();
		dao.insertBoard(qnaboard);
		
		//일단 refresh처리했는데 잘 안 되면 나중에 return으로 write.jsp랑 연결할 예정
		response.addHeader("Refresh", "2;url=list.do");
		
		return null;
	}

}
