<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="게시글 작성" name="title" />
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css">    
<style>
	.board_view {
		width: 100%;
		display: flex;
		justify-content: space-between;
	}
	.board_content {
		width: 40%;
	}
	.board_img {
		width: 55%;
	}
	.board_img > img {
		width: 100%;
	}
</style>

<script>
	$(document).ready(function(){
		const delete_btn = $('#delete_btn');
		delete_btn.click(function(){
			if(confirm('삭제하시겠습니까?')){
				location.href = '/10_MODEL2/deleteBoard.b?idx=${dto.idx}'; /* 누구를 삭제할 것인지 */
			}
		})
	})
</script>

<div class="board_view">
	<div class="board_content">
		<p class="title">작성자</p>
		${dto.author}<br><br>
		<p class="title">작성자IP</p>
		${dto.ip}<br><br>
		<p class="title">최종수정일</p>
		${dto.lastmodified}<br><br>
		<p class="title">조회수</p>
		${dto.hit}<br><br>
		<p class="title">제목</p>
		${dto.title}<br><br>
		<p class="title">내용</p>
		<pre>${dto.content}</pre><br><br>
	</div>
	<div class="board_img">
		<img src="/10_MODEL2/archive/${dto.filename}" alt="첨부파일 이미지">
	</div>
</div>
<div>
	<input type="button" value="목록보기" onclick="location.href='${referer}'">
	<c:if test="${loginDTO.id == dto.author}"> <!-- 작성자만 볼 수 있다(작성자id == 게시글 작성자author) -->
		<input type="button" id="update_btn" value="수정하기">
		<input type="button" id="delete_btn" value="삭제하기">
	</c:if>
</div>    

<%@ include file="../layout/footer.jsp" %>

    
    