<%@page import="dto.MemberDTO"%>
<%@page import="dao.BoardDAO"%>
<%@page import="dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		*{
			font-family: '돋움', sans-serif;
			font-size: 16px;
		}
		.container{
			width: 600px;
			margin: 100px auto;
		}
	</style>
</head>
<body>

	<%
		// 1. 파라미터 처리
		request.setCharacterEncoding("utf-8");
	
		long idx = Long.parseLong(request.getParameter("idx"));
		
		// 2. DAO의 updateHit() 메소드 호출
		// 로그인된 사용자 : loginDTO
		String author1 = ((MemberDTO)session.getAttribute("loginDTO")).getId();
		// 게시글의 작성자 : dto
		BoardDTO dto = BoardDAO.getInstance().selectByIdx(idx);
		String author2 = dto.getAuthor();
		if(!author1.equals(author2)){
			BoardDAO.getInstance().updateHit(idx);
			dto.setHit(dto.getHit() + 1);
		}
		
		// 3. DAO의 selectByIdx() 메소드 호출
		pageContext.setAttribute("dto", dto); // EL사용을 위해서
		
	%>
	
	<div class="container">
		<h3>게시글 번호</h3>
		${dto.idx}<br><br>
		<h3>제목</h3>
		${dto.title}<br><br>
		<h3>작성자</h3>
		${dto.author}<br><br>
		<h3>조회수</h3>
		${dto.hit}<br><br>
		<h3>작성일</h3>
		${dto.postdate}<br><br>
	</div>
	
</body>
</html>


