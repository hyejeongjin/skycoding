package kr.jobEmploy.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.jobEmploy.dao.EmployDAO;
import kr.jobEmploy.vo.EmployVO;
import kr.util.FileUtil;

public class EmployUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		Integer mem_auth = (Integer)session.getAttribute("mem_auth");
		
		if(mem_num==null) {//로그인 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//객체가 생성될 때 파일이 upload 경로에 저장됨
		MultipartRequest multi = FileUtil.createFile(request);
		//글번호 반환
		int emp_id = Integer.parseInt(multi.getParameter("emp_id"));
		String emp_photo = multi.getFilesystemName("emp_photo");
		
		//수정전 데이터 호출(파일 정보를 받아서, 변경될 경우 원래 파일 삭제하고 새로운 파일로 갱신)
		EmployDAO dao = EmployDAO.getInstance();
		EmployVO db_employ = dao.getEmployDetail(emp_id);
		
		if(mem_auth!=9){
			//관리자가 아닌 경우
			FileUtil.removeFile(request, emp_photo);//업로드 된 파일 삭제
			return "/WEB-INF/views/common/notice.jsp";
		}
		//관리자인 경우
		EmployVO employ = new EmployVO();
		employ.setEmp_id(emp_id);
		employ.setEmp_title(multi.getParameter("emp_title"));
		employ.setEmp_content(multi.getParameter("emp_content"));
		employ.setEmp_photo(emp_photo);
		
		dao.UpdateEmploy(employ);
		
		if(emp_photo!=null) {
			//새파일로 교체할 떄 원래 파일 제거
			FileUtil.removeFile(request, db_employ.getEmp_photo());
		}
		
		return "redirect:/jobEmploy/employDetail.do?emp_id="+emp_id;
	}

}
