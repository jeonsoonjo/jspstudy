package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.LoginCommand;
import command.LoginPageCommand;
import command.MemberCommand;
import common.ModelAndView;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청, 응답 기본 처리
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// command 알아내기
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length - 1];
		
		MemberCommand command = null;
		switch(cmd) {
		case "loginPage.do" :
			command = new LoginPageCommand();
			break;
		case "login.do" :
			command = new LoginCommand();
			break;

		}
		
		ModelAndView mav = null;
		try {
			if(command != null) {
				mav = command.execute(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


