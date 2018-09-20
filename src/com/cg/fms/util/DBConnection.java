package com.cg.fms.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	
	public static Connection getInstance() {
		Properties properties = new Properties();
	    Connection connection = null;
	    
		FileInputStream inputStream; 
		
			if(connection==null) 
				
			try {
				inputStream = new FileInputStream("resources/dbcredentials.properties");
				properties.load(inputStream);
				String url = "jdbc:oracle:thin:@localhost:1521:xe";//properties.getProperty("url");
				String user = "system";//properties.getProperty("user");
				String password = "root";//properties.getProperty("password");
				inputStream.close();
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				connection = DriverManager.getConnection(url, user, password);
			} catch (FileNotFoundException e1) {
				System.err.println("File not found" + e1.getMessage());
			} catch (IOException e) {
				System.err.println("Error in fetching data" + e.getMessage());
		} catch (SQLException e) {
			System.err.println("SQL Exception occured" + e.getMessage());
		}
			return connection;
}
}