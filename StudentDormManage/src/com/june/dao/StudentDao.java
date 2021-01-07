package com.june.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Student> getStuList(String stuid){
		String sql = "select * from student where student.dom_id = " +
					"(select dom_id from student where stu_id = ?)";
		List<Student> stuList = new ArrayList<Student>();
		
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, stuid);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setID(rs.getString("Stu_ID"));
				s.setName(rs.getString("Stu_name"));
				s.setDom_ID(rs.getString("Dom_ID"));
				s.setBed(rs.getString("Bed_ID"));
				s.setPhone(rs.getString("Phone"));
				s.setMajor(rs.getString("Major"));
				stuList.add(s);
			}
			if(stuList.size() == 0) {
				stuList = null;
			}
			return stuList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
	}
}
