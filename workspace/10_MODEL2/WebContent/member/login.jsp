<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("utf-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="로그인" name="title"/>
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css"> <!-- jsp화면이 깨질 때는 css link 추가  -->
로그인 화면입니다.

<%@ include file="../layout/footer.jsp" %>

