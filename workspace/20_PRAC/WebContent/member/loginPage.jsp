<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<div class="login_form">
		<form action="/20_PRAC/login.do" id="f" method="post">
			<input type="text" name="id" id="id" placeholder="ID"><br>
			<span class="message" id="id_message"></span><br>
			
			<input type="password" name="pw" id="pw" placeholder="Password"><br>
			<span class="message" id="pw_message"></span><br>
			
			<button>로그인</button>
		</form>
	</div>

</body>
</html>


