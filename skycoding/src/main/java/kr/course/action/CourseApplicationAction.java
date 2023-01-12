package kr.course.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.cart.dao.CartDAO;
import kr.cart.vo.CartVO;
import kr.controller.Action;


public class CourseApplicationAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("mem_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/hmember/loginForm.do";
		}
		
		//로그인 된 경우
		int course_id = Integer.parseInt(request.getParameter("course_id"));
		
		CartDAO dao = CartDAO.getInstance();
		CartVO cart = new CartVO();
		cart.setCourse_id(course_id);
		cart.setMem_num(user_num);
		dao.insertCart(cart);
		
		request.setAttribute("course_id", course_id);
		return "/WEB-INF/views/course/application.jsp";
	}

}
