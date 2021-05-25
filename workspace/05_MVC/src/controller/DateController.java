package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GetTime;
import model.GetToday;

@WebServlet("/DateController")
public class DateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// input.jsp의 요청이 오는 곳
		
		// Controller
		// 1. 요청을 확인한다
		// 2. 해당 요청을 처리할 Model(POJO: 순수 Java)을 호출한다
		// 3. Model 실행 결과
		//	1) 결과 값(현재날짜) -> input
		//	2) 응답 view(결과값을 보여 줄 JSP) -> output
		// 4. 응답view로 페이지 이동
		
		GetToday getToday=new GetToday();
		String path=getToday.execute(request); // Controller의 request를 model에게 전달
		
		// 결과값이 저장된 request를 가지고 path(views/output.jsp)로 이동해야 한다
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
