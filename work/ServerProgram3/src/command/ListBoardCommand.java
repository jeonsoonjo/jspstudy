package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class ListBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		int totalRecord = BoardDAO.getInstance().getTotalBoardCount();
		List<BoardDTO> list = BoardDAO.getInstance().selectList();
		
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("list", list);
		
		ModelAndView mav = new ModelAndView("listBoard.jsp", false);
		return mav;
		
	}

}
