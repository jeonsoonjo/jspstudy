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

	<h1>회원 관리 프로그램</h1>

	<form action="/ServerProgram1/loginPage.do" id="f" method="post">
		<input type="text" name="id" id="id" placeholder="ID"><br>
		<input type="text" name="name" id="name" placeholder="Name"><br><br>
		<button>로그인</button>

		<a href="joinPage.do">회원가입</a>
	</form>
	
</body>
</html>

