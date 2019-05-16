package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试批处理的基本用法
 * @author Administrator
 *
 */
public class demo05 {
	public static void main(String[] args) {
		Connection cn = null;
		Statement sm = null ;
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kamuisdatabase","root","123456");
			System.out.println("数据库已连接...");
			
			cn.setAutoCommit(false);//设为手动提交
			
			long start = System.currentTimeMillis();
			sm = cn.createStatement();
			
			//生成2w条数据
			for (int i = 0; i < 20000 ; i++) {
				sm.addBatch("insert into user (name,password,age,sex,birthday) values ('Kamui"+ i +"','kam123',"+ i +",'男',now())");
			}
			
			//执行批处理
			sm.executeBatch();
			cn.commit();//提交事务
			long end = System.currentTimeMillis();
			
			System.out.println("插入20000条数据：耗时(ms)" + (end-start));
			//输出结果：插入20000条数据：耗时(ms)1618
			
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
		}finally {//按照后开先关的顺序，关闭数据库
			//注意一定要将异常捕获分开写，否则会导致抛出异常的时候其他链接无法没有执行关闭方法
			if(sm!=null) {
				try {
					sm.close();
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
