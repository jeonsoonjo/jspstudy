<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<form action="/11_MYBATIS/insertReply1.do" method="post">
		<!-- 원글의 그룹번호(InsertReplyComamnd의 no를 파라미터 처리 해주기 위해 전달해야 한다 -->
		<input type="hidden" name="groupno" value="${param.groupno}">
		
		<h3>댓글 작성하기</h3>
		
		<p>작성자</p>
		<div><input type="text" name="author" autofocus></div>
		
		<p>제목</p>
		<div><input type="text" name="title" required></div> <!-- required : 필수 -->
		
		<p>내용</p>
		<div><textarea name="content" rows="5" cols="80"></textarea></div>
		
		<button>저장하기</button>
		<input type="reset" value="작성초기화">
		<input type="button" value="작성취소" onclick="history.back()">
	</form>

</body>
</html>


