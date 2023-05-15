package mysql_conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.*;

public class Delete {
	public void deleteColumn (String table_name, int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Scanner sc = new Scanner(System.in);
		Class c1 = Class.forName("mysql_conn.GetResult");
		GetResult gr = (GetResult)c1.newInstance();
		Connection conn = gr.returnConn();
		
		String sql = "DELETE FROM " + table_name + " WHERE id = " + id;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.executeUpdate();
		System.out.println("sucessful");
	}
}
