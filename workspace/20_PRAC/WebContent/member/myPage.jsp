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

	<form id="f">
		${loginUser.name}님 반갑습니다.
		<a href="/20_PRAC/logout.do">로그아웃</a><br><br>
		
		아이디<br>
		${loginUser.id}<br><br>
		
		현재 비밀번호<br>
		<input type="password" id="pw0" name="pw0"><br><br>
		새 비밀번호<br>
		<input type="password" id="pw" name="pw"><br><br>
		새 비밀번호 확인<br>
		<input type="password" id="pw1" name="pw1">
		<input type="button" id="updatePw_btn" value="비밀번호 변경하기"><br><br>
		
		이름<br>
		<input type="text" name="name" id="name" value="${loginUser.name}"><br><br>
		이메일<br>
		<input type="text" name="email" id="email" value="${loginUser.email}"><br><br>
		연락처<br>
		<input type="text" name="phone" id="phone" value="${loginUser.phone}"><br><br>
		가입일<br>
		${loginUser.regdate}<br><br>
		
		<input type="button" id="updateInfo_btn" value="회원정보 수정하기">
		<input type="button" id="delete_btn" value="탈퇴하기">
		
		<!-- 수정, 탈퇴를 구현할 때에는 no값을 hidden으로 전달해야 한다(sql문) -->
		<input type="hidden" name="no" value="${loginUser.no}">
	</form>

</body>
</html>

