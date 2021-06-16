package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;
import dto.ReplyDTO;

public class ViewBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 2. DAO의 viewBoardByIdx() 메소드 호출
		BoardDTO dto = BoardDAO.getInstance().viewBoardByIdx(idx);
		
		// 3. 조회수 증가 : DAO의 updateHit() 메소드 호출
		BoardDAO.getInstance().updateHit(idx);
		
		// 4. DAO의 selectListReply() 메소드 호출 : 댓글 리스트
		List<ReplyDTO> replyList = BoardDAO.getInstance().selectListReply(idx);
		
		// 5. 게시글이 존재하던 목록의 주소
		String referer = request.getHeader("referer");
		
		// 6. 응답View로 전달할 데이터
		request.setAttribute("dto", dto);
		request.setAttribute("referer", referer);
		request.setAttribute("replyList", replyList);

		return new ModelAndView("viewBoard.jsp", false);
		
	}

}

