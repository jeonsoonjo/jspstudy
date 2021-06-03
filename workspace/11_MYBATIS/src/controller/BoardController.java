package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.BoardCommand;
import command.FindListCommand;
import command.InsertCommand;
import command.InsertPageCommand;
import command.InsertReplyCommand;
import command.InsertReplyPageCommand;
import command.SelectListCommand;
import common.ModelAndView;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/*
	 *  BoardDAO : DBService로부터 SqlSessionFactory를 받아서 SqlSession을 생성
	 *  DBService : SqlSessionFactory 빌드
	*/
	
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String[] arr = request.getRequestURI().split("/");
		String cmd = arr[arr.length - 1];
		
		// Mapper를 따로 두지 않고 Controller에서 한 번에 처리할 경우
		ModelAndView mav = null;
		BoardCommand command = null;
		switch(cmd) {
		case "selectList.do" :
			command = new SelectListCommand();
			break;
		case "insertPage.do" :
			command = new InsertPageCommand();
			break;
		case "insert.do" :
			command = new InsertCommand();
			break;
		case "insertReplyPage.do" :
			command = new InsertReplyPageCommand();
			break;
		case "insertReply.do" :
			command = new InsertReplyCommand();
			break;
		case "findList.do" :
			command = new FindListCommand();
			break;
			
			
			
			
			
		}
		
		
		// BoardCommand에 throws Exception 처리를 해주면
		// 따로 command에 생성되는 class마다 try-catch 예외 처리를 하지 않아도 된다
		// controller에 command를 예외 처리해주면 된다
		if(command != null) {
			try {
				mav = command.execute(request, response);
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(mav != null) {
			if(mav.isRedirect()) {
				response.sendRedirect(mav.getView());
			} else {
				request.getRequestDispatcher(mav.getView()).forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


