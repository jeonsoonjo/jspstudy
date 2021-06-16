<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			const f = $('#f');
			const author = $('#author');
			const title = $('#title');
			const content = $('#content');
			f.submit(function(e){
				if(author.val() == '' || title.val() == '' || content.val() == ''){
					alert('입력된 값이 없습니다. 작성내용을 확인해주세요.');
					e.preventDefault();
					return false;
				}
			})
			
			const insert_btn = $('#insert_btn');
			insert_btn.click(function(){
				if(author.val() != '' && title.val() != '' && content.val() != ''){
					alert('게시글이 등록되었습니다.');
				}
			})
		})
	</script>
</head>
<body>
	
	<h1>게시글 작성하기</h1>
	<form action="/ServerProgram3/insertBoard.do" id="f" method="post">
		작성자<br><br>
		<input type="text" id="author" name="author" placeholder="홍길동"><br><br>
		
		제목<br><br>
		<input type="text" id="title" name="title" placeholder="새 글입니다."><br><br>
		
		내용<br><br>
		<textarea id="content" name="content" rows="5" cols="20" placeholder="새 내용입니다."></textarea><br><br>
		
		<button id="insert_btn">저장하기</button>
		<input type="reset" id="reset_btn" value="작성초기화">
		<input type="button" value="목록보기" onclick="location.href='/ServerProgram3/listBoard.do'">
	</form>

</body>
</html>

