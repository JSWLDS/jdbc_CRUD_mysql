package mysql_conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reset {
	
	public void idReset(String table_name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		Class c1 = Class.forName("mysql_conn.GetResult");
		GetResult gr = (GetResult)c1.newInstance();
		Connection conn = gr.returnConn();
		
		String sql_1 = "ALTER TABLE `" + table_name + "` CHANGE COLUMN `id` `id` BIGINT NOT NULL";
		String sql_2 = "SET @count = 0";
		String sql_3 = "UPDATE " + table_name +" SET id = @count := @count + 1";
		String sql_4 = "ALTER TABLE `" + table_name + "` CHANGE COLUMN `id` `id` BIGINT NOT NULL AUTO_INCREMENT";
		

		PreparedStatement pstmt = null;
		
		pstmt = conn.prepareStatement(sql_1);
		pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql_2);
		pstmt.executeUpdate();
		
		pstmt = conn.prepareStatement(sql_3);
		pstmt.executeUpdate();

		pstmt = conn.prepareStatement(sql_4);
		pstmt.executeUpdate();
		

		System.out.println("sucessful");
		
	}
	
	public void idCountReset(String table_name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		Class c1 = Class.forName("mysql_conn.GetResult");
		GetResult gr = (GetResult)c1.newInstance();
		Connection conn = gr.returnConn();
		
		String sql = "SET @count = (select max(id) from " + table_name + ") + 1";

		PreparedStatement pstmt =  conn.prepareStatement(sql);
		
	}
	
}
