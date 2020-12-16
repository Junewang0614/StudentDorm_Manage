package com.june.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DbUtil {
	private String dburl = "jdbc:mysql://localhost:3306/dormitory?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
	private String dbUserName = "aaa";
	private String dbPassword = "0000";
	private String jdbcName = "com.mysql.cj.jdbc.Driver";
	
	//链接
	public Connection getCon(){
		try {
			Class.forName(jdbcName);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl,dbUserName,dbPassword);
		}
		catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	//关闭连接
	public void closeCon(Connection con) throws Exception{
		if(con != null) {
			con.close();
		}
	}
	
	
	public static void main(String args[]) {
		DbUtil dbUtil = new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功!");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("连接失败");
		}
	}
	
}
