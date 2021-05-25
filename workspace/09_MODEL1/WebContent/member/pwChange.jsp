<%@page import="dao.MemberDAO"%>
<%@page import="dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

      
<%
	// 1. 파라미터 처리
	request.setCharacterEncoding("utf-8");

	String pw = request.getParameter("pw");
	
	// 2. DB로 보낼 DTO 생성
	MemberDTO dto = new MemberDTO();
	dto.setPw(pw);
	dto.setNo(((MemberDTO)session.getAttribute("loginDTO")).getNo()); // session에 저장된 값을 꺼낸다
	
	// 3. DAO의 updatePw() 메소드 호출
	int result = MemberDAO.getInstance().updatePw(dto);
	
	// 4. 결과
	if(result > 0){ // if(result == 1) 1건 이상 수정이 되었다면
		// session의 loginDTO 비밀번호 갱신(변경된 비번)
		MemberDTO loginDTO = (MemberDTO)session.getAttribute("loginDTO");
		loginDTO.setPw(pw);
		out.println("<script>");
		out.println("alert('비밀번호가 변경되었습니다.')");
		out.println("location.href='myPage.jsp'");
		out.println("</script>");
	} else{
		out.println("<script>");
		out.println("alert('비밀번호가 변경되지 않았습니다.')");
		out.println("history.back()");
		out.println("</script>");
	}
	
%>  
    
    
    