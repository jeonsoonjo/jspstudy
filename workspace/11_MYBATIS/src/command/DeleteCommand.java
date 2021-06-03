package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;

public class DeleteCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		long no = Long.parseLong(request.getParameter("no"));
		
		// 2. DAO의 delete() 메소드 호출
		int result = BoardDAO.getInstance().delete(no);
		
		// 3. 응답 View 데이터 전달
		return new ModelAndView("/11_MYBATIS/board/deleteResult.jsp?=result" + result, true); // redirect
	
	}

}
