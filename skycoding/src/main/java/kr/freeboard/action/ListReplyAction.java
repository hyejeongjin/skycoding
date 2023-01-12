package kr.freeboard.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.freeboard.dao.FreeBoardDAO;
import kr.freeboard.vo.FreeBoardReplyVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ListReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리(post로 넘겨서 인코딩 처리를 했지만 지금은 숫자만 넘어오는 거라 인코딩 처리 안 해도 상관없긴 함)
		request.setCharacterEncoding("utf-8");

		String pageNum = request.getParameter("pageNum");
		//무조건 오류가 난다고 해도 1페이지로
		if(pageNum == null) {
			pageNum = "1";
		}

		int free_id = Integer.parseInt(request.getParameter("free_id"));
		FreeBoardDAO freeDao = FreeBoardDAO.getInstance();
		int count = freeDao.getReplyBoardCount(free_id);

		/*
		 * ajax 방식으로 목록을 표시하기 때문에 여기에서 PagingUtil은 페이지수표시가 목적이 아니라
		 * 목록 데이터 페이지 처리를 위한 rownum 번호(startRow, endRow)를 구하는 게 목적이다.
		 * 얘 없으면 starrow,endrow 구하기 번거로워서..
		 */
		int rowCount = 10; //10개의 댓글이 보여지게 함
		//CurrentPage,count,rowCount,pageCount,url 순서로 전달
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,rowCount,1,null);

		List<FreeBoardReplyVO> list = null;
		if(count > 0) {
			list = freeDao.getListReplyBoard(page.getStartRow(),
											page.getEndRow(),
											free_id);
		}else {
			//freeBoard.reply.js에서 each 메서드가 하는 작업
			list = Collections.emptyList(); //null이면 빈 List 생성해서 js가 비어있는 배열로 인식하게 한다. 안 그러면 빈문자열이 아닌 null로 표시됨
		}

		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		//로그인한 사람이 작성자인지 체크하기 위해서 user_num 값을 전송
		mapAjax.put("user_num", user_num);

		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);

		request.setAttribute("ajaxData", ajaxData);

		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
