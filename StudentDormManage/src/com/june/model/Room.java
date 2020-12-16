package com.june.model;
//房间信息

public class Room {
	private String id;//房间号
	private boolean roomstate;//房间状态，true为满员
	private int num;//宿舍人员数量
	//private int  
	public String getId() {
		return id;
	}
	public void setId(String string) {
		this.id = string;
	}
	public boolean isRoomstate() {
		return roomstate;
	}
	public void setRoomstate(boolean roomstate) {
		this.roomstate = roomstate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void printinfo() {
		System.out.println("房间号："+id);
		System.out.println("房间人数："+num);
		System.out.println("房间状态："+roomstate);
	}
}
