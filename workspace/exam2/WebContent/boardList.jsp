<%@page import="dao.BoardDAO"%>
<%@page import="dto.BoardDTO"%>
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

	<div class="container">
		<a href="/exam2/insertPage.do">새글작성</a>
		<table border="1">
			<thead>
				<tr>
					<td>번호</td>
					<td>작성자</td>
					<td>제목</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty list}">
					<tr>
						<td colspan="5">작성된 게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.idx}</td>
						<td>${dto.author}</td>
						<td><a href="/exam2/viewBoard.do?idx=${dto.idx}">${dto.title}</a></td>
						<td>${dto.hit}</td>
						<td>${dto.postdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>

