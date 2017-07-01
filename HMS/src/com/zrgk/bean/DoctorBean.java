package com.zrgk.bean;

import java.io.Serializable;
import java.sql.Date;



/**
 * doctor医生个人信息
 * 
 * @author weiyangming
 *
 */

public class DoctorBean implements Serializable{
	private int d_id;//id
	private int d_code;//编号
	private String d_name;//医生姓名
	private String d_idcar;//医生身份证
	private String d_telphone;//医生手机号
	private String d_phone;//座机号
	private String d_sex;//性别
	private String d_birthday;//医生生日
	private int d_age;//医生年龄
	private String d_email;//医生的电子邮件
	private String d_keshi;//医生所属科室
	private String d_xueli;//医生学历
	private String d_desc;//医生描述
	private String d_intime;//入职时间
	private int d_state;//状态码0正常1删除
	private int sta=0;//科室被选中状态,0未被选中，1就是被选中
	private int xsta=0;//学历被选中状态，0未被选中，1就是被选中
	private int dsta=0;//医生被选中状态，0未被选中，1被选中
	public DoctorBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void getsta(int sta){
		this.sta = sta;
	}
	public void getxsta(int xsta){
		this.xsta = xsta;
	}
	public DoctorBean(int xsta) {
		super();
		this.xsta = xsta;
	}



	public int getDsta() {
		return dsta;
	}

	public void setDsta(int dsta) {
		this.dsta = dsta;
	}

	public DoctorBean(int d_id, int d_code, String d_name,String d_idcar,
			String d_telphone, String d_phone, String d_sex, String d_birthday,
			int d_age, String d_email, String d_keshi, String d_xueli,
			String d_desc, String d_intime, int d_state) {
		super();
		this.d_id = d_id;
		this.d_code = d_code;
		this.d_name = d_name;
		this.d_idcar = d_idcar;
		this.d_telphone = d_telphone;
		this.d_phone = d_phone;
		this.d_sex = d_sex;
		this.d_birthday = d_birthday;
		this.d_age = d_age;
		this.d_email = d_email;
		this.d_keshi = d_keshi;
		this.d_xueli = d_xueli;
		this.d_desc = d_desc;
		this.d_intime = d_intime;
		this.d_state = d_state;
	}
	public int getD_code() {
		return d_code;
	}
	public void setD_code(int d_code) {
		this.d_code = d_code;
	}
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getD_idcar() {
		return d_idcar;
	}
	public void setD_idcar(String d_idcar) {
		this.d_idcar = d_idcar;
	}
	public String getD_telphone() {
		return d_telphone;
	}
	public void setD_telphone(String d_telphone) {
		this.d_telphone = d_telphone;
	}
	public String getD_phone() {
		return d_phone;
	}
	public void setD_phone(String d_phone) {
		this.d_phone = d_phone;
	}
	public String getD_sex() {
		return d_sex;
	}
	public void setD_sex(String d_sex) {
		this.d_sex = d_sex;
	}
	public String getD_birthday() {
		return d_birthday;
	}
	public void setD_birthday(String d_birthday) {
		this.d_birthday = d_birthday;
	}
	public int getD_age() {
		return d_age;
	}
	public void setD_age(int d_age) {
		this.d_age = d_age;
	}
	public String getD_email() {
		return d_email;
	}
	public void setD_email(String d_email) {
		this.d_email = d_email;
	}
	public String getD_keshi() {
		return d_keshi;
	}
	public void setD_keshi(String d_keshi) {
		this.d_keshi = d_keshi;
	}
	public String getD_xueli() {
		return d_xueli;
	}
	public void setD_xueli(String d_xueli) {
		this.d_xueli = d_xueli;
	}
	public String getD_desc() {
		return d_desc;
	}
	public void setD_desc(String d_desc) {
		this.d_desc = d_desc;
	}
	public String getD_intime() {
		return d_intime;
	}
	public void setD_intime(String d_intime) {
		this.d_intime = d_intime;
	}
	public int getD_state() {
		return d_state;
	}
	public void setD_state(int d_state) {
		this.d_state = d_state;
	}

	public int getSta() {
		return sta;
	}

	public void setSta(int sta) {
		this.sta = sta;
	}

	public int getXsta() {
		return xsta;
	}

	public void setXsta(int xsta) {
		this.xsta = xsta;
	}

	
	
}
