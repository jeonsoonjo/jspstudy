package model;

import javax.servlet.http.HttpServletRequest;

public class BeerService {

	public String execute(HttpServletRequest request) {
		
		int age=0;
		try {
			age=Integer.parseInt(request.getParameter("age"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", age<20?"미성년자는 음료만 가능합니다":"여기 있습니다!");
		
		return "views/output.jsp";
	}
	
}
