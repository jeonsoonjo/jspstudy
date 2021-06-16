<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>


		<h3>게시글 번호</h3>
		${boardDTO.idx}<br><br>
		<h3>제목</h3>
		${dto.title}<br><br>
		<h3>작성자</h3>
		${dto.author}<br><br>
		<h3>조회수</h3>
		${dto.hit}<br><br>
		<h3>작성일</h3>
		${dto.postdate}<br><br>
</body>
</html>

