package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class GetTime {

	public String execute(HttpServletRequest request) {
		
		// 날짜에서 시간만 추출하기
		Date time=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("H:mm:ss");
		request.setAttribute("time", sdf.format(time));
		
		return "views/output.jsp";
	}
	
}
