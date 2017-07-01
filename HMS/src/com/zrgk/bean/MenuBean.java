package com.zrgk.bean;

import java.io.Serializable;

/**
 * Menu基础类
 */
public class MenuBean implements Serializable{
	private int m_id;
	private String m_name;
	private int m_state;
	private String m_url;
	private int flag=0;//返回用户拥有的菜单是使用
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public int getM_state() {
		return m_state;
	}
	public void setM_state(int m_state) {
		this.m_state = m_state;
	}
	public String getM_url() {
		return m_url;
	}
	public void setM_url(String m_url) {
		this.m_url = m_url;
	}
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public MenuBean(int m_id, String m_name, int m_state, String m_url) {
		super();
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_state = m_state;
		this.m_url = m_url;
	}
	
	public MenuBean(int m_id, String m_name, int m_state, String m_url, int flag) {
		super();
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_state = m_state;
		this.m_url = m_url;
		this.flag = flag;
	}
	public MenuBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MenuBean [m_id=" + m_id + ", m_name=" + m_name + ", m_state="
				+ m_state + ", m_url=" + m_url + ", flag=" + flag + "]";
	}
	
	
}
