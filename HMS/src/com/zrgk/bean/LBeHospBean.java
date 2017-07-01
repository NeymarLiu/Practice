package com.zrgk.bean;
/**
 * behosptial住院表
 * @author 龙汶宇
 *
 */
public class LBeHospBean {

	private int beh_id;//编号
	private String name;//姓名
	private String beh_nursepeople;//护理人
	private int beh_patbed;//病床号
	private double beh_antecedent;//交纳押金
	private double beh_leftmoney;//押金余额
	private String beh_illness;//病情介绍
	private String beh_time;//住院时间
	private int beh_closeprice;//结算状态：0未结算1已结算
	private int beh_state;//信息状态 0：已住院 1.已出院 2：已退院 '
	
	
	//住院    修改
	public LBeHospBean(int beh_id, String beh_nursepeople, int beh_patbed,
			String beh_illness) {
		super();
		this.beh_id = beh_id;
		this.beh_nursepeople = beh_nursepeople;
		this.beh_patbed = beh_patbed;
		this.beh_illness = beh_illness;
	}
	public LBeHospBean(int beh_id, String beh_nursepeople, int beh_patbed,
			double beh_antecedent, double beh_leftmoney, String beh_illness,
			String beh_time) {
		super();
		this.beh_id = beh_id;
		this.beh_nursepeople = beh_nursepeople;
		this.beh_patbed = beh_patbed;
		this.beh_antecedent=beh_antecedent;
		this.beh_leftmoney=beh_leftmoney;
		this.beh_illness = beh_illness;
		this.beh_time=beh_time;
	}
	public LBeHospBean() {
		super();
	}
	
	public LBeHospBean(int beh_id, String beh_nursepeople, int beh_patbed,
			double beh_antecedent, double beh_leftmoney, String beh_illness,
			String beh_time, int beh_closeprice, int beh_state) {
		super();
		this.beh_id = beh_id;
		this.beh_nursepeople = beh_nursepeople;
		this.beh_patbed = beh_patbed;
		this.beh_antecedent = beh_antecedent;
		this.beh_leftmoney = beh_leftmoney;
		this.beh_illness = beh_illness;
		this.beh_time = beh_time;
		this.beh_closeprice = beh_closeprice;
		this.beh_state = beh_state;
	}
	
	
	
	public LBeHospBean(int beh_id, String name, 
			double beh_antecedent, double beh_leftmoney) {
		super();
		this.beh_id = beh_id;
		this.name = name;
		this.beh_antecedent = beh_antecedent;
		this.beh_leftmoney = beh_leftmoney;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBeh_id() {
		return beh_id;
	}
	public void setBeh_id(int beh_id) {
		this.beh_id = beh_id;
	}
	public String getBeh_nursepeople() {
		return beh_nursepeople;
	}
	public void setBeh_nursepeople(String beh_nursepeople) {
		this.beh_nursepeople = beh_nursepeople;
	}
	public int getBeh_patbed() {
		return beh_patbed;
	}
	public void setBeh_patbed(int beh_patbed) {
		this.beh_patbed = beh_patbed;
	}
	public double getBeh_antecedent() {
		return beh_antecedent;
	}
	public void setBeh_antecedent(double beh_antecedent) {
		this.beh_antecedent = beh_antecedent;
	}
	public double getBeh_leftmoney() {
		return beh_leftmoney;
	}
	public void setBeh_leftmoney(double beh_leftmoney) {
		this.beh_leftmoney = beh_leftmoney;
	}
	public String getBeh_illness() {
		return beh_illness;
	}
	public void setBeh_illness(String beh_illness) {
		this.beh_illness = beh_illness;
	}
	public String getBeh_time() {
		return beh_time;
	}
	public void setBeh_time(String beh_time) {
		this.beh_time = beh_time;
	}
	public int getBeh_closeprice() {
		return beh_closeprice;
	}
	public void setBeh_closeprice(int beh_closeprice) {
		this.beh_closeprice = beh_closeprice;
	}
	public int getBeh_state() {
		return beh_state;
	}
	public void setBeh_state(int beh_state) {
		this.beh_state = beh_state;
	}

	
}
