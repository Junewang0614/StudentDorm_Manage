package com.june.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.june.model.Room;


//房间信息与数据库操作

public class RoomDao extends BaseDao {

	//这个是负责整体查询的
	//不同的查询有不同的分类
	//0.没满。1.满了。2.未选择
	public List<Room> getRoomList(String room_id,int state,String major){
		List<Room> roomList = null;
		
		//2.指定房间号有
		if(!room_id.equals(""))
		{
			roomList = SelRoom_id(room_id);
		}
		//3.指定专业+状态有
		else if(state != 2 || !major.equals("")) {
			
			String flag = "";
			if(state == 1)
				flag = "1";
			else if(state == 0)
				flag = "0";
			//System.out.println(state+",,,"+major);
			roomList = SelMajor(flag,major);
		}	
		
		//1.查询所有房间有，条件感觉得改改
		else
		{
			//System.out.println("hah");
			roomList = allRoom();
		}
		return roomList;
	}
	
	//3.专业与状态组合查询
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

	//2.查询指定房间
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

	//1.如果没有设置条件，查询所有的房间
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
