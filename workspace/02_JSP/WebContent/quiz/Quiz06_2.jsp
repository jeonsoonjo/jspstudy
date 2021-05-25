<%@page import="java.net.URLEncoder"%>
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
		// 쿠키에 저장하고 이동(hidden을 줘서 이동할 필요가 없다)
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		
		// 쿠키에 담기
		Cookie cookie1=new Cookie("id", URLEncoder.encode(id, "utf-8"));
		cookie1.setMaxAge(60*60); // 유효시간 1시간
		Cookie cookie2=new Cookie("pw", URLEncoder.encode(pw, "utf-8"));
		cookie1.setMaxAge(60*60);
		Cookie cookie3=new Cookie("name", URLEncoder.encode(name, "utf-8"));
		cookie1.setMaxAge(60*60);
		
		// client에게 전달하기
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
	%>
	
	<h3>연락처를 입력하세요</h3>
	<form action="/02_JSP/quiz/Quiz06_3.jsp" method="post">
		<table>
			<tr>
				<td>주소</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email"></td>
			</tr>
		</table>
		<br><br>
		<input type="submit" value="확인">
	</form>
</body>
</html>

