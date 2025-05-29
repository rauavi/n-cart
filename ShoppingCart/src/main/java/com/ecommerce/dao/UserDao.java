package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ecommerce.model.Users;

public class UserDao {

	private Connection connection;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public Users userlogin(String email, String password) {
		
		Users user=null;
		try {
			query="SELECT * FROM users WHERE email=? and password=?";
			pst=this.connection.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				user =new Users();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
