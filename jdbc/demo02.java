package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试执行SQL语句，以及SQL注入问题
 * @author Administrator
 *
 */
public class demo02 {
	public static void main(String[] args) {
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
				Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamuisdatabase","root","123456");

				//注：一般实际开发中不会使用Statment
				Statement stmt = cn.createStatement();
				//设置变量
				String name = "Subaru";
				String password = "sub123";
				int age = 17 ;
				String sex = "男";
				String birthday = "1994-12-12";
				
				//编写数据库执行语句
				//插入语句
//				String sql = "INSERT INTO user(name,password,age,sex,birthday)"
//						+ " VALUES ('"+ name +"','"+ password +"',"+ age +
//						",'"+ sex +"','"+ birthday +"') ";
				
				//测试SQL注入（测试SQL漏洞的恶意语句）
				String id = "5 or 1=1";
				String sql = "DELETE from user where id=" + id ;
				
				//执行数据库语句
				stmt.execute(sql);
				System.out.println("数据语句执行完毕！");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
	}
}
