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
		
		// 1. session의 loginDTO의 로그아웃 기록을 위해 id 추출
		HttpSession session = request.getSession();
		String id = ((MemberDTO)session.getAttribute("loginDTO")).getId();

		// 2. session 초기화(기록 삭제)
		session.invalidate();
		
		// 3. 응답처리
		return new ModelAndView("/ServerProgram1/index.do", true);
	}

}


