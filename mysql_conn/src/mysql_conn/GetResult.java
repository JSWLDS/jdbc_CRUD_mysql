package mysql_conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class GetResult {
	Util util = new Util();
	Connection conn = util.getConnection();
	public ResultSet getResult(String table_name) throws SQLException {
		
		String sql = "select * from " + table_name;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		return rs;
	}
	public int getColumnCnt(String table_name) throws SQLException {

		String sql = "select * from " + table_name;
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCnt = rsmd.getColumnCount();
		return columnCnt;
	}
	
	public Connection returnConn() {
		return conn;
	}
	public String getDatabase() throws SQLException {
		
		String sql = "select database()";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		String database = rs.getString(1);
		return database;
	}
	public void printTables() throws SQLException {

		String sql = "show tables";
		String database = this.getDatabase();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("==================================");
		System.out.println("현재 데이터베이스 " + database + "의 테이블은 다음과 같습니다.");
		System.out.println("==================================");
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
		System.out.println("==================================");
	}
}
