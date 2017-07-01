package com.zrgk.bean;

/**
 * Hosregister病人信息表
 * 
 * @author 王明勇
 * 
 */
public class WHosrBean {
	private int hosr_id;// 挂号编号 也是病人编号
	private int beh_id;//住院id
	private String hosr_name;// 姓名
	private double beh_antecedent;//押金
	private double beh_leftmoney;// 押金余额
	private int chap_id;//项目id
	private String  cp_name;//收费项目名称
	private int cp_price;//收费项目价格
	private String hosr_remark;// 备注
	private String chap_time;//收费时间
	private String beh_time;// 住院时间
	private int hosr_state;// 挂号状态0挂号1已住院2已结算3已退号4已出院5已退院
	
	public int getBeh_id() {
		return beh_id;
	}
	public void setChap_id(int chap_id) {
		this.chap_id = chap_id;
	}
	public double getBeh_antecedent() {
		return beh_antecedent;
	}
	public void setBeh_antecedent(Double beh_antecedent) {
		this.beh_antecedent = beh_antecedent;
	}
	public int getHosr_id() {
		return hosr_id;
	}
	public void setHosr_id(int hosr_id) {
		this.hosr_id = hosr_id;
	}
	public int getbeh_id() {
		return beh_id;
	}
	public void setBeh_id(int beh_id) {
		this.beh_id = beh_id;
	}
	public int getChap_id() {
		return hosr_id;
		}
	public void setChapr_id(int Chap_id) {
		this.chap_id = Chap_id;
	}
	public String getHosr_name() {
		return hosr_name;
	}
	public void setHosr_name(String hosr_name) {
		this.hosr_name = hosr_name;
	}
	public double getBeh_leftmoney() {
		return beh_leftmoney;
	}
	public void setBeh_leftmoney(double beh_leftmoney) {
		this.beh_leftmoney = beh_leftmoney;
	}
	public String getCp_name() {
		return cp_name;
	}
	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}
	public int getCp_price() {
		return cp_price;
	}
	public void setCp_price(int cp_price) {
		this.cp_price = cp_price;
	}
	public String getHosr_remark() {
		return hosr_remark;
	}
	public void setHosr_remark(String hosr_remark) {
		this.hosr_remark = hosr_remark;
	}
	public String getChap_time() {
		return chap_time;
	}
	public void setChap_time(String chap_time) {
		this.chap_time = chap_time;
	}
	public String getBeh_time() {
		return beh_time;
	}
	public void setBeh_time(String beh_time) {
		this.beh_time = beh_time;
	}
	public int getHosr_state() {
		return hosr_state;
	}
	public void setHosr_state(int hosr_state) {
		this.hosr_state = hosr_state;
	}
	public WHosrBean(int hosr_id,Integer beh_id, String hosr_name,double beh_antecedent, double beh_leftmoney,Integer chap_id,
			String cp_name, int cp_price, String hosr_remark, String chap_time,
			String beh_time, int hosr_state) {
		super();
		this.hosr_id = hosr_id;
		this.beh_id = beh_id;
		this.hosr_name = hosr_name;
		this.beh_antecedent=beh_antecedent;
		this.beh_leftmoney = beh_leftmoney;
		this.chap_id = chap_id;
		this.cp_name = cp_name;
		this.cp_price = cp_price;
		this.hosr_remark = hosr_remark;
		this.chap_time = chap_time;
		this.beh_time = beh_time;
		this.hosr_state = hosr_state;
	}
	public WHosrBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WHosrBean(int hosr_id, String hosr_name, String beh_time,
			int hosr_state,double beh_antecedent) {
		super();
		this.hosr_id = hosr_id;
		this.hosr_name = hosr_name;
		this.beh_time = beh_time;
		this.hosr_state = hosr_state;
		this.beh_antecedent=beh_antecedent;
	}
	public WHosrBean(int hosr_id, String hosr_name, String beh_time,
			int hosr_state) {
		super();
		this.hosr_id = hosr_id;
		this.hosr_name = hosr_name;
		this.beh_time = beh_time;
		this.hosr_state = hosr_state;
	}
	public WHosrBean(int hosr_id, String hosr_name, String cp_name,
			int cp_price, String chap_time) {
		super();
		this.hosr_id = hosr_id;
		this.hosr_name = hosr_name;
		this.cp_name = cp_name;
		this.cp_price = cp_price;
		this.chap_time = chap_time;
	}
	public WHosrBean(int hosr_id, String hosr_name, String cp_name,
			int cp_price, String chap_time,double beh_antecedent) {
		super();
		this.hosr_id = hosr_id;
		this.hosr_name = hosr_name;
		this.cp_name = cp_name;
		this.cp_price = cp_price;
		this.chap_time = chap_time;
		this.beh_antecedent = beh_antecedent;
	}
	public void setBeh_antecedent(double beh_antecedent) {
		this.beh_antecedent = beh_antecedent;
	}
	@Override
	public String toString() {
		return "WHosrBean [hosr_id=" + hosr_id + ", hosr_name=" + hosr_name
				+ ", cp_name=" + cp_name + ", cp_price=" + cp_price
				+ ", chap_time=" + chap_time + "]";
	}
	public WHosrBean(String cp_name) {
		super();
		this.cp_name = cp_name;
	}
	
	
}
	
	
