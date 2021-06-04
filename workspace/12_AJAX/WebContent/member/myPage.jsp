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
			
			// 이벤트
			$('#update_pw_btn').click(function(){
				updatePw();
			})
			
			// 함수
			function updatePw(){
				if('${loginUser.pw}' != $('#pw0').val()){
					alert('현재 비밀번호를 확인하세요.');
					return;
				} else if('${loginUser.pw}' == $('#pw').val()){
					alert('현재 비밀번호와 동일한 비밀번호입니다.');
					return;
				} else if($('#pw').val() == ''){ // 비밀번호 정규식으로 변경
					alert('새 비밀번호를 입력하세요.');
					return;
				} else if($('#pw').val() != $('#pw1').val()){
					alert('새로 입력한 비밀번호가 맞지 않습니다. 다시 확인해주세요!');
					return;
				}
				$.ajax({
					url: '/12_AJAX/updatePw.do',
					type: 'post',
					data: $('#my_form').serialize(), 
					dataType: 'json',
					success: function(obj){ // DAO, controller, xml 작업 후 넘어오기
						if(obj.result == 1){
							alert('비밀번호가 변경되었습니다.');
							// update 후 빈칸으로 초기화
							// 함수를 만들어서 사용해도 상관없음
							$('#pw0').val('');
							$('#pw').val('');
							$('#pw1').val('');
						} else{
							alert('비밀번호가 변경되지 않았습니다.');
						} 
					},
					error: function(){
						alert('비밀번호 변경 과정에서 오류가 발생했습니다');
					}
					
				})
			}
		})
	</script>
</head>
<body>

	<form id="my_form">
		${loginUser.name}님 반갑습니다. <a href="/12_AJAX/logout.do">로그아웃</a>
		<br><br>
		
		아이디<br>
		${loginUser.id}<br><br>
		
		현재 비밀번호<br>
		<input type="password" id="pw0" name="pw0"><br><br>
		새 비밀번호<br>
		<input type="password" id="pw" name="pw"><br><br>
		새 비밀번호 확인<br>
		<input type="password" id="pw1" name="pw1">
		<input type="button" id="update_pw_btn" value="비밀번호 변경하기"><br><br>
		
		이름<br>
		<input type="text" id="name" name="name" value="${loginUser.name}"><br><br>
		이메일<br>
		<input type="text" id="email" name="email" value="${loginUser.email}"><br><br>
		연락처<br>
		<input type="text" id="phone" name="phone" value="${loginUser.phone}"><br><br>
		가입일시<br>
		${loginUser.regdate}<br><br>
		
		<input type="hidden" name="no" value="${loginUser.no}">
		
	</form>
	
</body>
</html>


