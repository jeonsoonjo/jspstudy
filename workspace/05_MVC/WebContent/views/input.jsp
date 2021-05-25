<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 5/28 서버프로그램구현 평가(완벽하게 숙지하기!) -->
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
		onload=function(){
			document.getElementById('btn1').onclick=function(){
				location.href='/05_MVC/DateController';
			}
			document.getElementById('btn2').onclick=function(){
				location.href='/05_MVC/TimeController';
			}
			document.getElementById('btn3').onclick=function(){
				location.href='/05_MVC/LottoController';
			}
		}
	</script>
</head>
<body>

	<%-- VIEW : 언제나 Servlet(Controller)에게 요청한다 --%>
	<input type="button" value="현재 날짜를 알려다오." id="btn1"><br><br>
	
	<input type="button" value="지금 시간을 알려다오." id="btn2"><br><br>
	
	<input type="button" value="로또 번호 6개를 알려다오." id="btn3"><br><br>
	
	<form action="/05_MVC/GugudanController">
		<input type="number" min="2" max="9" name="dan" placeholder="원하는 구구단 입력">
		<button>구구단을 보여다오</button>
	</form>
	<br>

	<a href="/05_MVC/AgeController?age=10">맥주 하나 주이소!</a><br>

	
	<form id="f">
		<input type="text" name="hour" placeholder="지금 시간을 입력하세요">
		<input type="button" value="밥 주세요" id="btn4">
	</form>
	<script>
		// form 알아내기 
		// jQuery -> const form=$('#f');
		const form=document.getElementById('f');
		document.getElementById('btn4').addEventListener('click', function(){
			if(form.hour.value==''){
				alert('지금 시간을 꼭 입력하세요');
				form.hour.focus();
				return;
			}
			form.action = '/05_MVC/MealController';
			form.submit(); // input에 있는 일반 버튼은 아무 것도 실행이 되지 않기에 script에서 submit을 해줘야 한다
		})
		
	</script>
</body>
</html>


