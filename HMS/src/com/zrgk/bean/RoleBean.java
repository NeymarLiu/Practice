package com.zrgk.bean;

import java.io.Serializable;

/**
 * 用户基础类
 * */
public class RoleBean implements Serializable {
	private int r_id;
	private String r_name;
	private int r_state;

	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public int getR_state() {
		return r_state;
	}
	public void setR_state(int r_state) {
		this.r_state = r_state;
	}
	public RoleBean(int r_id, String r_name, int r_state) {
		super();
		this.r_id = r_id;
		this.r_name = r_name;
		this.r_state = r_state;
	}
	public RoleBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
