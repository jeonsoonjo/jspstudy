package command.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.BoardDAO;
import dto.MemberDTO;
import dto.ReplyDTO;

public class InsertReplyCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		String content = request.getParameter("content");
		Long boardIdx = Long.parseLong(request.getParameter("boardIdx"));
		String ip = request.getRemoteAddr();
		
		// 2. session에 있는 loginDTO
		HttpSession session = request.getSession();
		MemberDTO loginDTO = (MemberDTO)session.getAttribute("loginDTO");
		
		// 3. DB로 보낼 DTO
		ReplyDTO dto = new ReplyDTO();
		dto.setAuthor(loginDTO.getId());
		dto.setBoardIdx(boardIdx);
		dto.setContent(content);
		dto.setIp(ip);
		
		// 4. DAO의 insertReply() 메소드 호출
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
				mav = new ModelAndView("/10_MODEL2/selectOneBoard.b?idx=" + boardIdx, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

}
