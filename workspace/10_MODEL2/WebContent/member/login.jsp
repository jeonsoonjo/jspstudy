<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("utf-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="로그인" name="title"/>
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css"> <!-- jsp화면이 깨질 때는 css link 추가  -->

<style>
	.login_form{
		width: 300px;
		margin: 0 auto;
		margin-top: 30px;
	}
	#f input{
		padding: 10px;
		width: 100%;
		height: 50px;
	}
	#f button{
		width: 100%;
		height: 50px;
		line-height: 50px;
		background-color: orange;
		border: none;
		font-size: 18px;
	}
	#f button:hover{
		color: white;
		font-weight: 500;
	}
	.message{
		font-size: 12px;
		color: crimson;
	}
</style>

<script>
	$(document).ready(function(){
		const f = $('#f');
		const id = $('#id');
		const pw = $('#pw');
		const id_message = $('#id_message');
		const pw_message = $('#pw_message');
		f.submit(function(e){
			if(id.val() == ''){
				id_message.text('아이디를 입력하세요');
				id.focus();
				e.preventDefault();
				return false;
			} else if(pw.val() == ''){
				id_message.text('');
				pw_message.text('비밀번호를 입력하세요');
				pw.focus();
				e.preventDefault();
				return false;
			}
		})
	})
</script>

<!-- 로그인 화면 생성 -->
<div class="login_form">
	<form action="/10_MODEL2/login.m" id="f" method="post">
		<input type="text" id="id" name="id" placeholder="ID"><br>
		<span class="message" id="id_message"></span><br>
		<input type="password" id="pw" name="pw" placeholder="Password"><br>
		<span class="message" id="pw_message"></span><br><br>
		<button>로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>



