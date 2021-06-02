package command.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;
import dto.ReplyDTO;

public class SelectOneBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		long idx = Long.parseLong(request.getParameter("idx"));
		
		// 2. DAO의 selectOneBoardByIdx() 메소드 호출
		BoardDTO dto = BoardDAO.getInstance().selectOneBoardByIdx(idx);
		
		// 3. 조회수 증가(DAO의 updateHit() 메소드 호출)
		BoardDAO.getInstance().updateHit(idx);
		
		// 4. 댓글의 개수 구하기
		// DAO의 getReplyCount() 메소드 호출
		int replyCount = BoardDAO.getInstance().getReplyCount(idx);
		
		// 5. 댓글 리스트 가져오기
		List<ReplyDTO> replyList = BoardDAO.getInstance().selectListReply(idx);
		
		// 5. 게시글이 존재하던 목록의 주소
		String referer = request.getHeader("referer");
		
		// 6. 응답View로 전달할 데이터
		request.setAttribute("dto", dto);
		request.setAttribute("referer", referer);
		request.setAttribute("replyCount", replyCount);
		request.setAttribute("replyList", replyList);
		
		// 7. 어디로 어떻게 갈 것인가
		ModelAndView mav = new ModelAndView("/board/viewBoard.jsp", false); // forward로 이동
		return mav;
	}

}


