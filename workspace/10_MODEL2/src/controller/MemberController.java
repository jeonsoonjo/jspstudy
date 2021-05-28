package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.member.LoginPageCommand;
import command.member.MemberCommand;
import common.ModelAndView;

@WebServlet("*.m")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 기본 처리(요청과 응답)
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		// 요청 확인
		// 요청주소 : request.getRequestURI() == /10_MODEL2/loginPage.m 
		// split("/") == {"", ""} 으로 분리한다
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length-1]; // cmd == "loginPage.m"이 들어있음
		
		// 요청을 전달하면 그 요청을 처리할  Model(Command)을 반환하는 CommandMapper 클래스를 Command를 받는다
		MemberCommand command = CommandMapper.getInstance().getCommand(cmd);
		
		ModelAndView mav = null;
		if(command != null) {
			mav = command.execute(request, response);
		}
		if(mav == null) { // mav가 null이라면 command가 다 처리하고 이동할 페이지가 없음을 의미한다. return으로 종료 -> doGet() 메소드 종료
			return;
		}
		if(mav.isRedirect()) {
			response.sendRedirect(mav.getView());
		} else {
			request.getRequestDispatcher(mav.getView()).forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
