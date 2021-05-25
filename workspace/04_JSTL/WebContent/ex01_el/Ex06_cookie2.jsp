<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<!-- 표현언어로 cookie에 저장된 데이터를  ${cookie.이름.값}으로 쉽게 호출하면 된다 -->
	<h3>이름: ${cookie.name.value}</h3>
	<h3>나이: ${cookie.age.value}</h3>

</body>
</html>


