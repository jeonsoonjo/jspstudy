package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import common.ModelAndView;
import dao.MemberDAO;

public class DeleteInfoCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		long no = Long.parseLong(request.getParameter("no"));
		
		// 2. DAO의 deleteInfo() 메소드 호출
		int result = MemberDAO.getInstance().deleteInfo(no);
		if(result > 0) {
			request.getSession().invalidate();
		}
		
		// 3. JSON 타입으로 응답처리
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		out.close();
		
		return null;
		
	}

}


