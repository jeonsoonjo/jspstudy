<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 예외가 발생하면 이동할 페이지 등록 --%>
<%@ page errorPage="Quiz02_error.jsp" %>

<%
	request.setCharacterEncoding("utf-8");
	int age=request.getParameter("age").isEmpty()?0:Integer.parseInt(request.getParameter("age"));
	if(age < 20){
		// forward(request를 그대로 전달)
		request.getRequestDispatcher("Quiz02_kid.jsp").forward(request, response);
	} else{
		request.getRequestDispatcher("Quiz02_adult.jsp").forward(request, response);
	}
%>


