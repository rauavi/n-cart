package com.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DbConnection;
import com.ecommerce.dao.OrderDao;
import com.ecommerce.model.Cart;
import com.ecommerce.model.Order;
import com.ecommerce.model.Users;


@WebServlet("/buy-now")
public class BuyNowSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			Date date =new Date();
			Users auth = (Users) request.getSession().getAttribute("auth");
			if(auth != null) {
				String productid=request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if(productQuantity <=0) {
					productQuantity=1;
				}
				Order ordermodel=new Order();
				ordermodel.setId(Integer.parseInt(productid));
				ordermodel.setUid(auth.getId());;
				ordermodel.setQuantity(productQuantity);
				ordermodel.setDate(formatter.format(date));
				
				OrderDao odao=new OrderDao(DbConnection.getconnection());
				boolean result=odao.insertOrder(ordermodel);
				
				if(result) {
					ArrayList<Cart> cart =(ArrayList<Cart>)request.getSession().getAttribute("crat-list");
					if(cart != null) {
						for(Cart c:cart) {
							if(c.getId()==Integer.parseInt(productid)) {
								cart.remove(cart.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("orders.jsp");
					
				}else {
					out.write("failed..");
				}
			}else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
