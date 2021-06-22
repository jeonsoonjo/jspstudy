package command;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.PersonDAO;
import dto.Person;

@WebServlet("/insertPerson.do") // controller없이 진행할 땐 servlet으로 생성한다
public class InsertPersonCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertPersonCommand() {
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
			int count = PersonDAO.getInstance().insertPerson(p);
			JSONObject obj = new JSONObject();
			obj.put("count", count);
			response.getWriter().println(obj);
		} catch (NumberFormatException e) { // 나이: 정수 이외의 값
			response.setStatus(3001);
			response.getWriter().println("나이는 정수만 입력 가능합니다");
		} catch (RuntimeException e) { // 나이 범위 이외의 값
			response.setStatus(3002);
			response.getWriter().println("나이는 0~100 사이만 입력 가능합니다");
		} catch (SQLIntegrityConstraintViolationException e) { // 주민등록번호: 동일한 값 제외
			response.setStatus(3003);
			response.getWriter().println("동일한 주민등록번호는 입력할 수 없습니다");
		} catch (Exception e) { // 이름, 생일: 칼럼 크기와 안 맞을 경우
			response.setStatus(3004);
			response.getWriter().println("입력 데이터를 확인하세요");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}


