package kr.qnaboard.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.qnaboard.dao.QnaBoardDAO;
import kr.qnaboard.vo.QnaBoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		if(user_num==null) {//로그인 안 된 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			int qna_id = Integer.parseInt(request.getParameter("qna_id"));
			QnaBoardDAO qnaDao = QnaBoardDAO.getInstance();
			QnaBoardVO db_board = qnaDao.getBoard(qna_id); //로그인한 사람이랑 비교할 데이터 넘겨받기
			if(user_num!=db_board.getMem_num()){
				//로그인한 사람과 작성자가 불일치한 경우
				mapAjax.put("result", "wrongAccess");
			}else {
				//로그인한 사람과 작성자 일치
				qnaDao.deleteFile(qna_id);
				//파일 삭제
				FileUtil.removeFile(request, db_board.getQna_photo());
				mapAjax.put("result", "success");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		
		return "/WEB-INF/views/common/ajax_view.jsp"; //네트워크 오류라고 뜨면 보통 WEB 앞에 슬래시 안 넣은 것!
	}

}
