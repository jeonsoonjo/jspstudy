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
		<a href="/ServerProgram3/insertPage.do">새글작성</a><br><br>
		<p>전체: ${totalRecord}개 게시물</p>
		<table border="1">
			<thead>
				<tr>
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
					<td>조회수</td>
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
						<td>${dto.no}</td>
						<td><a href="/ServerProgram3/viewBoard.do?no=${dto.no}">${dto.title}</a></td>
						<td>${dto.author}</td>
						<td>${dto.postdate}</td>
						<td>${dto.hit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>

