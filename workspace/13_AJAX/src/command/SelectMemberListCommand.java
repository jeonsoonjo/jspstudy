package command;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import controller.ModelAndView;
import dao.MemberDAO;
import dto.Member;

public class SelectMemberListCommand implements MemberCommand {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 파라미터 처리
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1")); // null값일 때 1페이지 사용하겠다는 의미
		
		// 전체 회원 수 구하기
		int totalRecord = MemberDAO.getInstance().getMemberCount();
		
		// beginRecord, endRecord 구하기
		int recordPerPage = 5;
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = (endRecord < totalRecord) ? endRecord : totalRecord;
		
		// beginRecord + endRecord : Map
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRecord", beginRecord);
		map.put("endRecord", endRecord);
		
		// beginRecord ~ endRecord 페이지 목록 가져오기
		List<Member> list = MemberDAO.getInstance().selectMemberList(map);
		
		// totalPage, beginPage, endPage 구하기
		int totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) {
			totalPage++;
		}
		// 한 블록당 페이지를 5개 배치하기
		int pagePerBlock = 5;
		// 시작페이지 구하기
		int beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		// 종료페이지 구하기
		int endPage = beginPage + pagePerBlock - 1;
		endPage = endPage < totalPage ? endPage : totalPage;
		
		// 페이지 관련 변수만 저장할 JSON
		JSONObject paging = new JSONObject();
		paging.put("totalRecord", totalRecord);
		paging.put("page", page);
		paging.put("totalPage", totalPage);
		paging.put("pagePerBlock", pagePerBlock);
		paging.put("beginPage", beginPage);
		paging.put("endPage", endPage);
		
		// JSP로 반환할 결과 JSON
		JSONObject obj = new JSONObject();
		obj.put("paging", paging);
		
		if(list.size() > 0) { // 데이터가 있다는 얘기
			JSONArray arr = new JSONArray();
			for(Member member : list) {
				JSONObject obj2 = new JSONObject();
				obj2.put("no", member.getNo());
				obj2.put("id", member.getId());
				obj2.put("name", member.getName());
				obj2.put("gender", member.getGender());
				obj2.put("address", member.getAddress());
				arr.add(obj2);
			}
			obj.put("list", arr);
			obj.put("isExist", true);
			// System.out.println(obj.toJSONString());
		} else {
			obj.put("isExist", false);
		}
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj);
		
		return null;
		
	}

}


