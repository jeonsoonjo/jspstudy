package quiz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz01")
public class Quiz01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Quiz01() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 처리
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd"); // 요청에 따라 다르게 움직일 수 있게 switch문을 사용한다
		Calendar calendar=Calendar.getInstance(); // 현재 날짜와 시간
		String message="";
		switch(cmd) {
		case "date":
			int year=calendar.get(Calendar.YEAR); // 생성된 날짜에서 연도만 추출
			int month=calendar.get(Calendar.MONTH)+1;
			int date=calendar.get(Calendar.DATE);
			message=year+"년 "+month+"월 "+date+"일";
			break;
		case "time":
			int hour=calendar.get(Calendar.HOUR);
			int minute=calendar.get(Calendar.MINUTE);
			int second=calendar.get(Calendar.SECOND);
			message=hour+"시 "+minute+"분 "+second+"초";
			break;
		}
		
		// 응답 처리
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('"+message+"')");
		out.println("history.go(-1);");
		out.println("</script>");
		out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
