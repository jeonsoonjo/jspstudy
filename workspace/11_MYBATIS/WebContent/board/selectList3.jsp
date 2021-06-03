<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<style>
		.link{
			color: blue;
			font-size: 10px;
			text-decoration: none;
		}
		.link:hover{
			cursor: pointer;
		}
		.insert_reply{
			display: none;
		}
		.link_delete{
			color: red;
			font-size: 10px;
			text-decoration: none;
		}
	</style>
	<script>
		$(document).ready(function(){
			// '답글'을 클릭하면 아래 insert_reply 클래스가 toggle된다
			/* 구조 확인하기
				<tr> -- parent2
					<td> -- parent1
						<a class="link">답글</a>
					</td>
				</tr>
				
				<tr class="insert_reply"> -- parent2의 형제
				</tr>
			*/
			const links = $('.link');
			// links.click(); links는 배열 이므로 사용할 수 없다
			// jQuery의 for문인 $.each(배열, function(인덱스, 요소){}) 사용하면 된다
			$.each(links, function(i, link){
				$(link).click(function(){ // $(link) == <a class="link">답글</a>
					$(this).parent().parent().next().toggleClass('insert_reply');
				})
			})
		})
	</script>
</head>
<body>
	
	<a href="/11_MYBATIS/insertPage.do">새글작성</a>
	<br><br><br>
	
	<form action="/11_MYBATIS/findList.do">
		<select name="column">
			<option value="TITLE">내용</option>
			<option value="AUTHOR">작성자</option>
			<option value="BOTH">내용+작성자</option>
		</select>
		<input type="text" name="query"><button>검색</button>
		<button>전체</button>
	</form>
	
	전체 게시글: ${totalRecord}<br>
	
	<table border="1">
		<thead>
			<tr>
				<td>순번</td>
				<td>제목</td>
				<td>작성자</td>
				<td>최종수정일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5">작성된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="dto" items="${list}" varStatus="k">
					<tr>
						<td>${seq - k.index}</td>
						<td>
							<c:if test="${dto.state == 0}">
								<c:forEach begin="1" end="${dto.depth}" step="1">
									&nbsp;&nbsp;
								</c:forEach>
								<c:if test="${dto.depth > 0}">
									[re]
								</c:if>
								${dto.title}
								<a class="link">답글</a>
								<a class="link_delete" href="/11_MYBATIS/delete.do?no=${dto.no}">삭제</a>
							</c:if>
							<c:if test="${dto.state == -1}">
								삭제된 게시글입니다.
							</c:if>
						</td>
						<td>${dto.author}</td>
						<td>${dto.lastmodified}</td>
						<td>${dto.hit}</td>
					</tr>
					<tr class="insert_reply">
						<form action="/11_MYBATIS/insertReply3.do">
							<input type="hidden" name="no" value="${dto.no}">
							<td><input type="text" name="author" placeholder="작성자"></td>
							<td><input type="text" name="title" placeholder="제목"></td>
							<td><input type="text" name="content" placeholder="내용"></td>
							<td><button>작성</button></td>
							<td><input type="button" value="취소"></td>
						</form>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">${paging}</td>
			</tr>
		</tfoot>
	</table>
	<br><br>
	<a href="/11_MYBATIS/index.jsp">첫 화면으로</a>
	
</body>
</html>


