<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
	<%
		// 쿠키 읽기
		// 서버가 client가 보관하고 있는 모든 쿠키를 가져온다(일부만 가져올 수 없다)
		// client의 데이터를 서버가 가져가므로 요청(request)
		
		// 1. 전체 쿠키를 읽는다(배열)
		Cookie[] cookies=request.getCookies();
	
		// 2. 쿠키를 하나씩 읽는다
		if(cookies!=null && cookies.length!=0){
			for(Cookie cookie : cookies){
				out.println("쿠키이름: "+cookie.getName()+"<br>");
				out.println("유효시간: "+cookie.getMaxAge()+"<br>"); // 유효시간 -1의 값은 브라우저를 종류하면 사라진다는 의미
				out.println("쿠키 값: "+URLDecoder.decode(cookie.getValue(), "utf-8")+"<br>");
			}
		}
	%>
	
</body>
</html>

