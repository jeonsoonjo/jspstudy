package command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.PersonDAO;
import dto.Person;


@WebServlet("/updatePerson.do")
public class UpdatePersonCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdatePersonCommand() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		try {
			String sno = request.getParameter("sno");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			if(age < 0 || age > 100) {
				throw new RuntimeException(); // 예외 강제 발생
			}
			String birthday = request.getParameter("birthday");
			
			Person p = new Person();
			p.setSno(sno);
			p.setName(name);
			p.setAge(age);
			p.setBirthday(birthday);
			int count = PersonDAO.getInstance().updatePerson(p);
			JSONObject obj = new JSONObject();
			obj.put("count", count);
			response.getWriter().println(obj);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

