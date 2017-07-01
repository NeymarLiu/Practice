package com.zrgk.bean;

public class LHosrBean {
	private int hosr_id;// 挂号编号 也是病人编号
	private String hosr_name;// 姓名
	private String hosr_idcar;// 身份证号
	private String hosr_medical;// 社保号
	private double hosr_regprice;// 挂号费
	private String hosr_phone;// 电话
	private int hosr_selfprice;// 是否自费 0自费 1用社保
	private String hosr_sex;// 性别
	private int hosr_age;// 年龄
	private String hosr_word;// 职业
	private int hosr_lookdoctor;// 初复诊0初诊1复诊
	private String keshi;// 科室
	private int d_id;// 医生id
	private String d_name;// 医生姓名
	private double beh_leftmoney;// 押金余额
	private String hosr_remark;// 备注
	private String hosr_time;// 挂号时间
	private int hosr_state;// 挂号状态0挂号1已住院2已结算3已退号4已出院5已退院

	private String beh_nursepeople;//护理人
	private int beh_patbed;//病床号
	private String beh_illness;//病情介绍
	
	public LHosrBean(int hosr_id, String hosr_name, String hosr_idcar,
			String hosr_medical, double hosr_regprice, String hosr_phone,
			int hosr_selfprice, String hosr_sex, int hosr_age,
			String hosr_word, int hosr_lookdoctor, String keshi, String d_name,
			String hosr_remark, int hosr_state) {
		super();
		this.hosr_id = hosr_id;
		this.hosr_name = hosr_name;
		this.hosr_idcar = hosr_idcar;
		this.hosr_medical = hosr_medical;
		this.hosr_regprice = hosr_regprice;
		this.hosr_phone = hosr_phone;
		this.hosr_selfprice = hosr_selfprice;
		this.hosr_sex = hosr_sex;
		this.hosr_age = hosr_age;
		this.hosr_word = hosr_word;
		this.hosr_lookdoctor = hosr_lookdoctor;
		this.keshi = keshi;
		this.d_name = d_name;
		this.hosr_remark = hosr_remark;
		this.hosr_state = hosr_state;
	}

	//查看详情时的参数类
	
	public LHosrBean(int hosr_id, String hosr_name, String hosr_idcar,
			String hosr_medical, String hosr_phone, int hosr_selfprice,
			int hosr_age, String hosr_sex, String hosr_word,
			int hosr_lookdoctor, String keshi, String d_name,
			double beh_leftmoney, String hosr_remark,
			String beh_nursepeople, int beh_patbed, String beh_illness) {
		super();
		this.hosr_id = hosr_id;
		this.hosr_name = hosr_name;
		this.hosr_idcar = hosr_idcar;
		this.hosr_medical = hosr_medical;
		this.hosr_phone = hosr_phone;
		this.hosr_selfprice = hosr_selfprice;
		this.hosr_sex = hosr_sex;
		this.hosr_age = hosr_age;
		this.hosr_word = hosr_word;
		this.hosr_lookdoctor = hosr_lookdoctor;
		this.keshi = keshi;
		this.d_name = d_name;
		this.beh_leftmoney = beh_leftmoney;
		this.hosr_remark = hosr_remark;
		this.beh_nursepeople = beh_nursepeople;
		this.beh_patbed = beh_patbed;
		this.beh_illness = beh_illness;
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

	public String getBeh_illness() {
		return beh_illness;
	}

	public void setBeh_illness(String beh_illness) {
		this.beh_illness = beh_illness;
	}
	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public double getBeh_leftmoney() {
		return beh_leftmoney;
	}

	public void setBeh_leftmoney(double beh_leftmoney) {
		this.beh_leftmoney = beh_leftmoney;
	}

	public int getHosr_id() {
		return hosr_id;
	}

	public void setHosr_id(int hosr_id) {
		this.hosr_id = hosr_id;
	}

	public String getHosr_name() {
		return hosr_name;
	}

	public void setHosr_name(String hosr_name) {
		this.hosr_name = hosr_name;
	}

	public String getHosr_idcar() {
		return hosr_idcar;
	}

	public void setHosr_idcar(String hosr_idcar) {
		this.hosr_idcar = hosr_idcar;
	}

	public String getHosr_medical() {
		return hosr_medical;
	}

	public void setHosr_medical(String hosr_medical) {
		this.hosr_medical = hosr_medical;
	}

	public double getHosr_regprice() {
		return hosr_regprice;
	}

	public void setHosr_regprice(double hosr_regprice) {
		this.hosr_regprice = hosr_regprice;
	}

	public String getHosr_phone() {
		return hosr_phone;
	}

	public void setHosr_phone(String hosr_phone) {
		this.hosr_phone = hosr_phone;
	}

	public int getHosr_selfprice() {
		return hosr_selfprice;
	}

	public void setHosr_selfprice(int hosr_selfprice) {
		this.hosr_selfprice = hosr_selfprice;
	}

	public String getHosr_sex() {
		return hosr_sex;
	}

	public void setHosr_sex(String hosr_sex) {
		this.hosr_sex = hosr_sex;
	}

	public int getHosr_age() {
		return hosr_age;
	}

	public void setHosr_age(int hosr_age) {
		this.hosr_age = hosr_age;
	}

	public String getHosr_word() {
		return hosr_word;
	}

	public void setHosr_word(String hosr_word) {
		this.hosr_word = hosr_word;
	}

	public int getHosr_lookdoctor() {
		return hosr_lookdoctor;
	}

	public void setHosr_lookdoctor(int hosr_lookdoctor) {
		this.hosr_lookdoctor = hosr_lookdoctor;
	}

	public String getKeshi() {
		return keshi;
	}

	public void setKeshi(String keshi) {
		this.keshi = keshi;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public String getHosr_remark() {
		return hosr_remark;
	}

	public void setHosr_remark(String hosr_remark) {
		this.hosr_remark = hosr_remark;
	}

	public String getHosr_time() {
		return hosr_time;
	}

	public void setHosr_time(String hosr_time) {
		this.hosr_time = hosr_time;
	}

	public int getHosr_state() {
		return hosr_state;
	}

	public void setHosr_state(int hosr_state) {
		this.hosr_state = hosr_state;
	}

}
