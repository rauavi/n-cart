package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Order;
import com.ecommerce.model.Products;

public class OrderDao {

	private Connection connection;
	private PreparedStatement pre;
	private ResultSet rs;
	private String query;
	
	public OrderDao(Connection connection) {
		this.connection = connection;
	}
	
	public boolean insertOrder(Order model) {
		boolean result=false;
		try {
			query = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
			pre=this.connection.prepareStatement(query);
			pre.setInt(1, model.getId());
			pre.setInt(2, model.getOrderid());
			pre.setInt(3, model.getQuantity());
			pre.setString(4, model.getDate());
			pre.executeUpdate();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Order> useOrders(int id){
		List<Order> list=new ArrayList<Order>();
		
		try {
			query ="SELECT * FROM ORDERS WHERE u_id=? ORDER BY orders.o_id DESC";
			pre=this.connection.prepareStatement(query);
			pre.setInt(1, id);
			rs=pre.executeQuery();
			
			while(rs.next()) {
				Order order=new Order();
				ProductDao pdao=new ProductDao(this.connection);
				int pId=rs.getInt("p_id");
				
				Products product;
					product = pdao.getSingleProduct(pId);
					order.setOrderid(rs.getInt("o_id"));
					order.setId(pId);
					order.setName(product.getName());
					order.setCategory(product.getCategory());
					order.setPrice(product.getPrice()*rs.getInt("o_quantity"));
					order.setQuantity(rs.getInt("o_quantity"));
					order.setDate(rs.getString("o_date"));
					list.add(order);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void cancelOrder(int id) {
		
		try {
			query="DELETE FROM orders WHERE o_id=?";
			pre=this.connection.prepareStatement(query);
			pre.setInt(1, id);
			pre.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
