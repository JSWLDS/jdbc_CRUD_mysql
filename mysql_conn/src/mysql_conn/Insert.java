package mysql_conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Insert {
	public void insertValues(String table_name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		Scanner sc = new Scanner(System.in);
		Class c1 = Class.forName("mysql_conn.GetResult");
		GetResult gr = (GetResult)c1.newInstance();
		Connection conn = gr.returnConn();
		
		ResultSet rs = gr.getResult(table_name);
		ResultSetMetaData rsmd = rs.getMetaData(); //rs의 정보를 얻어오는 메소드
		
		int total = gr.getColumnCnt(table_name);
		String sql = "INSERT INTO " + table_name + " VALUES (NULL, ";
		for(int i=0; i<total-1; i++) {
			sql += "?, ";
		}
		sql = sql.substring(0, sql.length()-2);
		sql += ")";

	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    
	    System.out.println();
		System.out.println(table_name + "에 값을 추가합니다.");
		System.out.println();

		int cnt = 1;
		for (int i = 2; i <=total; i++) {
		
			int scale = rsmd.getScale(i); 
			int columnType = rsmd.getColumnType(i);
			
			System.out.print(rsmd.getColumnName(i)+ "의 값을 입력하세요 : ");
			
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
		pstmt.executeUpdate();
		System.out.println("sucessful");
	}	
}
