<%@page import="com.ecommerce.model.Order"%>
<%@page import="com.ecommerce.dao.OrderDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.ecommerce.model.Cart"%>
<%@page import="java.util.*"%>
<%@page import="com.ecommerce.connection.DbConnection"%>
<%@page import="com.ecommerce.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page buffer="16kb" autoFlush="false" %>
<%
DecimalFormat df = new DecimalFormat("#.##");
request.setAttribute("df", df);
Users auth = (Users) request.getSession().getAttribute("auth");
List<Order> orders=null;
if (auth != null) {
	request.setAttribute("auth", auth);
	orders=new OrderDao(DbConnection.getconnection()).useOrders(auth.getId());
} else {
	response.sendRedirect("login.jsp");
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart-list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Order Page</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<%
			if(orders!=null){
				for(Order o:orders){%>
				<tr>
					<td scope="col"><%= o.getDate() %></td>
					<td scope="col"><%= o.getName() %></td>
					<td scope="col"><%= o.getCategory() %></td>
					<td scope="col"><%= o.getQuantity() %></td>
					<td scope="col"><%= df.format(o.getPrice()) %></td>
					<td scope="col"><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderid()%>">Cancel</a></td>
				</tr>
				<%}
			}
			%>
			</tbody>
		</table>
	</div>
	<%@include file="includes/foot.jsp"%>
</body>
</html>