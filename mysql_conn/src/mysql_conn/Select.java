package mysql_conn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class Select {

	public void selectTable(String table_name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
	
		Class c1 = Class.forName("mysql_conn.GetResult");
		GetResult gr = (GetResult)c1.newInstance();
		
		ResultSet rs = gr.getResult(table_name);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		 while(rs.next()) {
			for (int i = 1; i <=rsmd.getColumnCount(); i++) {
				
				int scale = rsmd.getScale(i); 
				int columnType = rsmd.getColumnType(i);
				
				if (columnType == Types.INTEGER || columnType == Types.BIGINT&& scale == 0) { 
					System.out.printf("%d\t",rs.getInt(i));
	
				}else if(columnType == Types.INTEGER|| columnType == Types.BIGINT && scale != 0) {
					System.out.printf("%.2f\t", rs.getDouble(	i));
	
				}else if(columnType == Types.VARCHAR|| columnType == Types.CLOB) {
					System.out.printf("%s\t", rs.getString(i));
				}
			}
			
			System.out.println();
		}
	}

}
