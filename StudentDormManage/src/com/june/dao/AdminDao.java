package com.june.dao;

import com.june.model.Admin;
import com.june.model.Student;

import java.sql.CallableStatement;
//import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//根据管理员表，进行管理员操作功能
public class AdminDao extends BaseDao {
	//登录功能
	public Admin login(Admin admin) {
		String sql = "select * from ADMIN where Admin_ID = ? and Admin_pass = ?";
		Admin adminRst = null;
		try {//传sql语句
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, admin.getName());
			prst.setString(2, admin.getPassword());
			//执行sql
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next())//找到了
			{
				adminRst = new Admin();
				//adminRst.setId(executeQuery.getString("id"));
				adminRst.setName(executeQuery.getString("Admin_ID"));
				adminRst.setPassword(executeQuery.getString("Admin_pass"));
				//adminRst.setCreateDate(executeQuery.getString("createDate"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//closeDao();
		return adminRst;
	}
	
	//1、判断学号是否在登录表中
	public boolean StuExists(String stuId) {
		String sql = "select * from STUDENT_LOGIN where Stu_ID = ?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1,stuId);
			ResultSet execute = prst.executeQuery();
			if(execute.next()) {
				//存在
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	//2、判断是否已经入住，看信息表有没有学生记录
	public boolean StuLiveIN(String stuId) {
		String sql = "select * from STUDENT where Stu_ID = ?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1,stuId);
			ResultSet execute = prst.executeQuery();
			if(execute.next()) {
				//存在
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//判断房间是否存在,是否已满
	public int RoomExists(String roomId) {
		String sql = "select * from DORMITORY where Dom_ID = ?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1,roomId);
			ResultSet execute = prst.executeQuery();
			if(execute.next()) {
				//存在
				int tmp = execute.getInt("state");
				if(tmp == 1)
					return 2;
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
	

	//4、判断该床位号是否有人
	public boolean Bedused(String roomId,String bed) {
		String sql = "select * from STUDENT where Dom_ID = ? and Bed_ID = ?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1,roomId);
			prst.setString(2, bed);
			ResultSet execute = prst.executeQuery();
			if(execute.next()) {
				//有人了
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	//5、插入
	public int AddStu(Student stu){ 
		//int包括返回的错误类型，正确插入返回0
		if(StuExists(stu.getID()) == false) {
			return 1;//没找到学生返回1
		}
		if(StuLiveIN(stu.getID()) == true) {
			//学生已经入住了
			return 2;
		}
		if(RoomExists(stu.getDom_ID()) == 1) {
			//房间不存在
			return 3;
		}
		if(RoomExists(stu.getDom_ID()) == 2) {
			//房间已满
			return 4;
		}
		//床位不存在
		if(Integer.parseInt(stu.getBed()) >= 5 || Integer.parseInt(stu.getBed()) <= 0) {
			return 6;
		}
		if(Bedused(stu.getDom_ID(), stu.getBed()) == true)
		{
			//床位有人
			return 5;
		}
		
		if(stu.getPhone().equals("")) //调用2
		{
			//System.out.println(stu.getPhone().equals(""));
			String sql = "call insert_stu2(?,?,?,?,?);";
			CallableStatement cs = null;
			try {
				cs = con.prepareCall(sql);
				cs.setString(1,stu.getID());
				cs.setString(2,stu.getName());
				cs.setString(3,stu.getDom_ID());
				cs.setString(4,stu.getBed());
				cs.setString(5,stu.getMajor());
				
				if(cs.executeUpdate() > 0) {
					return 0;
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else { // 调用1
			String sql = "call insert_stu1(?,?,?,?,?,?)";
			CallableStatement cs = null;
			try {
				//cs = con.prepareCall(sql);
				cs = con.prepareCall(sql);
				cs.setString(1,stu.getID());
				cs.setString(2,stu.getName());
				cs.setString(3,stu.getDom_ID());
				cs.setString(4,stu.getBed());
				cs.setString(5, stu.getPhone());
				cs.setString(6,stu.getMajor());
				
				if(cs.executeUpdate()>0) {
					return 0;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return -1;
	}
	
	
	public int DelStu(String sid,String rid) {
		if(StuExists(sid) == false)
			return 1;//没找到学生
		if(RoomExists(rid) == 1)
			return 2;//房间不存在
		
		//判断房间和学号是否匹配防止误删
		String sql = "select * from STUDENT where Stu_ID = ? and Dom_ID = ?";
		try {
			PreparedStatement prs = con.prepareStatement(sql);
			prs.setString(1,sid);
			prs.setString(2,rid);
			ResultSet rs = prs.executeQuery();
			if(!rs.next()) //没查到，可能有错
				return 3;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//没有问题了执行语句
		String sql2 = "call delete_stu(?)";
		try {
			CallableStatement cs = con.prepareCall(sql2);
			cs.setString(1, sid);
			if(cs.executeUpdate() > 0) {
				return 0;//成功
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1; //失败
	}
	
	public int ChaStu(Student stu,String old_room) {
		//传入的是新调整的信息
		if(StuExists(stu.getID()) == false) {
			return 1;//没找到学生
		}
		if(RoomExists(old_room) == 1 || RoomExists(stu.getDom_ID()) == 1)
			return 2;//新房间或者旧房间不存在
		if(RoomExists(stu.getDom_ID()) == 2)
			return 3;//新房间已经满了
		if(Bedused(stu.getDom_ID(), stu.getBed()) == true)
			return 4;//床位有人
		
		String sql = "call change_dom(?,?,?,?)";
		try {
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, stu.getID());
			cs.setString(2, old_room);
			cs.setString(3, stu.getDom_ID());
			cs.setString(4, stu.getBed());
			
			if(cs.executeUpdate()>0) {
				return 0;//成功
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;//失败
	}
}
