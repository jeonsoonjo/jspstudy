<%@page import="com.sun.xml.internal.ws.api.pipe.NextAction"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("utf-8");
		String agree=request.getParameter("agree");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		
		if(agree.equals("yes")){ // 동의 함
			// 저장할 파일명 생성(id명.txt)
			String filename=id + ".txt";
			// 저정할 파일 경로 지정(realPath 활용)
			String realPath=application.getRealPath("quiz/" + filename);
			// 저장할 파일 생성
			File file=new File(realPath);
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			bw.write("가입 아이디: "+id); bw.newLine();
			bw.write("가입 비밀번호: "+pw); bw.newLine();
			bw.write("가입 성명: "+name); bw.newLine();
			if(bw!=null){
				bw.close();
			}
			// 가입이 끝나면 "시스템 변화"가 생긴 것으로 가정하고, redirect를 한다
			// 이동할 때 생성된 파일명을 함께 전달한다
			response.sendRedirect("/02_JSP/quiz/Quiz05_4.jsp?filename="+filename);
		} else{ // 동의 안 함
			out.println("<h1>가입되지 않았습니다.</h1>");
			out.println("<a href=\"/02_JSP/quiz/Quiz05_1.jsp\">다시 가입하기</a>");
		}
	%>

</body>
</html>

