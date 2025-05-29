<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.ecommerce.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="com.ecommerce.model.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ecommerce.connection.DbConnection"%>
<%@page import="com.ecommerce.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page buffer="16kb" autoFlush="false" %>
<%
DecimalFormat df = new DecimalFormat("#.##");
request.setAttribute("df", df);
Users auth = (Users) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartproducts = null;
if (cart_list != null) {
	ProductDao pDao = new ProductDao(DbConnection.getconnection());
	double total = pDao.totalPriceOfCart(cart_list);
	cartproducts = pDao.getcartProducts(cart_list);
	request.setAttribute("cart-list", cart_list);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>

<%@include file="includes/head.jsp"%>
<title>Cart Page</title>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 20px;
}
</style>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price: ₹ ${(total)>0?df.format(total):0}</h3>
			<a class="mx-3 btn btn-primary" href="check-out">Check Out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartproducts) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td>₹ <%=df.format(c.getPrice())%></td>
					<td>
						<form action="buy-now" method="post" class="form-inline">
							<input type="hidden" value="<%=c.getId()%>" name="id"
								class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre"
									href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus"></i>
								</a> 
									<input type="text" name="quantity"
									readonly="readonly" class="form-control"
									value="<%=c.getQuantity()%>">
									 <a
									class="btn btn-sm btn-incre"
									href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus"></i>
									</a>
							</div>
							<button class="btn btn-primary " type="submit">Buy</button>
						</form>
					</td>
					<td><a href="remove-from-cart?<%=c.getId()%>"
						class="btn btn-sm btn-danger">Remove</a></td>
				</tr>
				<%
				}
				}
				%>

			</tbody>
		</table>
	</div>

	<%@include file="includes/foot.jsp"%>
</body>
</html>