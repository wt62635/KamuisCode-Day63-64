package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * 测试PrepareStatement的基本用法
 * @author Administrator
 *
 */
public class demo03 {
	public static void main(String[] args) {
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamuisdatabase","root","123456");
			System.out.println("数据库已连接...");
			
			String sql = "INSERT into user(name,password,age,birthday) values (?,?,?,?)";//?←占位符,可防止sql注入
			PreparedStatement ps = cn.prepareStatement(sql);
			//使用setObject可以省略掉指定数据类型的需要，通过参数可以自动识别对应数据类型。
			ps.setObject(1, "Kamui");//参数索引是从1开始计算，并非从0开始计算
			ps.setObject(2, "kam123");
			ps.setObject(3, 25);
	//		ps.setDate(4, new Date(System.currentTimeMillis()));
			ps.setObject(4, new Date(System.currentTimeMillis()));
			
			System.out.println("开始执行插入指令...");
	//		ps.execute();
			int count = ps.executeUpdate();
			System.out.println("指令执行完毕," + count);
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
	}
}
