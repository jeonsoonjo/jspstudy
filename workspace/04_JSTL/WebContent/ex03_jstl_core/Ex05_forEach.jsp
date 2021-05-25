<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	
	<%-- 반복문을 forEach로 사용하기 --%>
	<%
		for(int i=0; i<10; i++){
			out.println(i+"<br>");
		}
	%>
	<hr>
	
	<c:forEach var="i" begin="0" end="9" step="1">
		${i}<br>
	</c:forEach>
	
	
	<%-- 배열을 forEach문으로 사용하기 --%>
	<%
		String[] hobbies={"낚시", "운동", "게임"};
		pageContext.setAttribute("hobbies", hobbies); // EL을 사용하려면 pageContext에 올려줘야 함
	%>
	<c:forEach var="hobby" items="${hobbies}">
		취미 : ${hobby}<br>
	</c:forEach>
	

	<%-- varStatus : index, count --%>
	<c:forEach var="hobby" items="${hobbies}" varStatus="k">
		${hobby}의 인덱스 : ${k.index}, 순번 : ${k.count}<br>
	</c:forEach>
	
	
	<%-- ArrayList를 forEach문으로 사용하기 --%>
	<%
		List<String> list=new ArrayList<>();
		list.add("튀김");
		list.add("김밥");
		list.add("떡볶이");
		pageContext.setAttribute("list", list);
	%>
	<c:forEach var="food" items="${list}" varStatus="f">
		${food}의 인덱스 : ${f.index + 1}<br>
	</c:forEach>
	
</body>
</html>


