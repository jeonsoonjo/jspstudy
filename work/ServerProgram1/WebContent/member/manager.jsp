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

	<h1>회원 관리 시스템</h1>
	<a href="/ServerProgram1/logout.do">로그아웃</a><br>
	<hr>
	
	<form>
	아이디&nbsp;&nbsp;&nbsp;이름&nbsp;&nbsp;&nbsp;등급&nbsp;&nbsp;&nbsp;포인트<br><br>
	&nbsp;<input type="text" placeholder="${loginDTO.id}">
	<button>수정하기</button>
	<button>탈퇴하기</button>
	
	</form>

</body>
</html>

