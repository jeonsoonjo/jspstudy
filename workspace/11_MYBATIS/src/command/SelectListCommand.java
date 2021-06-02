package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class SelectListCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return new ModelAndView("/board/selectList.jsp", false); // forward로 이동(forward는 내부 이동이기에 contextPath는 안 적어도 됨)
	
	}

}

