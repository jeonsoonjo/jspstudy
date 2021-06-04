package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;

public class JoinPageCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 회원가입을 할 수 있는 joinPage.jsp로 이동
		return new ModelAndView("/member/joinPage.jsp", false); // forward
		
	}

}
