package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import common.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class UpdatePwCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		long no = Long.parseLong(request.getParameter("no"));
		String pw = request.getParameter("pw");
		
		// 2. DB로 보낼 DTO
		Member member = new Member();
		member.setNo(no);
		member.setPw(pw);
		
		// 3. DAO의 updatePw() 메소드 호출
		int result = MemberDAO.getInstance().updatePw(member);
		if(result > 0) {
			HttpSession session = request.getSession(); // session 기존 비번 꺼내기
			Member loginUser = (Member)session.getAttribute("loginUser");
			loginUser.setPw(pw); // 변경된 비번이 있다면 session에 update하기
			
		}
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();
		
		return null;
	}

}
