package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class LoginCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// login.jsp에서 전송한 파라미터 처리
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 2. DB로 보낼 DTO 생성
		MemberDTO dto = new MemberDTO();
		dto.setId(id);
		dto.setPw(pw);
		
		// 3. DAO의 login() 메소드 호출
		MemberDTO loginDTO = MemberDAO.getInstance().login(dto);
		
		// 4. 성공 유무 확인
		// 성공하면 session에 loginDTO저장, member_log 테이블에 기록
		if(loginDTO != null) {
			// session은 jsp에서만 바로 사용 가능하다
			// java에서는 session을 request에서 가져와야 한다
			HttpSession session = request.getSession();
			session.setAttribute("loginDTO", loginDTO);
			
			// DAO의 loginLog() 메소드 호출
			MemberDAO.getInstance().loginLog(dto);
		}
		
		// 5. 어디로 어떻게 갈 것인가
		ModelAndView mav = new ModelAndView("/10_MODEL2/index.do", true);
		return mav;
	}

}
