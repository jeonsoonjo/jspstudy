package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class InsertPageCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return new ModelAndView("board/insert.jsp", false); // forward로 이동
		// forward : request값 가지고 그대로 전달
		// redirect : 수정이 되었으면 수정한 값만 가지고 전달(이전에 요청했던 값을 가지고 갈 필요가 없기 때문)
		
	}

}
