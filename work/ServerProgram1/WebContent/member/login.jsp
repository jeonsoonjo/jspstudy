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
			const id = $('#id');
			const name = $('#name');
			f.submit(function(e){
				if(id.val() == '' || name.val() == ''){
					alert('아이디와 이름은 필수입니다.');
					e.preventDefault();
					return false;
				}
			})
		})
	</script>
	
</head>
<body>

	<h1>회원 관리 프로그램</h1>
	
	<form action="/ServerProgram1/login.do" id="f" method="post">
		<input type="text" name="id" id="id" placeholder="ID"><br>
		<input type="text" name="name" id="name" placeholder="Name"><br><br>
		<button>로그인</button>

		<a href="joinPage.do">회원가입</a>
	</form>
</body>
</html>


