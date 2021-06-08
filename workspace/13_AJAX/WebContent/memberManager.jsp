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
			selectMemberList();
			selectMemberByNo();
			updateMember();
			init();
			insertMember();
			deleteMember();
		})
		
		// 함수
		
		// 페이징 처리를 위한 현재 페이지
		var page = 1;
		
		// 1. 회원 목록 가져오기
		function selectMemberList(){
			$.ajax({
				url: '/13_AJAX/selectMemberList.do',
				type: 'get',
				data: 'page=' + page,
				dataType: 'json',
				success: function(result){
					/*
						result = {
							"list" :
								[
									{
										"no":5,
										"address":"대구",
										"gender":"여",
										"name":"김아토",
										"id":"user5"
									},
									{
										"no":4,
										"address":"울산",
										"gender":"남",
										"name":"윤건우",
										"id":"user4"
									}
										
								],
							"isExist" : true
						}
					*/
					
					// 전체 회원 수
					$('#totalRecord').text('전체 회원: ' + result.paging.totalRecord + '명');
					
					// 기존의 목록을 화면에서 제거
					// 그렇지 않으면 다른 곳에서 호출했을 때(예: 회원 정보 수정 후 호출할 때)
					// 목록이 또 생기게 된다
					$('#memberList').empty();
					
					// 회원 목록 출력
					if(result.isExist){ // 목록이 있다면
						generateMemberList(result.list);
					} else{ // html 태그를 만들어 appendTo로 해당 아이디 값을 넣는다
						$('<tr>')
						.append($('<td colspan="6">').text('회원 목록이 없습니다.'))
						.appendTo('#memberList');
					}
					
					// 페이징 출력
					var paging = result.paging;
					
					// 페이징 영역 초기화
					$('#paging').empty();
					
					// 이전
					if(paging.beginPage <= paging.pagePerBlock){
						$('<div>이전</div>').appendTo('#paging');
					} else{
						$('<div><a>이전</a></div>').appendTo('#paging')
					}
					
					// 1 2 3 4 5 
					for(let p = paging.beginPage; p<=paging.endPage; p++){
						if(paging.page == p){
							$('<div>' + p + '</div>').appendTo('#paging');
						} else{
							$('<div><a>' + p + '</a></div>').appendTo('#paging');
						}
					}
					
					// 다음
					if(paging.endPage == paging.totalPage){
						$('<div>다음</div>').appendTo('#paging');
					} else{
						$('<div><a>다음</a></div>').appendTo('#paging');
					}
				},
				error: function(xhr, status, error){
					console.log(status + " : " + error);
					alert('회원 목록 로드를 실패했습니다.');
				}
			})
		}
		
		// 1-1. 회원 목록을 받아서 테이블을 생성하는 함수
		function generateMemberList(list){
			$.each(list, function(i, member){
				$('<tr>')
				.append($('<td>').text(member.no))
				.append($('<td>').text(member.id))
				.append($('<td>').text(member.name))
				.append($('<td>').text(member.gender))
				.append($('<td>').text(member.address))
				.append($('<input type="hidden" name="no">').val(member.no))
				.append($('<td>').html('<input type="button" value="조회" id="view_btn"><input type="button" value="삭제" id="delete_btn">'))
				.appendTo('#memberList');
				
			})
		}
		
		// 2. 회원 정보 가져오기
		function selectMemberByNo(){
			/* 
				ajax로 만든 동적 객체$('#view_btn') 처리가 안 되기 때문에 정적 객체로 만들어서 사용해야 한다
				$('#view_btn').on('click', function(){
					alert('수정하시겠습니까?');
				})
			*/
			$('body').on('click', '#view_btn', function(){
				// 해당 회원번호 출력하기
				// var no = $(this).parent().parent().find('input:hidden[name="no"]').val();
				var no = $(this).parents('tr').find('input:hidden[name="no"]').val();
				$.ajax({
					url: '/13_AJAX/selectMemberByNo.do',
					type: 'get',
					data: 'no=' + no,
					success: function(result){
						if(result.isExist){
							$('.left input:text[name="id"]').val(result.id);
							// $('#id').val(result.id);
							$('#name').val(result.name);
							$('.left input:radio[name="gender"][value="' + result.gender + '"]').prop('checked', true);
							$('#address').val(result.address);
							
							// 회원 번호 생성
							$('.left input:hidden[name="no"]').remove(); // 기존 hidden값 제거
							$('.left').append($('<input type="hidden" name="no">').val(result.no));
						} else{
							alert('해당 회원 정보를 확인할 수 없습니다.');
						}
					},
					error: function(xhr, status, error){
						console.log(status + " : " + error);
						alert('회원 정보 로드를 실패했습니다.');
					}
				})
			})
		}
		
		// 3. 회원 정보 수정하기
		function updateMember(){
			$('#update_btn').click(function(){
				// 서버로 JSON 데이터 보내기
				var obj = {
						"no" : $('input:hidden[name="no"]').val(),
						"id" : $('#id').val(),
						"name" : $('#name').val(),
						"gender" : $('input:radio[name="gender"]:checked').val(),
						"address" : $('#address').val()
				};
				// console.log(JSON.stringify(obj)); // JSON 문자열로 변환(javascript 내장객체)
				$.ajax({
					url: '/13_AJAX/updateMember.do',
					type: 'post',
					data: 'member=' + JSON.stringify(obj),
					dataType: 'json',
					success: function(result){
						if(result.isSuccess){
							alert('회원 정보가 수정되었습니다.');
							// 수정이 되었으면 selectMemberList(회원 목록)에도 업데이트 해야 한다
							// 호출하면 회원 목록을 다시 생성한다
							selectMemberList();
						} else{
							alert('변경된 회원 정보가 없습니다.');
						}
					},
					error: function(xhr, status, error){
						console.log(status + " : " + error);
						alert('회원 정보 수정이 실패했습니다.');
					}	
				})
			})
		}
		
		// 4. 회원 정보 초기화
		function init(){
			$('#init_btn').click(function(){
				$('#id').val(''); // id에 빈 문자열 넣기. 즉, 초기화
				$('#name').val('');
				$('input:radio[name="gender"]').prop('checked', false); // check되어 있는 것을 품
				$('#address').val('');
			})
		}
		
		// 5. 회원 등록하기
		function insertMember(){
			$('#insert_btn').click(function(){
				var obj = {
						id : $('#id').val(),
						name : $('#name').val(),
						gender : $('input:radio[name="gender"]:checked').val(),
						address : $('#address').val()
				};
				$.ajax({
					url: '/13_AJAX/insertMember.do',
					type: 'post',
					data: 'member=' + JSON.stringify(obj),
					dataType: 'json',
					success: function(result){
						if(result.isSuccess){
							alert('신규 회원이 등록되었습니다.');
							selectMemberList();
						} else{
							alert('신규 회원으로 등록되지 않았습니다.');
						}
					},
					error: function(xhr, status, error){
						console.log(status + " : " + error);
						alert('회원 등록이 실패했습니다.');
					}
				})
			})
		}
		
		// 6. 회원 삭제하기
		function deleteMember(){
			$('body').on('click', '#delete_btn', function(){
				var no = $(this).parent().parent().find('input:hidden[name="no"]').val();
				if(confirm(no + ' 번 회원을 삭제할까요?')){
					$.ajax({
						url: '/13_AJAX/deleteMember.do',
						type: 'get',
						data: 'no=' + no,
						dataType: 'json',
						success: function(result){
							if(result.isSuccess){
								alert('회원 정보가 삭제되었습니다.');
								selectMemberList();
							} else{
								alert('삭제된 회원이 없습니다.');
							}
						},
						error: function(xhr, status, error){
							console.log(status + " : " + error);
							alert('회원 삭제가 실패했습니다.');
						}
					})
				}
			})
		}
		
	</script>
	<style>
		.container{
			display: flex;
		}
		.left{
			width: 500px;
		}
		.right{
			width: 1000px;
		}
		table{
			border-collapse: collapse;
		}
		td{
			padding: 5px 10px;
			text-align: center;
			border-top: 1px solid gray;
			border-bottom: 1px solid gray;
		}
	</style>
