package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		
		// 2. DB로 보낼 DTO
		BoardDTO dto = new BoardDTO();
		dto.setAuthor(author);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setIp(ip);
		
		// 3. DAO의 insert() 메소드 호출
		int result = BoardDAO.getInstance().insert(dto);
		
		// 4. 결과를 처리할 insertResult.jsp를 생성(script 처리 하면 됨)하고 이동
		return new ModelAndView("/11_MYBATIS/board/insertResult.jsp?result=" + result, true); // redirect
		// 반드시 redirect로 이동(수정 후에 값을 전달하기에 수정 전 값까지 전달할 필요가 없다. ?result= 파라미터로 그 값을 전달)
		
	}

}

