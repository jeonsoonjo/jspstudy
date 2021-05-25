package quiz;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Quiz05_1")
public class Quiz05_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Quiz05_1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// redirect는 기존 request의 정보를 전달하지 않는다
		// 따라서 우리가 직접 다시 전달해야 한다(?로 파라미터 경로를 적어줘야 한다)
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String age=request.getParameter("age");
		
		// 새로운 요청(request)의 새로운 파라미터 생성함
		// 인코딩(암호화) : URLEncoder.encode("문자열", "utf-8")
		// 디코딩(복호화) : URLDecoder.decode("문자열", "utf-8")
		response.sendRedirect("/01_SERVLET/Quiz05_2?name="+URLEncoder.encode(name, "utf-8")+"&age="+age);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
