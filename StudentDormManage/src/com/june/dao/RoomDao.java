package com.june.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.june.model.Room;


//������Ϣ�����ݿ����

public class RoomDao extends BaseDao {

	//����Ǹ��������ѯ��
	//��ͬ�Ĳ�ѯ�в�ͬ�ķ���
	//0.û����1.���ˡ�2.δѡ��
	public List<Room> getRoomList(String room_id,int state,String major){
		List<Room> roomList = null;
		
		//2.ָ���������
		if(!room_id.equals(""))
		{
			roomList = SelRoom_id(room_id);
		}
		//3.ָ��רҵ+״̬��
		else if(state != 2 || !major.equals("")) {
			
			String flag = "";
			if(state == 1)
				flag = "1";
			else if(state == 0)
				flag = "0";
			//System.out.println(state+",,,"+major);
			roomList = SelMajor(flag,major);
		}	
		
		//1.��ѯ���з����У������о��øĸ�
		else
		{
			//System.out.println("hah");
			roomList = allRoom();
		}
		return roomList;
	}
	
	//3.רҵ��״̬��ϲ�ѯ
	private List<Room> SelMajor(String flag, String major) {
		// TODO Auto-generated method stub
		List<Room> roomList = new ArrayList<Room>();
		String sql = "call select_dom(?,?)";
		
		//System.out.println(flag+":"+major);
		try {
			CallableStatement cst = con.prepareCall(sql);
			cst.setString(1,flag);
			cst.setString(2,major);
			
			cst.execute();
			ResultSet rs = cst.getResultSet();
			while(rs.next()) {
				Room rm = new Room();
				rm.setId(rs.getString("Dom_ID"));
				rm.setNum(rs.getInt("occupied"));
				String tmp = rs.getString("state");
				if(tmp.equals("1"))
				{
					rm.setRoomstate(true);
				}
				else {
					rm.setRoomstate(false);
				}
				
				roomList.add(rm);
			}
			if(roomList.size() == 0)
				roomList = null;
			return roomList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//2.��ѯָ������
	private List<Room> SelRoom_id(String room_id) {
		// TODO Auto-generated method stub
		List<Room> roomList = new ArrayList<Room>();
		String sql = "select * from dormitory where Dom_id = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, room_id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Room rm = new Room();
				rm.setId(rs.getString("Dom_ID"));
				rm.setNum(rs.getInt("occupied"));
				String tmp = rs.getString("state");
				if(tmp.equals("1"))
				{
					rm.setRoomstate(true);
				}
				else {
					rm.setRoomstate(false);
				}
				
				roomList.add(rm);
			}
			if(roomList.size() == 0)
				roomList = null;
			return roomList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//1.���û��������������ѯ���еķ���
	public List<Room> allRoom(){
		List<Room> roomList = new ArrayList<Room>();
		
		String sql = "select * from dormitory";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Room rm = new Room();
				rm.setId(rs.getString("Dom_ID"));
				rm.setNum(rs.getInt("occupied"));
				String tmp = rs.getString("state");
				if(tmp.equals("1"))
				{
					rm.setRoomstate(true);
				}
				else {
					rm.setRoomstate(false);
				}
				
				roomList.add(rm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(roomList);
		return roomList;
	}
}
