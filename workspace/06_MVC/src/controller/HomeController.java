package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DateService;
import model.HomeService;
import model.TimeService;

@WebServlet("*.do") // .do로 끝나는 파일은 모두 HomeController인 여기로 온다(시작 슬래시가 없음에 주의!)
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request, response 기본 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// 요청 확인 과정
		String requestURI=request.getRequestURI(); /*** /06_MVC/date.do input에서 지정한 전체경로를 알 수 있다 ***/
		String[] arr=requestURI.split("/"); /*** arr:{"", "06_MVC", "date.do"} contextPath, mapping값을 split(분리)하고 배열에 저장한다 ***/
		String command=arr[arr.length-1]; /*** command: "date.do" ***/
		// response.getWriter().write(command); 확인용
		
		// 요청에 따른 model의 선택과 호출
		HomeService service=null; // 인터페이스를 생성하고 모든 service를 사용한다
		String path=null; // case밖에서도 사용할 수 있도록 외부에 path를 잡아준다
		switch(command) {
		case "date.do" :
			service=new DateService();
			path=service.execute(request, response);
			break;
		case "time.do" :
			service=new TimeService();
			path=service.execute(request, response);
			break;
		}
		
		// model의 호출 결과(응답 view)에 따른 페이지 이동
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
