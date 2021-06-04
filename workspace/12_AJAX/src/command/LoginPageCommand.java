package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class LoginPageCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인 할 수 있는 loginPage.jsp로 이동
		return new ModelAndView("/member/loginPage.jsp", false); // forward
		
	}

}
