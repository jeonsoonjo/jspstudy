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
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setName(name);
		
		// 3. DAO의 join() 메소드 호출
		int result = MemberDAO.getInstance().joinMember(dto);
		
		// 4. 응답을 만들 PrintWriter
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {
				out.println("<script>");
				out.println("alert('가입되었습니다.')");
				out.println("location.href='/ServerProgram1/loginPage.do'");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}


