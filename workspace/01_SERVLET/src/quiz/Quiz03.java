package quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz03")
public class Quiz03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Quiz03() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 처리
		request.setCharacterEncoding("utf-8");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String contents=request.getParameter("contents");
		String date=new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date());
		String ip=request.getRemoteAddr();
		// IP주소
		// 1. 기본은 request.getRemoteAddress()로 알 수 있다(가장 간단함)
		// 2. 클라우드를 거쳐서 가져올 때 X-Forwarded-For 요청 헤더(request header)에 있다(좀 더 세부적임)
		// 3. localhost 주소가 나왔다면
		//	  run -> run configurations -> tomcat 8.5 -> arguments 에
		//	  -Djava.net.preferIPv4Stack=true 를 추가로 적어준다(보기 편함)
		/*
		 	String ip=request.getHeader("X-Forwarded-For");
		 	if(ip==null){ // 거치지 않고 직접 왔다면
		 		ip=request.getRemoteAddress();
		 	}
		*/
		if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
			InetAddress inet=InetAddress.getLocalHost();
			ip=inet.getHostAddress();
		}
		// 응답 처리
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>제목</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<ul>");
		out.println("<li><strong>제목:</strong> "+title+"</li>");
		out.println("<li><strong>작성자:</strong> "+writer+"</li>");
		out.println("<li><strong>내용:</strong> "+contents+"</li>");
		out.println("<li><strong>IP주소:</strong> "+ip+"</li>");
		out.println("<li><strong>작성일:</strong> "+date+"</li>");
		out.println("</ul>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
