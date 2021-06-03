<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.link{
			color: blue;
			font-size: 10px;
			text-decoration: none;
		}
		.link:hover{
			cursor: pointer;
		}
	</style>
</head>
<body>
	
	<a href="/11_MYBATIS/insertPage.do">새글작성</a>
	<br><br><br>
	
	<form action="/11_MYBATIS/findList.do">
		<select name="column">
			<option value="TITLE">내용</option>
			<option value="AUTHOR">작성자</option>
			<option value="BOTH">내용+작성자</option>
		</select>
		<input type="text" name="query"><button>검색</button>
	</form>
	
	전체 게시글: ${totalRecord}<br>
	
	<table border="1">
		<thead>
			<tr>
				<td>순번</td>
				<td>제목</td>
				<td>작성자</td>
				<td>최종수정일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5">작성된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="dto" items="${list}" varStatus="k">
					<tr>
						<td>${seq - k.index}</td>
						<td>
							<c:if test="${dto.depth == 1}">
								&nbsp;&nbsp;&nbsp;[re]
							</c:if>
							${dto.title}
							<c:if test="${dto.depth == 0}">
								<font size="1"><a class="link" href="/11_MYBATIS/insertReplyPage.do?groupno=${dto.groupno}">답글</a></font>
							</c:if>
						</td>
						<td>${dto.author}</td>
						<td>${dto.lastmodified}</td>
						<td>${dto.hit}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">${paging}</td>
			</tr>
		</tfoot>
	</table>
	<br><br>
	<a href="/11_MYBATIS/index.jsp">첫 화면으로</a>
	
</body>
</html>


