<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_tableMaker();
			fn_selectList();
			fn_insert();
			//fn_update();
			fn_delete();
		})
		
		// 회원 목록을 받아서 테이블 생성
		function fn_tableMaker(arr){
			$('#person_list').empty();
			$.each(arr, function(i, person){
				$('<tr>')
				.append($("<td name='sno'>").text(person.sno))
				.append($('<td name="name" onclick="setDate('+i+');">').text(person.name))
				.append($('<td name="age">').text(person.age))
				.append($('<td name="birthday">').text(person.birthday))
				.append($('<td name="regdate">').text(person.regdate))
				.append($('<input type="hidden" name="sno">').val(person.sno))
				.append($('<input type="button" onclick="fn_update()" id="update_btn" value="수정">'))
				.append($('<input type="button" id="delete_btn" value="삭제">'))
				.append($('</tr>'))
				.appendTo('#person_list');
			});
		}
		
		//데이터 세팅
		function setDate(num){

			var sno = $("#person_list tr:eq("+num+") td:eq(0)").text();
			var name = $("#person_list tr:eq("+num+") td:eq(1)").text();
			var age = $("#person_list tr:eq("+num+") td:eq(2)").text();
			var birthday = $("#person_list tr:eq("+num+") td:eq(3)").text();
			
			$("#sno").val(sno);
			$("#name").val(name);
			$("#age").val(age);
			$("#birthday").val(birthday);
			
			
		}
		
		// 회원 조회
		function fn_selectList(){
			$.ajax({
				url: 'selectPersonList.do',
				type: 'get',
				dataType: 'json',
				success: function(arr){
					fn_tableMaker(arr);
				},
				error: function(xhr, textStatus, errorThrown){
					
				}
			}); // end ajax
		}
		
		// 회원 등록
		function fn_insert(){
			$('#insert_btn').click(function(){
				var regSNO = /^[0-9]{6}$/;
				if(!regSNO.test($('#sno').val())){
					alert('주민등록번호는 6자리 숫자입니다.');
					return;
				}
				$.ajax({
					url: 'insertPerson.do',
					type: 'post',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(obj){
						if(obj.count > 0){
							alert('회원 등록이 완료되었습니다.');
							fn_selectList();
						} else{
							alert('회원 등록에 실패했습니다.');
						}
					},
					error: function(xhr, textStatus, errorThrown){
						if (xhr.status == 3001 || xhr.status == 3002 || xhr.status == 3003 || xhr.status == 3004) {
							alert(xhr.responseText);
						}
					}
				}); // end ajax
			}); // click
		}
		
		// 회원 정보 수정
		function fn_update(){
			
			$('body').on('click', '#update_btn', function(){
				
				$.ajax({
					url: 'updatePerson.do',
					type: 'get',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(obj){
						
						if(obj.count > 0){
							alert('정보가 수정되었습니다');
							fn_selectList();
						} else{
							alert('정보가 수정되지 않았습니다');
						}
					},
					error: function(xhr, textStatus, errorThrown){
						
					}
				}); // end ajax
			})
		}
		
		// 회원 삭제
		function fn_delete(){
			$('body').on('click', '#delete_btn', function(){
				var sno = $(this).parent().find('input:hidden[name="sno"]').val();
				if(confirm(sno + ' 님의 정보를 삭제할까요?')){
					$.ajax({
						url: 'deletePerson.do',
						type: 'get',
						data: 'sno=' + sno,
						dataType: 'json',
						success: function(obj){
							if(obj.count > 0){
								alert(sno + ' 님의 정보가 삭제되었습니다');
								fn_selectList();
							} else{
								alert(sno + ' 님의 정보가 삭제되지 않았습니다');
							}
						},
						error: function(xhr, textStatus, errorThrown){
							
						}
					}); // end ajax
				}
			})
		}
		
	</script>
	<style>
		.container {
			display: flex;
		}
		.insert_form {
			margin-right: 20px;
		}
		table {
			width: 500px;
			border-collapse: collapse;
		}
		td {
			border-top: 1px solid black;
			border-bottom: 1px solid black;
		}
		td:nth-of-type(1) { width: 150px; }
		td:nth-of-type(2) { width: 100px; }
		td:nth-of-type(3) { width: 100px; }
		td:nth-of-type(4) { width: 150px; }
		td:nth-of-type(5) { width: 150px; }
	</style>
</head>
<body>

	<div class="container">
		<div class="insert_form">
			<form id="f">
				<input type="text" name="sno" id="sno" placeholder="주민등록번호(6자리)"><br>
				<input type="text" name="name" id="name" placeholder="이름"><br>
				<input type="text" name="age" id="age" placeholder="나이"><br>
				<input type="text" name="birthday" id="birthday" placeholder="생일"><br>
				<input type="button" id="insert_btn" value="등록하기">
			</form>
		</div>
		
		<div class="list_form">
			<table>
				<thead>
					<tr>
						<td>주민등록번호</td>
						<td>이름</td>
						<td>나이</td>
						<td>생일</td>
						<td>등록일</td>
					</tr>
				</thead>
				<tbody id="person_list">
				
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>

