package mysql_conn;

import java.sql.*;


public class Util {
	public Connection getConnection() {

		String url = "jdbc:mysql://localhost:3306/test";
		String userName = "myjsw";
		String password = "1234"; 

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, userName, password);
		
			return conn;
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	
	}
}
