package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.BoardDTO;

public class InsertReplyCommand3 implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1단 댓글 달기
		
		// 1. 파라미터 처리
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		long no = Long.parseLong(request.getParameter("no"));
		
		// 2. DB로 보낼 DTO
		BoardDTO replyDTO = new BoardDTO();
		replyDTO.setAuthor(author);
		replyDTO.setTitle(title);
		replyDTO.setContent(content);
		replyDTO.setIp(ip);
		
		// 1) 원글 정보 가져오기(DAO의 메소드 호출)
		BoardDTO boardDTO = BoardDAO.getInstance().selectBoard(no);
		
		// 2) 가져온 원글(부모) 정보를 이용해서 replyDTO 생성
		replyDTO.setGroupno(boardDTO.getGroupno());
		replyDTO.setGroupord(boardDTO.getGroupord() + 1);
		replyDTO.setDepth(boardDTO.getDepth() + 1);
		
		// 3) 같은 그룹의 기존 댓글들 중에, 가져온 원글(부모)의 groupord보다 큰 댓글들을 1씩 증가시킨다
		BoardDAO.getInstance().increseGroupordOtherReply(boardDTO);
		
		// 3. 댓글 삽입하기(DAO의 insertReply() 메소드 호출)
		int result = BoardDAO.getInstance().insertReply(replyDTO);
		
		// 4. 응답View로 전달할 데이터
		return new ModelAndView("/11_MYBATIS/board/insertReplyResult3.jsp?result=" + result, true); // redirect
		// 삽입 후에는 반드시 redirect한다
		
	}

}


