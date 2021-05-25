package ex05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ex07")
public class Ex07 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex07() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("name")==null); // false : null이 아니다
		System.out.println(request.getParameter("name").isEmpty()); // true : 비어있다. 즉, null아니라 비어있는 상태이다
		// 폼의 요소는 무조건 전달된다. 즉, null이 아니다
		
		// 1. 요청 처리
		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
		// 2) 파라미터 저장
		String name=request.getParameter("name");
		if(name.isEmpty()) {
			name="기본이름";
		}
		String strAge=request.getParameter("age");
		int age=0;
		if(!strAge.isEmpty()) {
			age=Integer.parseInt(strAge);
		}
		
		// 2. 응답 처리
		// 1) content-type 설정
		response.setContentType("text/html; charset=utf-8");
		// 2) 출력 스트림 생성
		PrintWriter out=response.getWriter();
		// 3) HTML문서 생성
		out.println("<script>");
		out.println("alert('이름: "+name+", 나이: "+age+"살')");
		out.println("</script>");
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
