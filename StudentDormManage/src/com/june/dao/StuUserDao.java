package com.june.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.june.model.StuUser;

public class StuUserDao extends BaseDao {
	//Ñ§ÉúµÇÂ¼
	public StuUser login(StuUser stu) {
		String sql = "select * from student_login where Stu_ID = ? and Stu_pass = ?";
		StuUser sturesult = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, stu.getName());
			prst.setString(2, stu.getPassword());
			
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next()) {
				sturesult = new StuUser();
				sturesult.setName(executeQuery.getString("Stu_ID"));
				sturesult.setPassword(executeQuery.getString("Stu_pass"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//closeDao();
		return sturesult;
	}
	
}
