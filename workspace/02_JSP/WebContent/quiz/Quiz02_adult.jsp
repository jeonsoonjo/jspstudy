<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	int age=Integer.parseInt(request.getParameter("age"));
%>    

<script>
	alert('성인이군요! 당신의 나이는 <%=age%>살 이므로 주류를 구매하실 수 있습니다');
	// foward는 이동 경로(URL)가 변하지 않는 이동이기 때문에 history에 잡히지 않는다
	// 따라서, history.go(-2)가 아니라 go(-1) = back()과 같다
	history.back();
</script>
    
    