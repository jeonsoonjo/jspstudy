package command.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;

import common.ModelAndView;
import common.Paging;
import dao.BoardDAO;
import dto.BoardDTO;

public class FindBoardCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 처리
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		
		// 2. DB로 보낼 Map
		Map<String, String> map = new HashMap<String, String>(); // object로 잡는 게 더 편함
		map.put("column", column);
		map.put("query", "%" + query + "%"); // 만능문자 처리를 위해 "%"를 붙임 (예: '%글%';)
		
		// 3. DAO의 getFindBoardCount() 메소드 호출
		// 검색 결과의 개수가 totalRecord
		int totalRecord = BoardDAO.getInstance().getFindBoardCount(map);
		// System.out.println("검색 개수: " + totalRecord);
		
		// 4. 검색 결과를 기반으로 페이징 처리
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 한 페이지에 표시할 게시글의 개수 : recordPerPage
		int recordPerPage = 5;
		
		// totalRecord, page, recordPerPage를 통해서
		// beginRecord, endRecord를 계산
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = (endRecord < totalRecord) ? endRecord : totalRecord;
		
		// 5. DB로 보낼 Map 생성
		map.put("beginRecord", beginRecord + "");
		map.put("endRecord", endRecord + "");
		
		// 6. DAO의 selectFindList() 메소드 호출
		List<BoardDTO> list = BoardDAO.getInstance().selectFindList(map);
		
		// 7. 페이징 처리
		// 검색 결과를 페이징 처리하는 경우
		// 검색 관련 파라미터를 Paging 클래스에 전달해야 한다
		// 그렇지 않으면 검색했을 때, 다음 페이지를 누르면 풀리게 된다
		String paging = Paging.getPaging("/10_MODEL2/findBoard.b?column=" + column + "&query=" + query, totalRecord, recordPerPage, page);
		
		// 8. 응답View로 전달할 데이터
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("seq", totalRecord - (page - 1) * recordPerPage);
		
		ModelAndView mav = new ModelAndView("/board/listBoard.jsp", false);
		return mav;
		
	}

}


