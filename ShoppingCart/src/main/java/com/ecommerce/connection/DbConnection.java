package com.ecommerce.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static Connection connection=null;
	
	public static Connection getconnection() throws ClassNotFoundException, SQLException{
		if(connection==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/ncart";
			String user="root";
			String password="root";
			
			connection=DriverManager.getConnection(url,user,password);
		}
		return connection;
	}
}
