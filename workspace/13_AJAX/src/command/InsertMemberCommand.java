package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import controller.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class InsertMemberCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		String strMember = request.getParameter("member");
		
		// 2. memberManager에서 보낸 JSON obj 데이터 받고 객체 생성하기(캐스팅)
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(strMember);

		// 3. DB로 보낼 DTO
		Member member = new Member();
		member.setId(obj.get("id").toString());
		member.setName(obj.get("name").toString());
		member.setGender(obj.get("gender").toString());
		member.setAddress(obj.get("address").toString());
		
		// 4. DAO의 insertMember() 메소드 호출
		int result = MemberDAO.getInstance().insertMember(member);
		
		// 5. JSON 타입으로 응답처리
		JSONObject obj2 = new JSONObject();
		obj2.put("isSuccess", result > 0);
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj2);
		
		return null;
		
	}

}


