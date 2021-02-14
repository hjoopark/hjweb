package com.ajaxweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionHelper {
	
	public static Connection getConnection(String dbname) {
		
		if (dbname.toLowerCase().equals("oracle")) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");				
				Connection conn = 
					DriverManager.getConnection(
					"jdbc:oracle:thin:@172.16.6.16:1521:xe", 
					"demoweb", "oracle");
				
				return conn;
			} catch (Exception ex) {
				return null;
			}
		} else if (dbname.toLowerCase().equals("mysql")) {
			try {
				//1.
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = 
					DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/demoweb", "root", "");
				
//				Context initContext = new InitialContext();
//				Context envContext  = (Context)initContext.lookup("java:/comp/env");
//				DataSource ds = (DataSource)envContext.lookup("jdbc/demoweb");
//				Connection conn = ds.getConnection();
				
				return conn;
			} catch (Exception ex) {
				return null;
			}
		} else {
			return null;
		}
		
	}

}
