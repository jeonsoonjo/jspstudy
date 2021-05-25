package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.ShapeCommand;

@WebServlet("*.do")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 기본 작업
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 2. 요청 확인
		String[] arr= request.getRequestURI().split("/"); // 전체 경로 중 '/'를 기준으로 나눠 배열에 저장
		String cmd=arr[arr.length-1]; // 저장된 배열의 마지막 요소(length-1)인 요청 값(예 : circle.do, rectangle.do) 저장
		
		// 3. 요청에 따른 model의 선택
		//	1) 클래스명 : ModelMapper
		//	2) 매개변수 : String cmd(어떤 요청인지 전달)
		//	3) 반환타입 : Model의 인터페이스(Model을 반환)
		
		// Model의 기본
		// 1. 인터페이스를 하나 생성한다
		// 2. 해당 인터페이스를 구현한다
		// 	-> 모든 Model의 타입은 인터페이스이다
		ShapeCommand command=ModelMapper.getInstance().getModel(cmd);
		
		// 4. Model의 호출(실행)
		// String path=command.execute(request, response); 응답view만 반환했을 때
		ModelAndView mav=null;
		mav=command.execute(request, response);
		if(mav==null) {
			return;
		}
		
		// 5. 응답view로 이동
		if(mav.isRedirect()) {
			response.sendRedirect(mav.getView());
		} else {
			request.getRequestDispatcher(mav.getView()).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
