package com.june.model;

//数据库实体表
public class Admin {
	//private int id;
	private String name;
	private String password;
	//private String createDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
