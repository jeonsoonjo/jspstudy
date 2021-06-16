<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			const delete_btn = $('#delete_btn');
			delete_btn.click(function(){
				if(confirm('게시글을 삭제할까요?')){
					location.href = '/ServerProgram3/deleteBoard.do?no=${dto.no}';
				}
			})
			
			const reply_btn = $('#reply_btn');
			const author = $('#author');
			const content = $('#content');
			reply_btn.click(function(){
				if(author.val() == '' || content.val() == ''){
					alert('댓글이 등록되지 않았습니다.');
					return false;
				} else{
					alert('댓글이 등록되었습니다.');
					locaion.href = '/ServerProgram3/listBoard.do';
				}
			})
		})
	</script>
</head>
<body>

		<h3>${dto.no}번 게시글</h3>
		작성자: ${dto.author}<br><br>
		작성일: ${dto.postdate}<br><br>
		작성IP: ${dto.ip}<br><br>
		조회수: ${dto.hit}<br><br>
		제목: ${dto.title}<br><br>
		내용<br><br>
		${dto.content}<br><br>
		
		<input type="button" value="삭제하기" id="delete_btn">
		<input type="button" value="목록보기" onclick="location.href='/ServerProgram3/listBoard.do'">
		<hr><br>
		
		<%-- 댓글입력 --%>
		<div class="reply_form">
			<form action="/ServerProgram3/insertReply.do" method="post">
				<textarea id="content" name="content" rows="3" cols="30" placeholder="첫 댓글입니다"></textarea><br>
				<input type="hidden" name="boardNo" value="${dto.no}">
				<input type="text" id="author" name="author" placeholder="작성자">
				<button id="reply_btn">작성</button>
			</form>
		</div>
		
		<%-- 댓글목록 --%>
		<div class="reply_list">
			<table border="1">
				<tbody>
					<c:if test="${empty replyList}">
						<tr>
							<td>작성된 댓글이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="replyDTO" items="${replyList}">
						<tr>
							<td>${replyDTO.content}</td>
							<td>${replyDTO.author}</td>
							<td>${replyDTO.ip}</td>
							<td>${replyDTO.postdate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br><br>
		
</body>
</html>
