package mysql_conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Update {
	public void updateColumn(String table_name, int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		Scanner sc = new Scanner(System.in);
		Class c1 = Class.forName("mysql_conn.GetResult");
		GetResult gr = (GetResult)c1.newInstance();
		Connection conn = gr.returnConn();
		ResultSet rs = gr.getResult(table_name);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int total = gr.getColumnCnt(table_name);
		String sql = "UPDATE " + table_name + " SET ";

		
		for(int i=2; i<=total; i++) {
			String column_name = rsmd.getColumnName(i);
			sql += (column_name + " = ?, ");
		}
		sql = sql.substring(0, sql.length()-2);
		sql += " WHERE id = " + id;

	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    
	    System.out.println();
		System.out.println(table_name + "테이블의 " + id + "번째 값을 변경합니다.");
		System.out.println();

		int cnt = 1;
		for (int i = 2; i <=total; i++) {
		
			int scale = rsmd.getScale(i); 
			int columnType = rsmd.getColumnType(i);
			String column_name = rsmd.getColumnName(i);
			System.out.print(column_name + "의 값을 입력하세요 : ");
			 
			if (columnType == Types.INTEGER || columnType == Types.BIGINT&& scale == 0) {
	
				int number = sc.nextInt();
				pstmt.setInt(cnt++, number);

			}else if(columnType == Types.INTEGER || columnType == Types.BIGINT&& scale != 0) {
			
				double clob =  sc.nextDouble();
				pstmt.setDouble(cnt++, clob);
			 
			}else if(columnType == Types.VARCHAR|| columnType == Types.CLOB) {
				
				String string =  sc.next();
				pstmt.setString(cnt++, string);
				
			}
		}
//	    System.out.println(sql);
		pstmt.executeUpdate();
		System.out.println("sucessful");
		
		
		
	}
}
