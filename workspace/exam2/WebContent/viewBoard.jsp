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
				if(confirm('게시글을 삭제하시겠습니까?')){
					location.href = '/exam2/deleteBoard.do?idx=${dto.idx}';
				}
			})
			
			const reply_btn = $('#reply_btn');
			reply_btn.click(function(){
				if(confirm())
			})
		})
	</script>
	<style>
		table{
			border-collapse: collapse;
		}
	</style>
</head>
<body>

		<h3>${dto.idx}번 게시글</h3>
		작성자: ${dto.author}<br>
		제목: ${dto.title}<br>
		내용: ${dto.content}<br>
		작성일: ${dto.postdate}<br><br>
		
		<input type="button" value="목록보기" onclick="location.href='/exam2/boardList.do'">
		<input type="button" value="삭제하기" id="delete_btn"><br>
		<hr><br>
		
		<div class="reply_list">
			<p>댓글목록</p>
			<table border="1">
				<tbody>
					<c:forEach var="replyDTO" items="${replyList}">
						<tr>
							<td>${replyDTO.author}&nbsp;&nbsp;&nbsp;</td>
							<td>${replyDTO.content}&nbsp;&nbsp;&nbsp;</td>
							<td><a href="/exam2/deleteReply.do">댓글삭제</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<br><br>
		
		<div class="reply_form">
			<form action="/exam2/insertReply.do" method="post">
				<input type="hidden" name="boardIdx" value="${dto.idx}">
				작성자 :
				<input type="text" id="author" name="author"><br>
				<textarea id="content" name="content" rows="5" cols="30"></textarea>
				<button id="reply_btn">댓글작성</button>
			</form>
		</div>
		
</body>
</html>
