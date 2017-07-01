package com.zrgk.bean;

public class LCanShu {

	private int beh_id;//编号
	private String hosr_name;//姓名
	private String hosr_idcar;// 身份证号
	private int beh_patbed;//病床号
	private String hosr_phone;//电话
	private double beh_antecedent;//交纳押金
	private double beh_leftmoney;//押金余额
	private String d_name;//主治医生
	private String beh_time;//住院时间
	private String keshi;//科室
	private int hosr_state;//挂号状态0挂号1已住院2已结算3已退号4已出院5已退院
	
	public LCanShu() {
		super();
	}
	public LCanShu(int beh_id,  int beh_patbed) {
		super();
		this.beh_id = beh_id;
		this.beh_patbed = beh_patbed;
	}
	public LCanShu(int beh_id, String hosr_name, String hosr_idcar,
			double beh_antecedent, double beh_leftmoney) {
		super();
		this.beh_id = beh_id;
		this.hosr_name = hosr_name;
		this.hosr_idcar = hosr_idcar;
		this.beh_antecedent = beh_antecedent;
		this.beh_leftmoney = beh_leftmoney;
	}

	public LCanShu(int beh_id, String hosr_name, int beh_patbed,
			String hosr_phone, double beh_antecedent, String d_name,
			String beh_time, String keshi, int hosr_state) {
		super();
		this.beh_id = beh_id;
		this.hosr_name = hosr_name;
		this.beh_patbed = beh_patbed;
		this.hosr_phone = hosr_phone;
		this.beh_antecedent = beh_antecedent;
		this.d_name = d_name;
		this.beh_time = beh_time;
		this.keshi = keshi;
		this.hosr_state = hosr_state;
	}
	
	public String getHosr_idcar() {
		return hosr_idcar;
	}
	public void setHosr_idcar(String hosr_idcar) {
		this.hosr_idcar = hosr_idcar;
	}
	public double getBeh_leftmoney() {
		return beh_leftmoney;
	}
	public void setBeh_leftmoney(double beh_leftmoney) {
		this.beh_leftmoney = beh_leftmoney;
	}
	public int getBeh_id() {
		return beh_id;
	}
	public void setBeh_id(int beh_id) {
		this.beh_id = beh_id;
	}
	public String getHosr_name() {
		return hosr_name;
	}
	public void setHosr_name(String hosr_name) {
		this.hosr_name = hosr_name;
	}
	public int getBeh_patbed() {
		return beh_patbed;
	}
	public void setBeh_patbed(int beh_patbed) {
		this.beh_patbed = beh_patbed;
	}
	public String getHosr_phone() {
		return hosr_phone;
	}
	public void setHosr_phone(String hosr_phone) {
		this.hosr_phone = hosr_phone;
	}
	public double getBeh_antecedent() {
		return beh_antecedent;
	}
	public void setBeh_antecedent(double beh_antecedent) {
		this.beh_antecedent = beh_antecedent;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getBeh_time() {
		return beh_time;
	}
	public void setBeh_time(String beh_time) {
		this.beh_time = beh_time;
	}
	public String getKeshi() {
		return keshi;
	}
	public void setKeshi(String keshi) {
		this.keshi = keshi;
	}
	public int getHosr_state() {
		return hosr_state;
	}
	public void setHosr_state(int hosr_state) {
		this.hosr_state = hosr_state;
	}
	
	
}
