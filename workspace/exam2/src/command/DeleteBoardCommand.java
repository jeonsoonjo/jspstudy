package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;

public class DeleteBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 2. DAO의 deleteBoard() 메소드 호출
		int result = BoardDAO.getInstance().deleteBoard(idx);
		
		// 3. 응답처리
		ModelAndView mav = null;
		try {
			if(result == 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('글이 삭제되지 않았습니다')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				mav = new ModelAndView("/exam2/boardList.do", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

}


