package ex06;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Redirect2")
public class Redirect2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Redirect2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 name이 존재하는가? No
		String name=request.getParameter("name");
		System.out.println(name);
		// Redirect.html -> Redirect1 -> Redirect2
		// Redirect1 값은 경유이기 때문에 나타나지 않는다
		// 즉, 처음 Redirect.html 파일에서 파라미터 name은 존재하지만
		// 바로 Redirect2로 이동하기에 Redirect1의 파라미터 name은 존재하지 않는다
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
