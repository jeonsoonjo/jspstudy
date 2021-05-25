package login.captcha;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginValidation() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 성공, 실패 message는 alert로 작성
		// 응답 처리를 위해서 response의 처리를 해줄 필요가 있다
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		// 사용자 입력 값 검증
		// 검증 결과에 따라서 이동할 페이지를 다르게 지정한다
		boolean result=CaptchaValidation.getValidate(request);
		if(result) {
			out.println("<script>");
			out.println("alert('검증에 성공했습니다')");
			out.println("location.href='/03_CAPTCHA/index.jsp'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('검증에 실패했습니다')");
			out.println("location.href='/03_CAPTCHA/Login'");
			out.println("</script>");
		}
		out.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
