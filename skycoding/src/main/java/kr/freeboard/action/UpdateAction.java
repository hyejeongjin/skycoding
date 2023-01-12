package kr.freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.freeboard.dao.FreeBoardDAO;
import kr.freeboard.vo.FreeBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		Integer user_auth = (Integer)session.getAttribute("mem_auth");
		
		if(user_num == null || user_auth == 0) { //로그인이 되지 않은 경우
			return "redirect:/hmember/loginForm.do";
		}

		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request); //파일이 있으니까 멀티파트리퀘스트
		
		int free_id = Integer.parseInt(multi.getParameter("free_id"));
		String free_photo = multi.getFilesystemName("free_photo");
		
		FreeBoardDAO freeDao = FreeBoardDAO.getInstance();
		//수정전 데이터 호출
		FreeBoardVO db_board = freeDao.getBoard(free_id);
		
		if(user_num != db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			FileUtil.removeFile(request, free_photo);//업로드된 파일이 있으면 파일을 삭제
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		FreeBoardVO freeBoard = new FreeBoardVO();
		freeBoard.setFree_id(free_id);
		freeBoard.setFree_title(multi.getParameter("free_title"));
		freeBoard.setFree_content(multi.getParameter("free_content"));
		freeBoard.setFree_photo(free_photo);
		
		freeDao.updateBoard(freeBoard); //만들어 둔 dao의 메서드에 정보 넘김
		
		if(free_photo!=null) {
			//새파일로 교체할 때 원래 파일은 제거(이거 안 하면 쓰레기가 계속 생김)
			FileUtil.removeFile(request, db_board.getFree_photo());
		}
		return "redirect:/board_free/detail.do?free_id="+free_id;
	}
}
