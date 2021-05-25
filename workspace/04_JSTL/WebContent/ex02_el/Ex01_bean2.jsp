<%@page import="ex02_el.Person"%>
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
		
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		
		// 빈으로 생성했을 때(DB에 데이터를 보내기 위해 bean 사용함) 호출
		// 빈으로 생성한 p를 EL로 쓸 수 있는가? NO(4개 영역에 포함되지 않았기 때문에)
		Person p=new Person();
		p.setName(name);
		p.setAge(age);
		
		// EL 표현을 위해서 4개 영역에 저장을 해줘야 호출할 수 있다
		pageContext.setAttribute("p", p); // 객체를 저장한다
	%>

	<%-- EL의 객체 표현방법 --%>
	<h3>이름: ${p.getName()}</h3>
	<h3>나이: ${p.getAge()}</h3>
	<hr>
	
	<h3>이름: ${p.name}</h3> <%-- 내부적으로 getter를 호출한다 --%>
	<h3>이름: ${p.age}</h3>

</body>
</html>


