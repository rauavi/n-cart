<%@page import="com.ecommerce.model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ecommerce.model.Products"%>
<%@page import="java.util.List"%>
<%@page import="com.ecommerce.dao.ProductDao"%>
<%@page import="com.ecommerce.connection.DbConnection"%>
<%@page import="com.ecommerce.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page buffer="16kb" autoFlush="false" %>
<%
Users auth = (Users) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pd=new ProductDao(DbConnection.getconnection());
List<Products> product=pd.getAllProducts();

ArrayList<Cart> cart_list=(ArrayList<Cart>)session.getAttribute("cart-list");
if(cart_list != null){
	request.setAttribute("cart-list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Welcome to N-Cart</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
		<%
			if( !product.isEmpty()){
				for(Products p:product){%>
					<div class="col-md-3 my-3">
					<div class="card w-100" style="width: 18rem;">
						<img class="card-img-top" src="images/<%= p.getImage() %>"
							alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title"><%= p.getName() %></h5>
							<h6 class="category">Category:<%= p.getCategory() %></h6>
							<h6 class="price">Price: ₹<%= p.getPrice() %></h6>
							<a href="buy-now?quantity=1&id=<%=p.getId() %>" class="btn btn-dark">Buy</a> 
							<a href="add-to-cart?id=<%= p.getId()%>" class="btn btn-primary">Add To Cart</a>
						</div>
					</div>
				</div>
				<% }
			}
		%>
		</div>
	</div>
	<%@include file="includes/foot.jsp"%>
</body>
</html>