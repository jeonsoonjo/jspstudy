package ex04;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ex02")
public class Ex02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Ex02() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 1. <a>태그를 이용해서 넘어오면 100% get 방식이다
		// 2. 함께 넘어오는 파라미터는 request가 처리해야한다
		
		// request에서 파라미터 꺼내는 방법(중요!!!)
		// 1. getParameter() 메소드를 이용
		// 2. String getParameter("String parameter명") {} 형식이다. 한 마디로 String을 반환한다(문자로 반환)
		String name=request.getParameter("name");
		if(name!=null) { // 파라미터값이 null이 아니면 출력해라
			System.out.println(name);
		}
		
		String strHeight=request.getParameter("height");
		int height=0; // 초기화
		if(strHeight!=null && !strHeight.isEmpty()) { // null이 아니고 빈 값이 아니라면 출력해라
			height=Integer.parseInt(strHeight); // 정수로 바꿔주기
		}
		
		String strWeight=request.getParameter("weight");
		double weight=0.0;
		if(strWeight!=null && !strWeight.isEmpty()) {
			weight=Double.parseDouble(strWeight); // 소수로 바꿔주기
		}
		
		System.out.println("키는 "+height+"cm이고, 몸무게는 "+weight+"kg이다");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
