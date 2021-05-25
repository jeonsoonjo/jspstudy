<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h3>이동하고자 하는 사이트 명을 선택하고 확인 버튼을 누르세요</h3>
	<%
		String[][] sites={ // sites[i][0] : 사이트명, sites[i][1] : 홈페이지 주소
				{"구글", "https://www.google.com"},
				{"네이버", "https://www.naver.com"},
				{"다음", "https://www.daum.net"},
				{"네이트", "https://www.nate.com"},
		};
	%>
	<form action="/02_JSP/quiz/Quiz01_2.jsp">
	사이트명: 
		<select name="site">
			<!-- 
				<option value="https://www.google.com" selected>구글</option>
				<option value="https://www.naver.com">네이버</option>
				<option value="https://www.daum.net">다음</option>
				<option value="https://www.nate.com">네이트</option>
			-->
			<!-- jsp를 배웠으니 jsp 활용하기 -->
			<% for(int i=0; i<sites.length; i++){ %>
				<option value="<%=sites[i][1]%>"><%=sites[i][0]%></option>
			<% } %>
		</select>
		<button>확인</button>
	</form>
</body>
</html>

