package command.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class SelectOneBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		long idx = Long.parseLong(request.getParameter("idx"));
		
		// 2. DAO의 selectOneBoardByIdx() 메소드 호출
		BoardDTO dto = BoardDAO.getInstance().selectOneBoardByIdx(idx);
		
		// 3. 조회수 증가(DAO의 updateHit() 메소드 호출)
		BoardDAO.getInstance().updateHit(idx);
		
		// 4. 게시글이 존재하던 목록의 주소
		String referer = request.getHeader("referer");
		
		// 5. 응답View로 전달할 데이터
		request.setAttribute("dto", dto);
		request.setAttribute("referer", referer);
		
		// 6. 어디로 어떻게 갈 것인가
		ModelAndView mav = new ModelAndView("/board/viewBoard.jsp", false); // forward로 이동
		return mav;
	}

}


