package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LoginService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		// 2. DAO의 login() 메소드 호출
		MemberDTO loginDTO = MemberDAO.getInstance().login(id, name);
		
		// 3. 로그인 성공 및 응답처리
		ModelAndView mav = null;
		if(loginDTO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginDTO", loginDTO);
			mav = new ModelAndView("/ServerProgram1/member/manager.jsp", true);
		} else {
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('아이디와 비밀번호를 확인하세요.')");
				out.println("history.back()");
				out.println("</script>");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mav;

	}

}


