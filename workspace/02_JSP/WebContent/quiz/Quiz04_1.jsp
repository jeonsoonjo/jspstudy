<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>랜덤 계산기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			$('#result').on('blur', function(){ // keyup으로 하면 1글자만 인식해서 - 계산을 오류메시지로 반환한다(예: 3-9=-6)
				if(isNaN($(this).val())){ // $(this) == $('#result')
					alert('정수만 입력할 수 있습니다.');
					$(this).val('');
					$(this).focus();
				}
			})
		})
	</script>
</head>
<body>
	<%
		// 숫자 1~9 랜덤 생성
		int digit1;
		int digit2;
		digit1=(int)(Math.random() * 9) + 1;
		digit2=(int)(Math.random() * 9) + 1;
		
		// 연산자(+, -, *, /, %) 랜덤 생성
		String[] opList={"+","-","*","/","%"};
		String op=opList[(int)(Math.random() * 5)];
		
		// 연산
		int answer=0;
		switch(op){
		case "+": answer = digit1 + digit2; break;
		case "-": answer = digit1 - digit2; break;
		case "*": answer = digit1 * digit2; break;
		case "/": answer = digit1 / digit2; break;
		case "%": answer = digit1 % digit2; break;
		}
	%>
	
	<form action="/02_JSP/quiz/Quiz04_2.jsp" method="post">
		<h3>랜덤 계산기</h3>
		<%=digit1%>&nbsp;<%=op%>&nbsp;<%=digit2%>&nbsp;=&nbsp;
		<input type="text" name="result" size="4" id="result">
		<input type="hidden" name="digit1" value="<%=digit1%>">
		<input type="hidden" name="digit2" value="<%=digit2%>">
		<input type="hidden" name="op" value="<%=op%>">
		<input type="hidden" name="answer" value="<%=answer%>">
		<button>제출</button>
	</form>
</body>
</html>