</head>
<body>

	<div class="container">
		<%-- 왼쪽, 회원 등록/수정/보기 --%>
		<div class="left">
			<h3>등록/수정/보기</h3>
			아이디<br>
			<input type="text" name="id" id="id"><br><br>
			이름<br>
			<input type="text" name="name" id="name"><br><br>
			성별<br>
			<label><input type="radio" name="gender" value="남">남</label>
			<label><input type="radio" name="gender" value="여">여</label><br><br>
			주소<br>
			<select name="address" id="address">
				<option value="">지역 선택</option>
				<option value="서울">서울</option>
				<option value="인천">인천</option>
				<option value="대전">대전</option>
				<option value="대구">대구</option>
				<option value="광주">광주</option>
				<option value="울산">울산</option>
				<option value="부산">부산</option>
			</select><br><br>
			<input type="button" value="회원등록" id="insert_btn">
			<input type="button" value="정보수정" id="update_btn">
			<input type="button" value="초기화" id="init_btn">
		</div>
		
		<%-- 오른쪽, 회원 목록/삭제 --%>
		<div class="right">
			<h3>회원 목록/삭제</h3>
			<div id="totalRecord"></div>
			<table>
				<thead>
					<tr>
						<td>회원번호</td>
						<td>아이디</td>
						<td>이름</td>
						<td>성별</td>
						<td>주소</td>
						<td>버튼</td>
					</tr>
				</thead>
				<tbody id="memberList"></tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							<div id="paging"></div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>

</body>
</html>


