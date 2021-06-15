package command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class BoardListCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<BoardDTO> list = BoardDAO.getInstance().boardList();
		request.setAttribute("list", list);
		
		return new ModelAndView("boardList.jsp", false);
		
	}

}

