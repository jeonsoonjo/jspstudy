<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<% request.setCharacterEncoding("utf-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="게시글 작성" name="title"/>
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css">
	
<div class="insert_form">
	<form method="post" enctype="multipart/form-data"> <!-- db를 가져다 사용하기에 enctype을 해줘야 한다 -->
		<p class="title">작성자</p>
		<input type="text" id="author" name="author" value="${loginDTO.id}" readonly><br>
		
		<p class="title">제목</p>
		<input type="text" id="title" name="title" autofocus><br>
		
		<p class="title">첨부</p>
		<input type="file" id="filename" name="filename">

		<p class="title">내용</p>
		<textarea id="content" name="content"></textarea><br><br>
		
		<input type="button" id="insert_btn" value="작성하기">
		<input type="reset" id="reset_btn" value="작성초기화">
		<input type="button" id="list_btn" value="목록보기">
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>

