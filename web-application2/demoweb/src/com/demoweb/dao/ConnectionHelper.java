package com.demoweb.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionHelper {
	public static Connection getConnection(String dbName) { //dsn = data source name ->dbName
		Connection conn = null;
		if(dbName.equals("oracle")) {
			try {
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env"); //찾아보는곳
				DataSource ds = (DataSource)envContext.lookup("jdbc/demoweb");
				conn = ds.getConnection();
			}catch(Exception ex) {
				return conn;
			}
		}
		return conn;
	}
}
