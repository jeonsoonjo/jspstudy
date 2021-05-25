<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<% request.setCharacterEncoding("utf-8"); %>
	<form action="/02_JSP/quiz/Quiz03_3.jsp">
		<h3>2. 좋아하는 운동선수는 누구인가요?</h3>
		<input type="text" name="athlete">
		<!-- hidden을 해주지 않으면 마지막으로 넘어가는 운동선수만 반환값이 나오고 writer와 celebrity는 null로 나온다 -->
		<input type="hidden" name="writer" value="<%=request.getParameter("writer")%>">
		<input type="hidden" name="celebrity" value="<%=request.getParameter("celebrity")%>">
		<button>결과보기</button>
	</form>
</body>
</html>

