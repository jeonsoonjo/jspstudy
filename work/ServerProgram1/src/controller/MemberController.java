package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import service.MemberService;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청, 응답
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String[] arr = request.getRequestURI().split("/");
		String ser = arr[arr.length-1];
		
		// 요청을 전달하면 그 요청을 처리할  Model(Command)을 반환하는 MemberCommandMapper 클래스를 Command를 받는다
		MemberService service = ServiceMapper.getInstance().getService(ser);
		
		ModelAndView mav = null;
		if(ser != null) {
			mav = service.execute(request, response);
		}
		if(mav == null) {
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


