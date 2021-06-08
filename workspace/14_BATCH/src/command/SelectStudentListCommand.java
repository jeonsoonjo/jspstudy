package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ModelAndView;
import dao.StudentDAO;

public class SelectStudentListCommand implements StudentCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// listPage.jsp로 보내기 위해 request에 저장
		request.setAttribute("list", StudentDAO.getInstance().selectStudentList());
		
		return new ModelAndView("/student/listPage.jsp", false); // forward
		
	}

}


