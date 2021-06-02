package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public interface BoardCommand {

	// throws Exception 처리를 해주면
	// 따로 command에 생성되는 class마다 try-catch 예외 처리를 하지 않아도 된다
	// controller에 command를 예외 처리해주면 된다
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
