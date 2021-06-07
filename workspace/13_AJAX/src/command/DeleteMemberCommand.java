package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import controller.ModelAndView;
import dao.MemberDAO;

public class DeleteMemberCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		long no = Long.parseLong(request.getParameter("no"));
		
		// 2. DAO의 deleteMember() 메소드 호출
		int result = MemberDAO.getInstance().deleteMember(no);
		
		// 3. JSON 타입으로 응답처리
		JSONObject obj = new JSONObject();
		obj.put("isSuccess", result > 0);
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		
		return null;
		
	}

}


