package kr.jobEmploy.action;
  
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.jobEmploy.dao.EmployDAO;
import kr.jobEmploy.vo.EmployVO;
import kr.mypage.dao.MypageDAO;
import kr.mypage.vo.MycourselistVO;
import kr.qnaboard.vo.QnaBoardVO;
import kr.util.StringUtil;

public class EmployDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//관리자만 수정,삭제 버튼 보이게 하기위해 auth값 전달
		HttpSession session = request.getSession();
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		request.setAttribute("mem_auth", mem_auth);
		
		//글 번호 반환 
		int emp_id = Integer.parseInt(request.getParameter("emp_id"));
		
		EmployDAO dao = EmployDAO.getInstance();
		//조회수 증가
		dao.UpdateReadCount(emp_id);
		
		//이전글,다음글
		EmployVO pnEmploy = dao.prevNext(emp_id);
		
		//상세정보 자바빈에 저장
		EmployVO employ = dao.getEmployDetail(emp_id);
		//여기에다가 등록폼에 입력한 회원 아이디의 회원번호 반환
		int mem_num = 141; 
		
		//회원이 수강한 강의 이름 저장
		MypageDAO myPageDao = MypageDAO.getInstance();
		int count = myPageDao.getCoursecartCount(mem_num, null);
		List<MycourselistVO> list = new ArrayList<MycourselistVO>();
		list = myPageDao.getListCourse(1, count, mem_num, null, null);
		
		//HTML태그를 허용하지 않음
		employ.setEmp_title(StringUtil.useNoHtml(employ.getEmp_title()));
		//HTML태그를 허용하지 않으면서 줄바꿈 처리
		employ.setEmp_content(StringUtil.useBrNoHtml(employ.getEmp_content()));
		
		request.setAttribute("employ", employ);
		request.setAttribute("list", list);
		request.setAttribute("pnEmploy", pnEmploy);
		
		return "/WEB-INF/views/jobEmploy/employDetail.jsp";
	}  

}
