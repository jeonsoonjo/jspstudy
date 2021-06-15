package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class ViewBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		// 2. DAO의 viewBoardByIdx() 메소드 호출
		BoardDTO dto = BoardDAO.getInstance().viewBoardByIdx(idx);
		
		// 3. 조회수 증가
		request.setAttribute("dto", dto);
		// 4. 응답처리
		return new ModelAndView("view.jsp", false);
		
	}

}

