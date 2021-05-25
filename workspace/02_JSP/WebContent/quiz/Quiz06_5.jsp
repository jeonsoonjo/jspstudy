<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<%!
		// 선언부 : method를 정의하는 영역(method 생성)
		
		// 1. 메소드명 : getCookieValue
		// 2. 반환타입 : String
		// 3. 매개변수 : Cookie[] cookies, String cookieName
		
		public String getCookieValue(Cookie[] cookies, String cookieName){
			String result=null;
			try{
				if(cookies!=null && cookies.length!=0){
					for(Cookie cookie : cookies){
						if(cookie.getName().equals(cookieName)){
							result=URLDecoder.decode(cookie.getValue(), "utf-8");
						}
					}
				}
			} catch(UnsupportedEncodingException e){
			
			}
			return result;
		}
	%>
	
	<!-- 쿠키 가져오기 -->
	<% Cookie[] cookies=request.getCookies(); %>
	
	<h3>가입 내용</h3>
	<ul>
		<li>아이디: <%=getCookieValue(cookies, "id")%></li>
		<li>비밀번호: <%=getCookieValue(cookies, "pw")%></li>
		<li>이름: <%=getCookieValue(cookies, "name")%></li>
		<li>주소: <%=getCookieValue(cookies, "address")%></li>
		<li>전화번호: <%=getCookieValue(cookies, "phone")%></li>
		<li>이메일: <%=getCookieValue(cookies, "email")%></li>
		<li>생년월일: <%=getCookieValue(cookies, "birthday")%></li>
		<li>결혼여부: <%=getCookieValue(cookies, "marry")%></li>
		<li>직업: <%=getCookieValue(cookies, "job")%></li>
	</ul>
	<br>
	
</body>
</html>

