package command.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class InsertBoardPageCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 게시글을 작성할  수 있는 insertBoardPage.jsp로 이동
		return new ModelAndView("/10_MODEL2/board/insertBoardPage.jsp", true);
		
	}

}
