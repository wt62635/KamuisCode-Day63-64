package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 测试ResultStatement的基本用法
 * @author Administrator
 *
 */
public class demo04 {
	public static void main(String[] args) {
		Connection cn = null;
		PreparedStatement ps = null ;
		ResultSet rs = null ;
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamuisdatabase","root","123456");
			System.out.println("数据库已连接...");
			
			//查询
			String sql = "select id,name,age from user where id>?";
			ps = cn.prepareStatement(sql);
			ps.setObject(1, 5);//把id大于5的数据取出
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1) + "----" + rs.getString(2) + "----" + rs.getInt(3));
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}finally {//按照后开先关的顺序，关闭数据库
			//注意一定要将异常捕获分开写，否则会导致抛出异常的时候其他链接无法没有执行关闭方法
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(cn!=null) {
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
