<div class="container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">N-Cart</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Cart<span class="badge badge-danger px-1">${ cart-list.size(1)}</span></a></li>
				<%
					if(auth != null){%>
						<li class="nav-item"><a class="nav-link" href="orders.jsp">Order</a></li>
						<li class="nav-item"><a class="nav-link" href="user-logout">Log-Out</a></li>
					<%}else{%>
						<li class="nav-item"><a class="nav-link" href="login.jsp">Log-In</a></li>
					<%}
				%>
			</ul>

		</div>
	</nav>
</div>