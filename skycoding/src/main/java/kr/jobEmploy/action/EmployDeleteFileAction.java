package kr.jobEmploy.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.jobEmploy.dao.EmployDAO;
import kr.jobEmploy.vo.EmployVO;
import kr.util.FileUtil;

public class EmployDeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer mem_num = (Integer)session.getAttribute("mem_num");
		if(mem_num==null) {//로그인이 안 된 경우
			mapAjax.put("result","logout");
		}else {//로그인 된 경우
			int emp_id = Integer.parseInt(request.getParameter("emp_id"));
			EmployDAO dao = EmployDAO.getInstance();
			EmployVO employ = dao.getEmployDetail(emp_id);
			//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
			if(mem_num!=employ.getMem_num()) {//불일치
				mapAjax.put("result", "wrongAccess");
			}else {
				dao.deleteFile(emp_id);//테이블에서 파일 이름만 삭제
				//실제 파일 삭제
				FileUtil.removeFile(request, employ.getEmp_photo());
				mapAjax.put("result", "success");
			}
		}  
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
