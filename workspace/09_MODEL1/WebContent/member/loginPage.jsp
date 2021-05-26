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
			const pw = $('#pw');
			f.on('submit', function(e){
				if(id.val() == '' || pw.val() == ''){
					alert('아이디와 비밀번호를 입력해주세요.');
					e.preventDefault();
					return false;
				}
			})
		})
	</script>
	<style>
		.container{
			width: 500px;
			margin: 0 auto;
			text-align: center;
		}
	</style>
</head>
<body>

	<div class="container">
		<form id="f" method="post" action="/09_MODEL1/member/login.jsp">
			<h1>로그인</h1>
			<input type="text" name="id" placeholder="ID"><br>
			<input type="password" name="pw" placeholder="Password"><br><br>
			<button>로그인</button>
			<input type="button" name="join" value="회원가입" onclick="location.href='joinPage.jsp'">
		</form>
	</div>

</body>
</html>


