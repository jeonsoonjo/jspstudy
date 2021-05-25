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
		String digit1=request.getParameter("digit1");
		String digit2=request.getParameter("digit2");
		String op=request.getParameter("op");
		String answer=request.getParameter("answer");
		String result=request.getParameter("result");
		
		String message="";
		if(answer.equals(result)){ // String 타입은 equals 사용!!
			message += digit1 + op + digit2 + "=" + result + "<br>" + "정답입니다";
		} else{
			message += "제출: " + digit1 + op + digit2 + "=" + result + "<br>";
			message += "정답: " + digit1 + op + digit2 + "=" + answer;
		}
	%>
	
	<%=message%>
	<br><br>
	<a href="/02_JSP/quiz/Quiz04_1.jsp">다시풀기</a>
</body>
</html>

