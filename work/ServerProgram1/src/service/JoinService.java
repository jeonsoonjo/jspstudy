package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class JoinService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. join.jsp에서 보낸 id, name
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		// 2. DB로 보낼 DTO
		MemberDTO dto = new MemberDTO(id, name);
		
		// 3. DAO의 join() 메소드 호출
		int result = MemberDAO.getInstance().join(dto);
		
		// 4. 응답을 만들 PrintWriter
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('가입되었습니다.')");
				out.println("location.href='/ServerProgram1/join.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원 가입에 실패했습니다')");
				out.println("history.back()");
				out.println("</script>");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}


