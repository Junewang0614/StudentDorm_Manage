package com.june.model;

public enum userType {
	STUDENT("Ñ§Éú",0), ADMIN("ËÞ¹Ü",1);
	private String name;
	private int index;
	private userType(String name,int index) {
		this.name = name;
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
