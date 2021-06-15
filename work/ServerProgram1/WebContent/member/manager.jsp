<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			const f = $('#f');
			const name = $('#name');
			const point = $('#point');
			$('#update_btn').click(function(e){
				if(name.val() == '${loginDTO.name}' && point.val() == '${loginDTO.point}'){
					alert('수정할 내용이 없습니다.');
					e.preventDefault();
					return false;
				}
				f.attr('action', '/ServerProgram1/update.do');
				f.submit();
			})
			
			$('#delete_btn').click(function(){
				if(confirm('탈퇴하시겠습니까?')){
					location.href = '/ServerProgram1/delete.do';
				}
			})
		})
	</script>
</head>
<body>

	<h1>회원 관리 시스템</h1>
	<a href="/ServerProgram1/logout.do">로그아웃</a><br>
	<hr>
	
	<form id="f" method="post">
		<table>
			<thead>
				<tr>
					<td>아이디</td>
					<td>이름</td>
					<td>등급</td>
					<td>포인트</td>
				</tr>
			</thead>
			<tbody>
				<tr>	
					<td>${loginDTO.id}</td>
					<td><input type="text" name="name" value="${loginDTO.name}"></td>
					<td>${loginDTO.grade}</td>
					<td><input type="text" name="point" value="${loginDTO.point}"></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						<input type="button" value="수정하기" id="update_btn">
						<input type="button" value="탈퇴하기" id="delete_btn">
					</td>
				</tr>
			</tfoot>
		</table>
	</form>

</body>
</html>

