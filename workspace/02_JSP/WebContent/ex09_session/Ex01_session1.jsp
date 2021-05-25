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
		// session
		
		// 1. jsp의 내장객체이다
		// 2. java에서는 HttpSession 타입의 클래스
		// 3. java에서는 request로부터 session을 구한다
		//		HttpSession session = request.getSession();
		// 4. 서버 측 저장 영역이기 때문에 쿠키에 비해 보안이 뛰어나다(쿠키는 클라이언트)
		// 5. 로그인 된 사용자 정보를 session에 저장한다
		
		// session에 저장하기
		session.setAttribute("name", "브레드");
		session.setAttribute("age", 50);
		
		// redirect
		response.sendRedirect("/02_JSP/ex09_session/Ex01_session2.jsp");
	%>

</body>
</html>

