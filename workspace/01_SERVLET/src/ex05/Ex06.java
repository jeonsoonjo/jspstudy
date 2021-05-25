package ex05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Ex06")
public class Ex06 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Ex06() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청 처리
		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
		// 2) 파라미터 저장
		Optional<String> opt1=Optional.ofNullable(request.getParameter("name"));
		String name=opt1.orElse("");
		Optional<String> opt2=Optional.ofNullable(request.getParameter("age"));
		int age=Integer.parseInt(opt2.orElse("0"));
		
		// 2. 응답 처리
		// 1) content-type 설정
		response.setContentType("text/html; charset=utf-8");
		// 2) 출력 스트림 생성
		PrintWriter out=response.getWriter();
		// 3) HTML문서 생성
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>제목</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>이름: "+name+"</h3>");
		out.println("<h3>나이: "+age+"</h3>");
		out.println("<button id=\"btn\">뒤로가기</button>");
		out.println("<script>");
		out.println("document.getElementById('btn').addEventListener('click', function(e){");
		out.println("e.preventDefault(); history.back();})");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
