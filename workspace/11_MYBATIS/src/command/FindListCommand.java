package command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import common.Paging;
import dao.BoardDAO;
import dto.BoardDTO;

public class FindListCommand implements BoardCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 1. 파라미터 처리
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);
		map.put("query", "%" + query + "%");
		
		// 2. DAO의 getFindRecordCount() 메소드 호출
		// 검색된 게시글의 개수
		int totalRecord = BoardDAO.getInstance().getFindRecordCount(map);
		
		// 1) 페이지 수 처리하기(파라미터로 전달)
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		// 2) 한 페이지에 표시할 게시글의 개수 : recordPerPage
		int recordPerPage = 5;
		// 3) 시작페이지, 종료페이지 구하기 
		// totalRecord, page, recordPerPage를 통해서
		// beginRecord, endRecord를 계산
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		if(endRecord > totalRecord) {
			endRecord = totalRecord;
		}
		
		// 3. DB로 보낼 Map
		map.put("beginRecord", beginRecord);
		map.put("endRecord", endRecord);
		
		// 4. DAO의 findList() 메소드 호출
		List<BoardDTO> list = BoardDAO.getInstance().findList(map);
		
		// 5. 페이징 처리(Paging 클래스)
		String paging = Paging.getPaging("/11_MYBATIS/findList.do?column=" + column + "&query=" + query, totalRecord, recordPerPage, page);
		
		// 6. 응답 View로 전달할 데이터
		request.setAttribute("list", list);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("paging", paging);
		request.setAttribute("seq", totalRecord - (page - 1) * recordPerPage); // sequence 순번 구하는 식
		
		return new ModelAndView("/board/selectList.jsp", false); // forward로 이동(forward는 내부 이동이기에 contextPath는 안 적어도 됨)
	
	}

}


