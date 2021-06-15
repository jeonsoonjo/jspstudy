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
		f.submit(function(e){
			const id = $('#id');
			const name = $('#name');
			if(id.val() == '' || name.val() == ''){
				alert('가입정보를 모두 입력하세요.');
				e.preventDefault();
				return false;
			}
		})
	})
	
	</script>
</head>
<body>
	<h1>회원 가입 폼</h1>
	
	<form action="/ServerProgram1/join.do" id="f" method="post">
		<span class="title">아이디</span>
		<input type="text" name="id" id="id"><br>
		
		<span class="title">이름</span>
		<input type="text" name="name" id="name"><br>
		
		<button>회원가입</button>
		<input type="button" value="돌아가기" onclick="location.href='/ServerProgram1/loginPage.do'">
	</form>
	
</body>
</html>

