package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.MemberDAO;
import dto.MemberDTO;

public class MemberListService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		List<MemberDTO> list = MemberDAO.getInstance().memberList();
		request.setAttribute("list", list);
		return new ModelAndView("/member/list.jsp", false);
	}

}


