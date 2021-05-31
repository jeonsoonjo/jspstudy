package command.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class SelectListBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {

		// 게시글을 볼  수 있는 listBoard.jsp로 이동
		ModelAndView mav = new ModelAndView("/10_MODEL2/board/listBoard.jsp", true);
		return mav;
		
	}

}
