package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.StaffDAO;
import dto.Staff;

@WebServlet("/selectStaffList.do")
public class SelectStaffListCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectStaffListCommand() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Staff> list = StaffDAO.getInstance().selectStaffList();
		JSONArray arr = new JSONArray();
		for(Staff staff : list) {
			JSONObject obj = new JSONObject();
			obj.put("sno", staff.getSno());
			obj.put("name", staff.getName());
			obj.put("dept", staff.getDept());
			obj.put("regdate", staff.getRegdate().toString());
			arr.add(obj);
		}
		// System.out.println(arr);
		response.setCharacterEncoding("utf-8");
		response.getWriter().println(arr);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

