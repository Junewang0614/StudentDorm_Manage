package com.june.dao;
/*���ݿ����Ӷ���*/

import java.sql.Connection;
import java.sql.SQLException;

import com.june.util.DbUtil;

public class BaseDao {
	public Connection con = new DbUtil().getCon();
	public void closeDao() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
