package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class LoginCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 2. DB로 보낼 DTO 생성
		Member member = new Member(id, pw, null, null, null);
		
		// 3. DAO의 loginUser() 메소드 호출
		Member loginUser = MemberDAO.getInstance().login(member);
		
		// 4. 로그인 확인(session에 있는 ID와 일치하는 지 확인)
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		// 5. 이동
		return new ModelAndView("/member/myPage.jsp", false);
		
	}

}
