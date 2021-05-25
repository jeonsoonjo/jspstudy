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

	<%--
		// 속성으로 request에 값을 저장
		<c:set var="a" value="20" scope="request" />
		<c:set var="b" value="5" scope="request" />
		위처럼 속성에 담았을 때는 문자열 "a", "b"로 해석이 되기 때문에 결과값이 정확하지 않다
		 -> 문자열이 전달되는 경우를 대비해서 크기 비교는 아래와 같이 한다
			${a > b} -> ${a-b > 0}
			${a < b} -> ${a-b < 0}
		이렇게 변경해주면 문자열, 숫자 모두 크기 비교가 정상적으로 작동한다
	--%>
	
	<%
		// 속성으로 request에 값을 저장
		// 저장된 값은 숫자이다
		request.setAttribute("a", 20);
		request.setAttribute("b", 5);
	%>
	<jsp:forward page="Ex02_set2.jsp" />

</body>
</html>


