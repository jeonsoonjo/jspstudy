<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<h1>3초 후 게시판으로 이동합니다</h1>
	<script>
		setTimeout(function(){
			location.href = '/ServerProgram3/listBoard.do';
		}, 3000);
	</script>

</body>
</html>

