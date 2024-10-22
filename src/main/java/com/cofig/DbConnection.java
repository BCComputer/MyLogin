package com.cofig;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public static Connection getConnection() {
		Connection con = null;
		try {
			final String DB_URL = "jdbc:mysql://localhost:3306/login";
			final String DB_USERNAME = "root";
			final String DB_PASSWORD = "1234";
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD); // create a connection to a database
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
