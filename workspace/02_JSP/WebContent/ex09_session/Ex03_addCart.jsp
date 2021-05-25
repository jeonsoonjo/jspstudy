<%@page import="java.util.ArrayList"%>
<%@page import="ex09_session.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");

	String product=request.getParameter("product");
	String quantity=request.getParameter("quantity");
	
	// product과 quantity를 하나의 데이터로 저장하려면 DTO 또는 Map을 사용한다
	// Map<product, quantity>
	// Map<String, String> map=new HashMap<>();
	// map.put(product, quantity);
	
	// ProductDTO
	ProductDTO dto=new ProductDTO();
	dto.setProduct(product);
	dto.setQuantity(quantity);
	
	// ProductDTO를 여러 개 저장할 수 있는 cart 생성
	// 1. session에 저장된 cart를 꺼낸다
	// 2. 만약 session에 없으면 그때 새로 cart를 생성한다
	ArrayList<ProductDTO> cart=(ArrayList<ProductDTO>)session.getAttribute("cart");
	if(cart==null){ // 장바구니가 비어있다면(즉, 첫 장바구니)
		cart=new ArrayList<>(); // session에 생성된다
		session.setAttribute("cart", cart);
	}
	cart.add(dto); // ArrayList에 추가하는 add() 메소드
	
%>

<script>
	alert('<%=dto.getProduct()%> 제품을 장바구니에 추가했습니다.');
	if(confirm('장바구니로 가려면 "확인", 계속 쇼핑하려면 "취소"를 누르세요.')){
		location.href='/02_JSP/ex09_session/Ex03_cart.jsp';
	} else{
		location.href='/02_JSP/ex09_session/Ex03_mall.jsp';
	}
</script>


