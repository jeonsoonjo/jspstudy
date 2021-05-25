<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	request.setCharacterEncoding("utf-8");
	String site=request.getParameter("site");
%>
<%-- 풀이1. javascript로 이동하기 --%>
<script>
	// location.href='<%=site%>';
</script>

<%-- 풀이2. redirect로 이동하기 --%>
<%
	// site에는 전체 URL이 나와있으니 forward는 안 된다
	// forward : 서버 내부 경로로 이동하기 때문에 전체 URL을 처리할 수 없다
	// redirect : client에게 전체 URL을 응답하면 client(Quiz01_1.jsp)가 다시 이동한다
	response.sendRedirect(site);
%>

	
