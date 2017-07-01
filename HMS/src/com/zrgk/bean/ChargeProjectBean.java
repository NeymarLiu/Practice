package com.zrgk.bean;
/**
 * 收费项目基础类
 * */
public class ChargeProjectBean {
	private int cp_id;//收费项目id
	private String  cp_name;//收费项目名称
	private double cp_price;//收费项目价格
	private String cp_date;//检查项创立时间
	private int cp_state;//状态 0：正常 1：删除
	
	public int getCp_id() {
		return cp_id;
	}
	public void setCp_id(int cp_id) {
		this.cp_id = cp_id;
	}
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	public double getCp_price() {
		return cp_price;
	}
	public void setCp_price(double cp_price) {
		this.cp_price = cp_price;
	}
	public String getCp_date() {
		return cp_date;
	}
	public void setCp_date(String cp_date) {
		this.cp_date = cp_date;
	}
	public int getCp_state() {
		return cp_state;
	}
	public void setCp_state(int cp_state) {
		this.cp_state = cp_state;
	}
	public ChargeProjectBean(int cp_id, String cp_name, double cp_price,
			String cp_date, int cp_state) {
		super();
		this.cp_id = cp_id;
		this.cp_name = cp_name;
		this.cp_price = cp_price;
		this.cp_date = cp_date;
		this.cp_state = cp_state;
	}
	public ChargeProjectBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChargeProjectBean(int cp_id, String cp_name, double cp_price) {
		super();
		this.cp_id = cp_id;
		this.cp_name = cp_name;
		this.cp_price = cp_price;
	}

	
}
