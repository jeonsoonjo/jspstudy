package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertReplyCommand1 implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1단 댓글 달기
		
		// 1. 파라미터 처리
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		long groupno = Long.parseLong(request.getParameter("groupno"));
		
		// 2. DB로 보낼 DTO
		BoardDTO replyDTO = new BoardDTO();
		replyDTO.setAuthor(author);
		replyDTO.setTitle(title);
		replyDTO.setContent(content);
		replyDTO.setIp(ip);
		replyDTO.setGroupno(groupno); // 댓글은 원글과 같은 그룹이 된다
		replyDTO.setDepth(1); // 댓글의 depth는 1이다
		replyDTO.setGroupord(1); // 그룹 내부 순서는 1을 가진다
		// 기존 댓글들의 groupord를 1씩 증가시킨다(DAO의 increseGroupordPreviousReply() 메소드 호출)
		BoardDAO.getInstance().increseGroupordPreviousReply(groupno); // groupno 전달
		
		// 3. 댓글 삽입하기(DAO의 insertReply() 메소드 호출)
		int result = BoardDAO.getInstance().insertReply(replyDTO);
		
		// 4. 응답View로 전달할 데이터
		return new ModelAndView("/11_MYBATIS/board/insertReplyResult1.jsp?result=" + result, true); // redirect
		// 삽입 후에는 반드시 redirect한다
		
	}

}


