package until;

import java.sql.*;

public class DBUtils {
	static {
		try {
			// 向DriverManager注册驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static synchronized Connection getConnection() throws SQLException {
		Connection conn = DriverManager
				.getConnection("jdbc:mysql://localhost/db_dorm?user=root&password=123456&serverTimezone=UTC");
//		Connection conn = DriverManager
//				.getConnection("jdbc:mysql://localhost:3307/db_dorm?user=root&password=123456");

		return conn;
	}

	public static void close(Statement stat, Connection conn)
			throws SQLException {
		if (stat != null && !stat.isClosed()) {
			stat.close();
		}
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	public static void close(ResultSet rst, Statement stat,
							 Connection conn) throws SQLException {
		if (rst != null && !rst.isClosed()) {
			rst.close();
		}
		close(stat,conn);
	}

}
