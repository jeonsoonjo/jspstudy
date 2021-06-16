package command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.ReplyDTO;

public class InsertReplyCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		String ip = request.getRemoteAddr();
		long boardNo = Long.parseLong(request.getParameter("boardNo"));
		
		// 2. DB로 보낼 DTO
		ReplyDTO dto = new ReplyDTO();
		dto.setBoardNo(boardNo);
		dto.setIp(ip);
		dto.setAuthor(author);
		dto.setContent(content);
		
		// 3. DAO의 insertReply() 메소드 호출
		int result = BoardDAO.getInstance().insertReply(dto);
		ModelAndView mav = null;
		try {
			if(result == 0) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('댓글이 작성되지 않았습니다')");
				out.println("history.back()");
				out.println("</script>");
			} else {
				mav = new ModelAndView("/ServerProgram3/viewBoard.do?no=" + boardNo, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

}

