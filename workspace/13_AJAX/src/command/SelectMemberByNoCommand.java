package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import controller.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class SelectMemberByNoCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		long no = Long.parseLong(request.getParameter("no"));
		
		// 2. DAO에 selectMemberByNo() 메소드 호출
		Member member = MemberDAO.getInstance().selectMemberByNo(no);
		
		// 3. JSON 타입으로 응답처리
		JSONObject obj = new JSONObject();
		if(member != null) {
			obj.put("no", member.getNo());
			obj.put("id", member.getId());
			obj.put("name", member.getName());
			obj.put("gender", member.getGender());
			obj.put("address", member.getAddress());
			obj.put("isExist", true); 
		} else {
			obj.put("isExist", false);
		}
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		
		return null;
		
	}

}
