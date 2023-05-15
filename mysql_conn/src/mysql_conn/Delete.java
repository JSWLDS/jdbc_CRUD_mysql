package mysql_conn;

import java.sql.Connection;
import java.util.Scanner;

public class Delete {
	public void deleteColumn (String table_name, int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		Class c1 = Class.forName("mysql_conn.GetResult");
		GetResult gr = (GetResult)c1.newInstance();
		Connection conn = gr.returnConn();
		
		
		
	}
}
