<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String id=request.getParameter("id");
	String chk=request.getParameter("chk");
	
	// 체크를 하지 않으면 null상태(isEmpty아님)
	// out.println(chk==null);
	// out.println(chk.isEmpty());
	
	// 1. 체크를 했을 때
	if(chk!=null){
		// 아이디를 쿠키에 보관해 둔다
		Cookie cookie=new Cookie("id", id);
		cookie.setMaxAge(60*60*24*15);
		response.addCookie(cookie);
	}
	
	// 2. 체크를 해제 했을 때
	else{
		Cookie[] cookies=request.getCookies();
		if(cookies!=null && cookies.length!=0){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("id")){
					Cookie ck=new Cookie("id", null);
					ck.setMaxAge(0);
					response.addCookie(ck);
					break;
				}
			}
		}
	}
	
	// login.jsp로 되돌아가기
	response.sendRedirect("/02_JSP/ex08_cookie/Ex06_login.jsp");

%>

