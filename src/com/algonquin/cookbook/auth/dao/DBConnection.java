package com.algonquin.cookbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnectionToDatabase() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/loggy";
			connection = DriverManager.getConnection(url, "armika", "Jopa11.11");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("Connection succesfull!");
		}
		return connection;
	}
}
