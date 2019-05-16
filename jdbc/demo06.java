package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试事务
 * @author Administrator
 *
 */
public class demo06 {
	public static void main(String[] args) {
		Connection cn = null;
		PreparedStatement ps1 = null ;
		PreparedStatement ps2 = null ;
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamuisdatabase","root","123456");
			System.out.println("数据库已连接...");
			
			cn.setAutoCommit(true);//JDBC中默认是true自动提交
			
			ps1 = cn.prepareStatement("insert into user (name,password,age) values (?,?,?)");
			ps1.setObject(1, "kamui");
			ps1.setObject(2, "kam123");
			ps1.setObject(3, 25);
			ps1.execute();
			System.out.println("ps1:插入一个用户。");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			ps2 = cn.prepareStatement("insert into user (name,password) values (?,?,?)");
			ps2.setObject(1, "kamui");
			ps2.setObject(2, "kam123");
			ps2.execute();
			System.out.println("ps2:插入一个用户。");
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				try {
					cn.rollback();//回滚操作
				} catch (Exception e2) {
				}
		}finally {//按照后开先关的顺序，关闭数据库
			//注意一定要将异常捕获分开写，否则会导致抛出异常的时候其他链接无法没有执行关闭方法
			if(ps1!=null) {
				try {
					ps1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps2!=null) {
				try {
					ps1.close();
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
