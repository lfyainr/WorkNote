package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBCtest {

	static String CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	static String URL = "jdbc:oracle:thin:@192.168.1.1:1521:orcl";
	static String USER = "root";
	static String PWD = "1234";

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;//结果集
		ResultSetMetaData rsmd = null;// 获取 列信息
		String sql = "select * from dual";
		try {
			Class.forName(CLASS_NAME);
			conn = DriverManager.getConnection(URL, USER, PWD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();

			int columns = rsmd.getColumnCount();//数据量
			// 显示列,表格的表头
			for (int i = 1; i <= columns; i++) {
				System.out.print(rsmd.getColumnName(i));
				System.out.print("\t\t");
			}

			System.out.println();
			// 显示表格内容
			while (rs.next()) {
				for (int i = 1; i <= columns; i++) {
					System.out.print(rs.getString(i));
					System.out.print("\t\t");
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
