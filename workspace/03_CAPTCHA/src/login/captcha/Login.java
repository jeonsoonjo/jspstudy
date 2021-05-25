package login.captcha;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * http://localhost:9090/03_CAPTCHA/Login
 */
@WebServlet("/Login") // url매핑값
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 네이버에 캡차 키 요청하기
		String key=CaptchaKey.getCaptchaKey(); // CaptchaKey 클래스의 getCaptchaKey 메소드 호출
		
		// Login.java가 LoginValidation.java에게 key 전달하기(건너 뛰지 않고, 거쳐서 거쳐서 전달한다)
		// 1) request 이용(전달, 전달...해서 사용하기)
		//	Login.java -> login.jsp -> LoginValidation.java
		//	reqeust.setAttribute("key", key);
		// 		<input type="hidden" name="key" value="<%=request.getAttribute("key")%>">
		// 			request.getParameter("key")
		// 2) session 이용(session에 저장해두고 꺼내서 사용하기)
		//	Login.java -> login.jsp -> LoginValidation.java
		// 	session.setAttribute("key", key);
		//		session.getAttribute("key")
		//			session.getAttribute("key")
		
		// session을 구하는 방법
		// 1) request에서 구한다
		// 2) HttpSession session=request.getSession();
		HttpSession session=request.getSession();
		session.setAttribute("key", key); // LoginValidation에서 필요하다
		
		// 2. 네이버에 캡차 이미지로 요청하기
		// CaptchaImage.java에게 request를 전달하고,
		// CaptchaImage.java가 그 request에 데이터를 저장했다
		// 따라서 request에 저장된 데이터를 꺼내서 사용할 수 있다
		CaptchaImage.getCaptchaImage(request, key); // CaptcahImage 클래스 getCaptchaImage에 key값 전달
		// CaptchaImage에 request를 해줘야 하니 매개변수에 request를 추가한다
		
		// (확인용)
		// String dir=(String)request.getAttribute("DIR");
		// String filename=(String)request.getAttribute("filename");
		// response.getWriter().write(dir+"/"+filename);
		
		
		// 3. 로그인 페이지(login.jsp)로 이동하기
		// 캡차 이미지가 저장된 디렉터리 + 캡차 이미지 파일명을 가지고 login.jsp로 이동한다
		// 즉, request의 정보를 유지한 상태로 이동한다 -> 즉, forward 활용
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
