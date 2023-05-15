package mysql_conn;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Scanner sc = new Scanner(System.in);
		GetResult gr = new GetResult();
		Select select = new Select();
		Insert insert = new Insert();
		Update update = new Update();
		Delete delete = new Delete();
		
		while(true) {
			System.out.println("작업을 선택하세요. ( 1. 조회 2. 추가 3. 업데이트 4. 삭제 5. 종료 )");
			
			int num = sc.nextInt();
			String menu;
			
			switch (num) {
			case 1: menu = "조회"; break; 
			case 2: menu = "추가"; break; 
			case 3: menu = "변경"; break; 
			case 4: menu = "삭제"; break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
			
			if(num == 5) {
				System.out.println("종료되었습니다.");
				break;
			}
			gr.printTables();
			System.out.print("(" + menu + "입니다)테이블을 선택하세요 : ");
			String table_name = sc.next();
			
			try {
				if(num == 1) {

					select.selectTable(table_name);
				} else if(num == 2) {
					
					insert.insertValues(table_name);
				}else if(num == 3) {

					System.out.print("변경할 컬럼의 id를 입력하세요 : ");
					int id = sc.nextInt();
					update.updateColumn(table_name, id);
				}
				else if(num == 4) {

					System.out.print("삭제할 컬럼의 id를 입력하세요 : ");
					int id = sc.nextInt();
					delete.deleteColumn(table_name, id);
				}
			}catch (Exception e) {
				new Exception();
			}
		}
	}

}
