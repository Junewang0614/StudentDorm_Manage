package com.june.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.june.model.Student;

public class StudentDao extends BaseDao {
	public Student stuInfoSelect(String stuid) {
		String sql = "select * from STUDENT where Stu_ID = ?";
		Student stu = null;
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1,stuid);
			ResultSet executeQuery = prst.executeQuery();
			
			if(executeQuery.next()) {
				stu = new Student();
				stu.setID(stuid);
				stu.setName(executeQuery.getString("Stu_name"));
				stu.setDom_ID(executeQuery.getString("Dom_ID"));
				stu.setBed(executeQuery.getString("Bed_ID"));
				stu.setPhone(executeQuery.getString("Phone"));
				stu.setMajor(executeQuery.getString("Major"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stu;
	}
}
