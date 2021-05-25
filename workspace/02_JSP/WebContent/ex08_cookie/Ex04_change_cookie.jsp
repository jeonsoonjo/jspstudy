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

	<%--
		쿠키를 변경하는 과정
		1. server가 client의 모든 쿠키를 읽어야 한다
		2. 변경하고자 하는 쿠키를 찾아야 한다
		3. 같은 쿠키이름을 가진 새로운 쿠키를 만들어서 덮어쓰기 한다(addCookie)
	--%>

	<%
		// 쿠키이름 "name"인 쿠키를 "초코", 유효시간을 1일간 유지로 변경한다
		
		// 1. 변경할 쿠키 생성
		Cookie ck=new Cookie("name", "초코");
	 	ck.setMaxAge(60*60*24);
		
		// 2. 전체 쿠키를 읽는다(배열)
		Cookie[] cookies=request.getCookies();
		
		// 3. 같은 쿠키를 찾는다(있는지 없는지)
		if(cookies!=null && cookies.length!=0){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("name")){ // 쿠키이름을 가져와 name인지 확인
					response.addCookie(ck); // 같은 쿠키이름이 있다면 client에게 전달(response) : 덮어쓰기 
				}
			}
		}
		
		// 4. 다시 전체 쿠키를 확인
		Cookie[] cookies2=request.getCookies();
		if(cookies2!=null && cookies2.length!=0){
			for(Cookie cookie : cookies2){
				out.println("쿠키이름: "+cookie.getName()+"<br>");
				out.println("유효시간: "+cookie.getMaxAge()+"<br>");
				out.println("쿠키값: "+URLDecoder.decode(cookie.getValue(), "utf-8")+"<br>");
			}
		}
	%>
	
</body>
</html>

