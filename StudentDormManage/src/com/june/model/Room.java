package com.june.model;
//������Ϣ

public class Room {
	private String id;//�����
	private boolean roomstate;//����״̬��trueΪ��Ա
	private int num;//������Ա����
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
		System.out.println("����ţ�"+id);
		System.out.println("����������"+num);
		System.out.println("����״̬��"+roomstate);
	}
}
