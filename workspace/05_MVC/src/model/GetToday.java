package model;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class GetToday {

	// Model
	// 1. 비즈니스 로직을 처리한다(업무 처리)
	// 2. Command, Service 등으로 불린다
	// 3. 요청을 받기 위해서 request를 사용할 수 있어야 한다
	// 4. Model이 결과를 처리하는 방식
	// 	1) 결과값 : Controller가 넘겨 준 request에 저장(Controller에서 결과값을 확인할 수 있다)
	//	2) 응답view : 직접 반환한다
	
	public String execute(HttpServletRequest request) {
		
		// 1. 현재 날짜를 구한다
		Date today=new Date();
		request.setAttribute("today", today);
		
		// 2. 응답 view를 반환한다
		return "views/output.jsp";
	}
}
