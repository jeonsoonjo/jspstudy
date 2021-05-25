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
		int a=7;
		int b=2; // 현재 EL로 표현할 수 없다

		// EL 표현을 위해서 pageContext에 a와 b를 "속성"으로 저장한다
		pageContext.setAttribute("a", a);
		pageContext.setAttribute("b", b);
		
	%>
	<%--
		속성을 저장하지 않고 값을 호출할 경우
		${a} -> 값이 나오지 않는다 
		EL로 표현할 수 있는 데이터는 JSP 4개 영역 중(page, request, session, application)
		하나에 저장이 되어 있어야 하기 때문이다
	--%>
	${a}<br>
	${b}<br>
	
	<h3>표현식 vs 표현언어</h3>
	<%-- 표현식은 "Java" 연산이다 --%>
	<%=a%> + <%=b%> = <%=a+b%><br>
	<%=a%> - <%=b%> = <%=a-b%><br>
	<%=a%> * <%=b%> = <%=a*b%><br>
	<%=a%> / <%=b%> = <%=a/b%><br> 
	<%=a%> % <%=b%> = <%=a%b%><br>
	<hr>
	
	<%-- 표현언어는 "Java"연산이 아니라 자신의 연산을 따로 가지고 있다 --%>
	${a} + ${b} = ${a+b}<br>
	${a} - ${b} = ${a-b}<br>
	${a} * ${b} = ${a*b}<br>
	${a} / ${b} = ${a/b}<br> <%-- 나누기 결과가 소수점으로 반환, 실제 나누기 연산이다 --%>
	${a} / ${b} = ${a div b}<br>
	${a} % ${b} = ${a%b}<br>
	${a} % ${b} = ${a mod b}<br>
	
</body>
</html>


