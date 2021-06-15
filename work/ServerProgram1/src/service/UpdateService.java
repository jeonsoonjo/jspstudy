package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class UpdateService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		String name = request.getParameter("name");
		int point = Integer.parseInt(request.getParameter("point"));
		
		// 2. DB로 보낼 DTO
		MemberDTO loginDTO = (MemberDTO)request.getSession().getAttribute("loginDTO");
		MemberDTO dto = new MemberDTO();
		dto.setNo(loginDTO.getNo());
		dto.setName(name);
		dto.setPoint(point);
		
		String grade = null;
		if(point < 3000) {
			grade = "bronze";
		} else if(point < 4000) {
			grade = "silver";
		} else if(point < 5000) {
			grade = "gold";
		} else {
			grade = "vip";
		}
		dto.setGrade(grade);
		
		// 3. DAO의 updateMember() 메소드 호출
		int result = MemberDAO.getInstance().updateMember(dto);
		try {
			if(result > 0) {
				loginDTO.setName(name);
				loginDTO.setPoint(point);
				loginDTO.setGrade(grade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/ServerProgram1/member/manager.jsp", true);
	}

}


