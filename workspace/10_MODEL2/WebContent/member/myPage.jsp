<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("utf-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="마이페이지" name="title"/>
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css"> <!-- jsp화면이 깨질 때는 css link 추가  -->

<style>
	.my_form{
		width: 300px;
		margin: 0 auto;
	}
	.title{
		font-size: 14px;
		font-weight: 600;
	}
	#f input:not([type=button]){
		padding: 10px;
		width: 100%;	
		height: 50px;
	}
	#f input[type=button]{
		width: 100%;
		height: 50px;
		line-height: 50px;
		background-color: orange;
		border: none;
		font-size: 18px;
	}
	#f input[type=button]:hover{
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
		const pw_btn = $('#pw_btn');
		pw_btn.click(function(){
			location.href = '/10_MODEL2/updatePwPage.m';
		})
		
		const update_btn = $('#update_btn');
		const name = $('#name');
		const email = $('#email');
		const f = $('#f');
		update_btn.click(function(){
			if(name.val() == '${loginDTO.name}' && email.val() == '${loginDTO.email}'){
				alert('변경할 정보가 없습니다');
				return;
			}
			f.attr('action', '/10_MODEL2/updateMember.m');
			f.submit();
		})
		
		const delete_btn = $('#delete_btn');
		delete_btn.click(function(){
			if(confirm('정말 탈퇴하시겠습니까??')){
				 location.href = '/10_MODEL2/deleteMember.m';
			}
		})
	})
</script>


<div class="my_form">
	<form id="f" method="post">
		<!-- 아이디 -->
		<span class="title">아이디</span><br>
		${loginDTO.id}<br><br>
		<!-- 비밀번호 -->
		<span class="title">비밀번호 변경</span><br>
		<input type="button" id="pw_btn" value="비밀번호 변경하기"><br><br>
		<!-- 이름 -->
		<span class="title">이름</span><br>
		<input type="text" id="name" name="name" value="${loginDTO.name}"><br><br>
		<!-- 이메일 -->
		<span class="title">이메일</span><br>
		<input type="text" id="email" name="email" value="${loginDTO.email}"><br><br>	

		<input type="button" id="update_btn" value="회원정보수정"><br><br> <!-- 일반 버튼은(input type=button) submit을 할 수 없기에 script에 메소드를 생성해 submit해야 한다  -->
		<input type="button" id="delete_btn" value="회원탈퇴">
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>



