package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LogoutCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. session의 loginDTO의 로그아웃 기록을 위해 id 추출
		HttpSession session = request.getSession();
		String id = ((MemberDTO)session.getAttribute("loginDTO")).getId();

		// 2. DAO의 logout() 메소드 호출
		MemberDAO.getInstance().logoutLog(id);

		// 3. session 초기화(기록 삭제)
		session.invalidate();
		
		// 3. 어디로 어떻게 갈 것인가
		return new ModelAndView("/10_MODEL2/index.do", true);
	}

}

