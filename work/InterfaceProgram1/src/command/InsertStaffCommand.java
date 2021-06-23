package command;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.StaffDAO;
import dto.Staff;

@WebServlet("/insertStaff.do")
public class InsertStaffCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertStaffCommand() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			String dept = request.getParameter("dept");
			
			Staff s = new Staff();
			s.setSno(sno);
			s.setName(name);
			s.setDept(dept);
			int count = StaffDAO.getInstance().insertStaff(s);
			JSONObject obj = new JSONObject();
			obj.put("count", count);
			response.getWriter().println(obj);
		} catch (SQLIntegrityConstraintViolationException e) {
			// 사원번호 : 동일한 값을 입력하는 경우
			response.setStatus(3003);
			response.getWriter().println("이미 존재하는 사원번호는 추가할 수 없습니다.");
		} catch (SQLException e) {
			response.setStatus(3004);
			response.getWriter().println("입력 데이터를 확인하세요.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


