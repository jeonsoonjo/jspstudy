package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LoginCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 2. DB로 보낼 DTO
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		// 3. DAO의 login() 메소드 호출
		MemberDTO loginDTO = MemberDAO.getInstance().login(dto);
		
		// 4. 로그인 성공 유무 확인
		if(loginDTO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginDTO", loginDTO);
		}
		
		// 5. 응답처리
		ModelAndView mav = new ModelAndView("/20_PRAC/index.do", true);
		return mav;
	}

}
