package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class DeleteService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. session에서 loginDTO(no) 알아내기
		int no = ((MemberDTO)request.getSession().getAttribute("loginDTO")).getNo();

		// 3. DAO의 deleteMember()메소드 호출
		int result = MemberDAO.getInstance().deleteMember(no);
		
		// 4. 응답처리
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('탈퇴되었습니다. 이용해 주셔서 감사합니다!')");
				out.println("location.href='/ServerProgram1/member/login.jsp'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원탈퇴가 취소되었습니다')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}


