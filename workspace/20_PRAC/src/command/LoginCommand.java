package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LoginCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 2. DB로 보낼 DTO
		MemberDTO memberDTO = new MemberDTO(id, pw, null, null, null);
		
		// 3. DAO의 login() 메소드 호출
		MemberDTO loginUser = MemberDAO.getInstance().login(memberDTO);
		
		// 4. 로그인 성공 확인(session ID와 일치하는지 확인)
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패')");
			out.println("history.back()");
			out.println("</script>");
			return null;
		}
		
		// 5. 응답처리
		return new ModelAndView("/member/myPage.jsp", false);
	}

}


