package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 测试与数据库建立连接
 * (需要导入jar包)
 * @author Administrator
 *
 */
public class demo01 {
	public static void main(String[] args) {
		//加载驱动类
		try {
			Class.forName("com.mysql.jdbc.Driver");
			/*
			 * 建立连接（连接对象内部其实包含了Socket对象，是一个远程的连接。
			 * 特点是比较耗时！这是Connection对象管理的一个重点！）
			 * 真正开发中，为了提高效率，都会使用连接池来管理连接对象！
			 */
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamuisdatabase", "root", "123456");
			System.out.println(cn);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
