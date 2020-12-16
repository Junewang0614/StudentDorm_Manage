package com.june.dao;

import com.june.model.Admin;
import com.june.model.Student;

import java.sql.CallableStatement;
//import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//���ݹ���Ա�����й���Ա��������
public class AdminDao extends BaseDao {
	//��¼����
	public Admin login(Admin admin) {
		String sql = "select * from ADMIN where Admin_ID = ? and Admin_pass = ?";
		Admin adminRst = null;
		try {//��sql���
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, admin.getName());
			prst.setString(2, admin.getPassword());
			//ִ��sql
			ResultSet executeQuery = prst.executeQuery();
			if(executeQuery.next())//�ҵ���
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
	
	//1���ж�ѧ���Ƿ��ڵ�¼����
	public boolean StuExists(String stuId) {
		String sql = "select * from STUDENT_LOGIN where Stu_ID = ?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1,stuId);
			ResultSet execute = prst.executeQuery();
			if(execute.next()) {
				//����
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	//2���ж��Ƿ��Ѿ���ס������Ϣ����û��ѧ����¼
	public boolean StuLiveIN(String stuId) {
		String sql = "select * from STUDENT where Stu_ID = ?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1,stuId);
			ResultSet execute = prst.executeQuery();
			if(execute.next()) {
				//����
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//�жϷ����Ƿ����,�Ƿ�����
	public int RoomExists(String roomId) {
		String sql = "select * from DORMITORY where Dom_ID = ?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1,roomId);
			ResultSet execute = prst.executeQuery();
			if(execute.next()) {
				//����
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
	

	//4���жϸô�λ���Ƿ�����
	public boolean Bedused(String roomId,String bed) {
		String sql = "select * from STUDENT where Dom_ID = ? and Bed_ID = ?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1,roomId);
			prst.setString(2, bed);
			ResultSet execute = prst.executeQuery();
			if(execute.next()) {
				//������
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	//5������
	public int AddStu(Student stu){ 
		//int�������صĴ������ͣ���ȷ���뷵��0
		if(StuExists(stu.getID()) == false) {
			return 1;//û�ҵ�ѧ������1
		}
		if(StuLiveIN(stu.getID()) == true) {
			//ѧ���Ѿ���ס��
			return 2;
		}
		if(RoomExists(stu.getDom_ID()) == 1) {
			//���䲻����
			return 3;
		}
		if(RoomExists(stu.getDom_ID()) == 2) {
			//��������
			return 4;
		}
		//��λ������
		if(Integer.parseInt(stu.getBed()) >= 5 || Integer.parseInt(stu.getBed()) <= 0) {
			return 6;
		}
		if(Bedused(stu.getDom_ID(), stu.getBed()) == true)
		{
			//��λ����
			return 5;
		}
		
		if(stu.getPhone().equals("")) //����2
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
		
		else { // ����1
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
			return 1;//û�ҵ�ѧ��
		if(RoomExists(rid) == 1)
			return 2;//���䲻����
		
		//�жϷ����ѧ���Ƿ�ƥ���ֹ��ɾ
		String sql = "select * from STUDENT where Stu_ID = ? and Dom_ID = ?";
		try {
			PreparedStatement prs = con.prepareStatement(sql);
			prs.setString(1,sid);
			prs.setString(2,rid);
			ResultSet rs = prs.executeQuery();
			if(!rs.next()) //û�鵽�������д�
				return 3;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//û��������ִ�����
		String sql2 = "call delete_stu(?)";
		try {
			CallableStatement cs = con.prepareCall(sql2);
			cs.setString(1, sid);
			if(cs.executeUpdate() > 0) {
				return 0;//�ɹ�
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1; //ʧ��
	}
	
	public int ChaStu(Student stu,String old_room) {
		//��������µ�������Ϣ
		if(StuExists(stu.getID()) == false) {
			return 1;//û�ҵ�ѧ��
		}
		if(RoomExists(old_room) == 1 || RoomExists(stu.getDom_ID()) == 1)
			return 2;//�·�����߾ɷ��䲻����
		if(RoomExists(stu.getDom_ID()) == 2)
			return 3;//�·����Ѿ�����
		if(Bedused(stu.getDom_ID(), stu.getBed()) == true)
			return 4;//��λ����
		
		String sql = "call change_dom(?,?,?,?)";
		try {
			CallableStatement cs = con.prepareCall(sql);
			cs.setString(1, stu.getID());
			cs.setString(2, old_room);
			cs.setString(3, stu.getDom_ID());
			cs.setString(4, stu.getBed());
			
			if(cs.executeUpdate()>0) {
				return 0;//�ɹ�
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;//ʧ��
	}
}
