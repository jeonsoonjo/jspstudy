<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
 
<!-- HTML 주석 : 소스보기에서 보인다 -->
<%-- JSP 주석 : 소스보기에서 안 보인다 --%>

<%--
	JSP
	1. Java Server Page
	2. HTML문서 내부에서 Java 코드를 섞어서 사용할 수 있다
	3. 서블릿 기반의 서버측 언어이다
	4. 모든 JSP 페이지는 서블릿으로 변환된 뒤 실행된다
		abc.jsp -> abc_jsp.java -> abc_jsp.class -> run
		 (JSP)		 (Servlet)
--%>
<%--
	JSP 스크립트 요소
	1. <%@ 지시어 %> : 지시어(directive)
		page 지시어, include 지시어, taglib 지시어
		
	2. <%! 선언부 %> : 선언부(declaration)
		전역변수 선언, 메소드 정의
		
	3. <%= 표현식 %> : 표현식(expression)
		결과 값 출력(변수, 메소드 호출, 연산)
	
	4. <% 스크립트릿 %> : scriptlet
		Java 코드 작성		
--%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<% // 여기는 자바 영역  %>
	
	<% // if문(표현식) %>
	<% int a=10; %>
	<% if(a>0) { %>
		<h3><%=a%>은 양수이다</h3> <% // 표현식을 쓰지 않으면 그냥 텍스트로 인식한다 %>
	<% } else { %>
		<h3><%=a%>은 음수이다</h3>
	<% } %>
	
	
	<% // for문(표현식) %>
	<% for(int i=0; i<9; i++){ %>
		<%=i%><br>
	<% } %>
	

	<% // method 정의(선언부) %>
	<%!
		public int getTotal(int n){
			int total=0;
			for(int i=1; i<=n; i++){
				total += i;
			}
			return total;
		}
	%>
	<h3>결과 : <%=getTotal(10)%></h3> <% // method 호출의 결과는 표현식을 사용한다 %>
	
	
	<% // 전역변수(선언부) %>
	<%!
		int square=1; // 전역 변수이다
		public void power(int n){
			for(int i=1; i<=n; i++){
				square *= i;
			}
		}
	%>
	<% power(5); %> <% // 반환값이 없기 때문에 표현식 말고 그냥 스크립트릿을 사용한다. 텍스트로 인식함 %>
	<h3>결과 : <%=square%></h3>

</body>
</html>


