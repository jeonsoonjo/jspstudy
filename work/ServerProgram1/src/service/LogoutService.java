package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LogoutService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().invalidate();
		return new ModelAndView("/ServerProgram1/member/login.jsp", true);
	}

}


