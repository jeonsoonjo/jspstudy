package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LoginService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		// 2. DB로 보낼 DTO 생성
		MemberDTO dto = new MemberDTO(id, name);
		dto.setId(id);
		dto.setName(name);
		
		// 3. DAO의 login() 메소드 호출
		MemberDTO loginDTO = MemberDAO.getInstance().login(dto);
		
		// 4. 성공 유무 확인
		if(loginDTO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginDTO", loginDTO);
		}
		
		// 5. 응답처리
		ModelAndView mav = new ModelAndView("/ServerProgram1/manager.do", true);
		return mav;
	}

}


