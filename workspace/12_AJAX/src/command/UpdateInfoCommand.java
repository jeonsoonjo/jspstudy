package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import common.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class UpdateInfoCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		// 2. DB로 보낼 DTO
		Member member = new Member();
		member.setNo(no);
		member.setName(name);
		member.setEmail(email);
		member.setPhone(phone);
		
		// 3. DAO의 updateInfo() 메소드 호출
		int result = MemberDAO.getInstance().updateInfo(member);
		if(result > 0) {
			HttpSession session = request.getSession();
			Member loginUser = (Member)session.getAttribute("loginUser");
			loginUser.setName(name);
			loginUser.setEmail(email);
			loginUser.setPhone(phone);
		}
		
		// 4. JSON 타입으로 응답처리
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();
		
		return null;
		
	}

}


