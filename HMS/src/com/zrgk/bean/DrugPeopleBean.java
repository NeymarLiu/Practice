package com.zrgk.bean;

/**
 * DrugPeople病人发药表
 * 
 * @author 任兵
 * 
 */
public class DrugPeopleBean {
	private int beh_id;// 挂号编号 也是病人编号
	private int dr_id;
	private int dr_number;
	private int dr_hadsent;
	private int dr_notsent;
	private String dr_time;
	private String dr_name;
	private int hosr_id;
	private String hosr_name;
	private int d_id;
	private String d_name;
	public DrugPeopleBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBeh_id() {
		return beh_id;
	}
	public void setBeh_id(int beh_id) {
		this.beh_id = beh_id;
	}
	public int getDr_id() {
		return dr_id;
	}
	public void setDr_id(int dr_id) {
		this.dr_id = dr_id;
	}
	public int getDr_number() {
		return dr_number;
	}
	public void setDr_number(int dr_number) {
		this.dr_number = dr_number;
	}
	public int getDr_hadsent() {
		return dr_hadsent;
	}
	public void setDr_hadsent(int dr_hadsent) {
		this.dr_hadsent = dr_hadsent;
	}
	public int getDr_notsent() {
		return dr_notsent;
	}
	public void setDr_notsent(int dr_notsent) {
		this.dr_notsent = dr_notsent;
	}
	public String getDr_time() {
		return dr_time;
	}
	public void setDr_time(String dr_time) {
		this.dr_time = dr_time;
	}
	public String getDr_name() {
		return dr_name;
	}
	public void setDr_name(String dr_name) {
		this.dr_name = dr_name;
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
	public DrugPeopleBean(int beh_id, String hosr_name, String d_name) {
		super();
		this.beh_id = beh_id;
		this.hosr_name = hosr_name;
		this.d_name = d_name;
	}
	public DrugPeopleBean(int beh_id, int dr_id, int dr_number, int dr_hadsent,
			int dr_notsent, String dr_name, String hosr_name) {
		super();
		this.beh_id = beh_id;
		this.dr_id = dr_id;
		this.dr_number = dr_number;
		this.dr_hadsent = dr_hadsent;
		this.dr_notsent = dr_notsent;
		this.dr_name = dr_name;
		this.hosr_name = hosr_name;
	}
	public DrugPeopleBean(int beh_id, int dr_id, String dr_time) {
		super();
		this.beh_id = beh_id;
		this.dr_id = dr_id;
		this.dr_time = dr_time;
	}
	public DrugPeopleBean(int dr_id, String dr_name) {
		super();
		this.dr_id = dr_id;
		this.dr_name = dr_name;
	}	
}
