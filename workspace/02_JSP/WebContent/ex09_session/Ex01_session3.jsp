<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
  	// session 삭제하기
  	// 1. 특정 속성만 삭제하기(일부)
  	session.removeAttribute("name"); // 이름만 삭제
  	session.removeAttribute("age"); // 나이만 삭제
  	
  	// 2. 전체 삭제하기(초기화)
  	session.invalidate(); // 더 이상 유효하지 않다
  	
  	// 3. session 정보 확인
  	response.sendRedirect("/02_JSP/ex09_session/Ex01_session2.jsp");
%> 
    
    
    