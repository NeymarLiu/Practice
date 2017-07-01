package com.zrgk.bean;

import java.io.Serializable;

/**
 * user基础类
 */
public class UserBean implements Serializable {
	private int u_id;
	private String u_loginname;
	private String u_password;
	private String u_truename;
	private String u_idcard;
	private int u_sex;
	private String u_email;
	private String u_phone;
	private String u_address;
	private String u_createtime;
	private int u_state;
	private int u_role;
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getU_loginname() {
		return u_loginname;
	}
	public void setU_loginname(String u_loginname) {
		this.u_loginname = u_loginname;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	public String getU_truename() {
		return u_truename;
	}
	public void setU_truename(String u_truename) {
		this.u_truename = u_truename;
	}
	public String getU_idcard() {
		return u_idcard;
	}
	public void setU_idcard(String u_idcard) {
		this.u_idcard = u_idcard;
	}
	public int getU_sex() {
		return u_sex;
	}
	public void setU_sex(int u_sex) {
		this.u_sex = u_sex;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public String getU_address() {
		return u_address;
	}
	public void setU_address(String u_address) {
		this.u_address = u_address;
	}
	public String getU_createtime() {
		return u_createtime;
	}
	public void setU_createtime(String u_createtime) {
		this.u_createtime = u_createtime;
	}
	public int getU_state() {
		return u_state;
	}
	public void setU_state(int u_state) {
		this.u_state = u_state;
	}
	public int getU_role() {
		return u_role;
	}
	public void setU_role(int u_role) {
		this.u_role = u_role;
	}
	public UserBean(int u_id, String u_loginname, String u_password,
			String u_truename, String u_idcard, int u_sex, String u_email,
			String u_phone, String u_address, String u_createtime, int u_state,
			int u_role) {
		super();
		this.u_id = u_id;
		this.u_loginname = u_loginname;
		this.u_password = u_password;
		this.u_truename = u_truename;
		this.u_idcard = u_idcard;
		this.u_sex = u_sex;
		this.u_email = u_email;
		this.u_phone = u_phone;
		this.u_address = u_address;
		this.u_createtime = u_createtime;
		this.u_state = u_state;
		this.u_role = u_role;
	}
	//登陆实现后删除！
	
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserBean(int u_id, int u_state, int u_role,String u_loginname) {
		super();
		this.u_id = u_id;
		this.u_state = u_state;
		this.u_role = u_role;
		this.u_loginname = u_loginname;
	}
	@Override
	public String toString() {
		return "UserBean [u_id=" + u_id + ", u_loginname=" + u_loginname
				+ ", u_state=" + u_state + ", u_role=" + u_role + "]";
	}
	

}
