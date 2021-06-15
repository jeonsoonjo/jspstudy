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
				} else{
					alert('게시글이 등록되었습니다.');
					f.submit();
				}
			})
		})
	</script>
</head>
<body>

	<form action="/exam2/insertBoard.do" id="f" method="post">
		작성자 :
		<input type="text" id="author" name="author"><br>
		
		제목 :
		<input type="text" id="title" name="title"><br>
		
		내용 :<br>
		<textarea rows="5" cols="30" placeholder="내용을 작성하세요"></textarea><br>
		
		<button>작성하기</button>
		<input type="reset" id="reset_btn" value="작성초기화">
		<input type="button" value="돌아가기" onclick="history.back();">
	</form>

</body>
</html>

