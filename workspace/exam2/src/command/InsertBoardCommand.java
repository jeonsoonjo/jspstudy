package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. DB로 보낼 DTO
		BoardDTO dto = new BoardDTO();
		dto.setAuthor(author);
		dto.setTitle(title);
		dto.setContent(content);

		// 3. DAO의 insertBoard() 메소드 호출
		int result = BoardDAO.getInstance().insertBoard(dto);
		
		// 4. 응답처리
		ModelAndView mav = null;
		try {
			if(result == 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('게시글 작성이 취소되었습니다.')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				mav = new ModelAndView("/exam2/boardList.do?result=" + result, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	
	}
}

