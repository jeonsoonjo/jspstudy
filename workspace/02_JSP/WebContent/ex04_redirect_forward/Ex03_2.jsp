<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// redirect
	// 1. 기존의 request를 유지하지 않는다
	// 2. client에게 새로 이동할 경로를 redirect한다
	// 3. client가 이동할 수 있도록 ContextPath를 포함한 전체 경로를 작성한다
	// response.sendRedirect("/02_JSP/ex04/Ex03_3.jsp");

	// 만약 기존의 request를 유지해서 redirect 해야 하는 상황이면?
	// 새로운 파라미터를 붙인다
	request.setCharacterEncoding("utf-8");
	String name=request.getParameter("name"); // 기존의 request
	response.sendRedirect("/02_JSP/ex04/Ex03_3.jsp?name="+URLEncoder.encode(name, "utf-8")); // 새로운 request
			
%>

