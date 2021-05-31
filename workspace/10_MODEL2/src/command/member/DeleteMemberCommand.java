package command.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class DeleteMemberCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. session에서 loginDTO(no) 정보 알아내기
		HttpSession session = request.getSession();
		MemberDTO loginDTO = (MemberDTO)session.getAttribute("loginDTO");
		
		// 2. DAO의 deleteMemberLog() 메소드 호출 -> 외래키 관계이기 때문에 먼저 삭제해야 한다
		MemberDAO.getInstance().deleteMemberLog(loginDTO.getId());
		
		// 3. DAO의 deleteMember()메소드 호출
		int result = MemberDAO.getInstance().deleteMember(loginDTO.getNo());
		
		// 4. 응답 및 이동
		try {
			PrintWriter out = response.getWriter();
			if(result > 0) {
				// session 초기화
				session.invalidate();
				// 응답 및 이동
				out.println("<script>");
				out.println("alert('탈퇴되었습니다. 이용해 주셔서 감사합니다!')");
				out.println("location.href='/10_MODEL2/index.do'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원탈퇴가 취소되었습니다')");
				out.println("history.back()");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
