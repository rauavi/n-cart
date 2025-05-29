package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Products;

public class ProductDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection connection) {
		this.connection =connection;
	}
	
	public List<Products> getAllProducts() {
		List<Products> products=new ArrayList<Products>();
		
		
		try {
			query="SELECT * FROM products";
			pst =this.connection.prepareStatement(query);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				Products row=new Products();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public List<Cart> getcartProducts(ArrayList<Cart> cartlist){
		List<Cart> products=new ArrayList<Cart>();
		try {
			if(cartlist.size()>0) {
				for(Cart item:cartlist) {
					query="SELECT * FROM products WHERE id=?";
					pst=this.connection.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					while(rs.next()) {
						Cart row=new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public Products getSingleProduct(int id) {
		Products row=null;
		try {
			query="SELECT * FROM products WHERE id=?";
			pst=this.connection.prepareStatement(query);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				row =new Products();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public double totalPriceOfCart(ArrayList<Cart> cart_list) {
		double sum=0;
		
		try {
			if(cart_list.size()>0) {
				for(Cart item:cart_list) {
					query="SELECT * FROM products WHERE id=?";
					pst=this.connection.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs=pst.executeQuery();
					while(rs.next()) {
						sum+=rs.getDouble("price")* item.getQuantity();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return sum;
	}
}
