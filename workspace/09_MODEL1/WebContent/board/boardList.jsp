<%@page import="dto.PageVO"%>
<%@page import="dao.BoardDAO"%>
<%@page import="dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		body{
			font-size: 14px;
		}
		.container{
			width: 500px;
			margin: 100px auto;
		}
		a{
			text-decoration: none;
			display: inline-block;
		}
		#write{
			float: right;
		}
		#home{
			float: left;
		}
		table{
			width: 100%;
			border-collapse: collapse;
		}
		td{
			padding: 5px;
			border-top: 1px solid gray;
			border-bottom: 1px solid gray;
			text-align: center;
		}
		td:nth-of-type(1) { width: 200px; }
		td:nth-of-type(2) { width: 400px; }
		td:nth-of-type(3) { width: 100px; }
		td:nth-of-type(4) { width: 100px; }
		td:nth-of-type(5) { width: 150px; }
		
		.paging{
			text-align: center;
		}
	</style>
</head>
<body>

	<div class="container">
		<a href="insertPage.jsp" id="write">새 글 작성하기</a>
		<a href="/09_MODEL1/index.jsp" id="home">첫 화면으로 가기</a>
		<br><hr><br>
		<%
			// session에 올라간 boardDTO 제거 -> removeAttribute
			// invalidate는 전체를 다 삭제 함(로그인도 풀림)
			// removeAttribute는 원하는 값만 지울 수 있음
			if(session.getAttribute("boardDTO") != null){
				session.removeAttribute("boardDTO");				
			}
		
			// Paging 처리
			// 1. PageVO 객체 생성
			PageVO pageVO = new PageVO();
			
			// 2. 전체 레코드 개수 구하기(totalRecord)
			int totalRecord = BoardDAO.getInstance().getTotalRecord();
			pageVO.setTotalRecord(totalRecord);
			
			// 3. 전체 페이지의 개수 구하기
			pageVO.setTotalPage();
			
			// 4. 파라미터 처리
			// 1) page가 안 넘어오면  page = 1로 처리
			// 2) page가 넘어오면 넘어온 page로 처리
			String strPage = request.getParameter("page");
			if(strPage != null){
				pageVO.setPage(Integer.parseInt(strPage));
			}
			
			// 5. 시작게시글 번호, 종료게시글 번호 구하기
			/* 예시
				1. 전체 11개 게시글이 있다 - totalRecord
				2. 한 페이지에 3개의 게시글을 표시한다 - recordPerPage
				page = 1, beginRecord=1, endRecord=3
				page = 2, beginRecord=4, endRecord=6
				page = 3, beginRecord=7, endRecord=9
				page = 4, beginRecord=10, endRecord=11 
			*/
			// 시작게시글번호 = (현재페이지번호 - 1) * 페이지당레코드수 + 1
			int beginRecord = (pageVO.getPage() -1 ) * pageVO.getRecordPerPage() + 1;
			pageVO.setBeginRecord(beginRecord);
			// 종료게시글번호 = 시작게시글번호 + 페이지당레코드수 - 1
			// 단, 종료레코드번호와 전체레코드수 중 작은 값을 종료레코드번호로 사용한다
			int endRecord = beginRecord + pageVO.getRecordPerPage() - 1;
			endRecord = (endRecord < totalRecord) ? endRecord : totalRecord;
			pageVO.setEndRecord(endRecord);

			// 6. 블록당 시작페이지, 종료페이지 구하기
			/* 예시
				1. 전체 12개 페이지가 있다 - totalPage
				2. 한 블록에 3개의 페이지를 표시한다 - pagePerBlock
				page = 1~5, beginRecord=1, endRecord=5
				page = 6~10, beginRecord=6, endRecord=10
				page = 11~15, beginRecord=11, endRecord=15
			*/
			// 시작페이지번호 = ((현재페이지번호 - 1) / 블록당페이지수) * 블록당페이지수 + 1
			int beginPage = ((pageVO.getPage() - 1) / pageVO.getPagePerBlock()) * pageVO.getPagePerBlock() + 1;
			pageVO.setBeginPage(beginPage);
			// 종료페이지번호 = 시작페이지번호 + 블록당페이지수 - 1
			// 단, 종료페이지번호와 전체페이지수 중 작은 값을 종료페이지번호로 사용한다
			int endPage = beginPage + pageVO.getPagePerBlock() - 1;
			endPage = (endPage < pageVO.getTotalPage()) ? endPage : pageVO.getTotalPage();
			pageVO.setEndPage(endPage);
			pageContext.setAttribute("pageVO", pageVO);
			// Paging 처리 끝
			
			// beginRecord ~ endRecord 사이의 목록만 가져오기
			List<BoardDTO> list = BoardDAO.getInstance().selectAll(pageVO);
			pageContext.setAttribute("list", list);
			
			/* 전체 게시글 보여줄 때
				List<BoardDTO> list = BoardDAO.getInstance().selectAll();
				pageContext.setAttribute("list", list);
			*/
			
		%>
		<table>
			<thead>
				<tr>
					<td>게시글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>조회수</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.idx}</td>
						<td><a href="view.jsp?idx=${dto.idx}">${dto.title}</a></td>
						<td>${dto.author}</td>
						<td>${dto.hit}</td>
						<td>${dto.postdate}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5">
						<%-- 1. 이전 블록으로 이동 : 1블록은 이전 블록이 없다 -> 비활성화 --%>
						<c:if test="${pageVO.beginPage < pageVO.pagePerBlock}">
							이전&nbsp;
						</c:if>
						<c:if test="${pageVO.beginPage >= pageVO.pagePerBlock}">
							<a href="/09_MODEL1/board/boardList.jsp?=${pageVO.beginPage -1}">이전&nbsp;</a>
						</c:if>
						
						<%-- 2. 페이지 번호 --%>
						<c:forEach var="page" begin="${pageVO.beginPage}" end="${pageVO.endPage}" step="1">
							<c:if test="${pageVO.page == page}"> <!-- 현재 페이지 이면 -->
								<span class="now_page">${page}&nbsp;</span>
							</c:if>
							<c:if test="${pageVO.page != page}">
								<a href="/09_MODEL1/board/boardList.jsp?page=${page}">${page}&nbsp;</a>
							</c:if>
						</c:forEach>
						
						<%-- 3. 다음 블록으로 이동 --%>
						<c:if test="${pageVO.endPage < pageVO.totalPage}">
							<a href="/09_MODEL1/board/boardList.jsp?page=${pageVO.endPage +1}">다음&nbsp;</a>
						</c:if>
						<c:if test="${pageVO.endPage >= pageVO.totalPage}">
							 다음&nbsp; 
						</c:if>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>


