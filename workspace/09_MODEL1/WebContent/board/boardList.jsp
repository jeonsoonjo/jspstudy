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
	<style>
		.container{
			width: 500px;
			margin: 100px auto;
		}
		a{
			text-decoration: none;
			display: inline-block;
		}
		#write{
			float: right;
		}
		#home{
			float: left;
		}
		table{
			width: 100%;
			border-collapse: collapse;
		}
		td{
			padding: 5px;
			border-top: 1px solid gray;
			border-bottom: 1px solid gray;
			text-align: center;
		}
		td:nth-of-type(1) { width: 200px; }
		td:nth-of-type(2) { width: 400px; }
		td:nth-of-type(3) { width: 100px; }
		td:nth-of-type(4) { width: 100px; }
		td:nth-of-type(5) { width: 150px; }
	</style>
</head>
<body>

	<div class="container">
		<a href="insertPage.jsp" id="write">새 글 작성하기</a>
		<a href="/09_MODEL1/index.jsp" id="home">첫 화면으로 가기</a>
		<br><hr><br>
		<%
			// session에 올라간 boardDTO 제거 -> removeAttribute
			// invalidate는 전체를 다 삭제 함(로그인도 풀림)
			// removeAttribute는 원하는 값만 지울 수 있음
			if(session.getAttribute("boardDTO") != null){
				session.removeAttribute("boardDTO");				
			}
		
			List<BoardDTO> list = BoardDAO.getInstance().selectAll();
			pageContext.setAttribute("list", list);
		%>
		<table>
			<thead>
				<tr>
					<td>게시글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.idx}</td>
						<td><a href="view.jsp?idx=${dto.idx}">${dto.title}</a></td>
						<td>${dto.author}</td>
						<td>${dto.hit}</td>
						<td>${dto.postdate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>


