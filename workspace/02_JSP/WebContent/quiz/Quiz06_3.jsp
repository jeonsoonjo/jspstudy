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
		request.setCharacterEncoding("utf-8");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		
		Cookie cookie1=new Cookie("address", URLEncoder.encode(address, "utf-8"));
		cookie1.setMaxAge(60*60);
		Cookie cookie2=new Cookie("phone", URLEncoder.encode(phone, "utf-8"));
		cookie2.setMaxAge(60*60);
		Cookie cookie3=new Cookie("email", URLEncoder.encode(email, "utf-8"));
		cookie3.setMaxAge(60*60);
		
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
	%>
	
	<h3>추가정보를 입력하세요</h3>
	<form action="/02_JSP/quiz/Quiz06_4.jsp" method="post">
		<table>
			<tr>
				<td>생년월일</td>
				<td><input type="text" name="year" size="2">년</td>
				<td><input type="text" name="month" size="2">월</td>
				<td><input type="text" name="day" size="2">일</td>
			</tr>
			<tr>
				<td>결혼여부</td>
				<td colspan="3">
					<input type="radio" name="marry" value="미혼">미혼
					<input type="radio" name="marry" value="기혼">기혼
				</td>
			</tr>
			<tr>
				<td>직업</td>
				<td colspan="3">
					<select name="job">
						<option value="developer">개발자</option>
						<option value="doctor">의사</option>
						<option value="teacher">선생님</option>
					</select>
				</td>
			</tr>
		</table>
		<br><br>
		<input type="submit" value="확인">
	</form>
</body>
</html>

