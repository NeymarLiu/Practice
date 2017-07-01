package com.zrgk.bean;

/**
 * Drug药品管理表
 * 
 * @author 任兵
 * 
 */

public class DrugBean {
	private int dr_id;
	private String dr_name;
	private String dr_url;
	private double dr_inprice;
	private double dr_outprice;
	private String dr_type;
	private String dr_simdesc;
	private int dr_savetime;
	private String dr_desc;
	private String dr_factory;
	private String dr_direction;
	private String dr_remark;
	private int dr_number;
	private int dr_state;
	public DrugBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DrugBean(int dr_id, String dr_name, String dr_url,
			double dr_inprice, double dr_outprice, String dr_type,
			String dr_simdesc, int dr_savetime, String dr_desc,
			String dr_factory, String dr_direction, String dr_remark,
			int dr_number, int dr_state
			) {
		super();
		this.dr_id = dr_id;
		this.dr_name = dr_name;
		this.dr_url = dr_url;
		this.dr_inprice = dr_inprice;
		this.dr_outprice = dr_outprice;
		this.dr_type = dr_type;
		this.dr_simdesc = dr_simdesc;
		this.dr_savetime = dr_savetime;
		this.dr_desc = dr_desc;
		this.dr_factory = dr_factory;
		this.dr_direction = dr_direction;
		this.dr_remark = dr_remark;
		this.dr_number = dr_number;
		this.dr_state = dr_state;
	}
	public int getDr_id() {
		return dr_id;
	}
	public void setDr_id(int dr_id) {
		this.dr_id = dr_id;
	}
	public String getDr_name() {
		return dr_name;
	}
	public void setDr_name(String dr_name) {
		this.dr_name = dr_name;
	}
	public String getDr_url() {
		return dr_url;
	}
	public void setDr_url(String dr_url) {
		this.dr_url = dr_url;
	}
	public double getDr_inprice() {
		return dr_inprice;
	}
	public void setDr_inprice(double dr_inprice) {
		this.dr_inprice = dr_inprice;
	}
	public double getDr_outprice() {
		return dr_outprice;
	}
	public void setDr_outprice(double dr_outprice) {
		this.dr_outprice = dr_outprice;
	}
	public String getDr_type() {
		return dr_type;
	}
	public void setDr_type(String dr_type) {
		this.dr_type = dr_type;
	}
	public String getDr_simdesc() {
		return dr_simdesc;
	}
	public void setDr_simdesc(String dr_simdesc) {
		this.dr_simdesc = dr_simdesc;
	}
	public int getDr_savetime() {
		return dr_savetime;
	}
	public void setDr_savetime(int dr_savetime) {
		this.dr_savetime = dr_savetime;
	}
	public String getDr_desc() {
		return dr_desc;
	}
	public void setDr_desc(String dr_desc) {
		this.dr_desc = dr_desc;
	}
	public String getDr_factory() {
		return dr_factory;
	}
	public void setDr_factory(String dr_factory) {
		this.dr_factory = dr_factory;
	}
	public String getDr_direction() {
		return dr_direction;
	}
	public void setDr_direction(String dr_direction) {
		this.dr_direction = dr_direction;
	}
	public String getDr_remark() {
		return dr_remark;
	}
	public void setDr_remark(String dr_remark) {
		this.dr_remark = dr_remark;
	}
	public int getDr_number() {
		return dr_number;
	}
	public void setDr_number(int dr_number) {
		this.dr_number = dr_number;
	}
	public int getDr_state() {
		return dr_state;
	}
	public void setDr_state(int dr_state) {
		this.dr_state = dr_state;
	}
	public DrugBean(String dr_name, String dr_type) {
		super();
		this.dr_name = dr_name;
		this.dr_type = dr_type;
	}
	public DrugBean(int dr_id, String dr_name, String dr_url,
			double dr_inprice, double dr_outprice, String dr_type,
			String dr_simdesc, int dr_savetime, String dr_desc,
			String dr_factory, String dr_direction, String dr_remark) {
		super();
		this.dr_id = dr_id;
		this.dr_name = dr_name;
		this.dr_url = dr_url;
		this.dr_inprice = dr_inprice;
		this.dr_outprice = dr_outprice;
		this.dr_type = dr_type;
		this.dr_simdesc = dr_simdesc;
		this.dr_savetime = dr_savetime;
		this.dr_desc = dr_desc;
		this.dr_factory = dr_factory;
		this.dr_direction = dr_direction;
		this.dr_remark = dr_remark;
	}
	public DrugBean(int dr_id, String dr_name, String dr_url,
			double dr_inprice, double dr_outprice, String dr_type,
			String dr_simdesc, int dr_savetime, String dr_desc,
			String dr_factory, String dr_direction, String dr_remark,
			int dr_state) {
		super();
		this.dr_id = dr_id;
		this.dr_name = dr_name;
		this.dr_url = dr_url;
		this.dr_inprice = dr_inprice;
		this.dr_outprice = dr_outprice;
		this.dr_type = dr_type;
		this.dr_simdesc = dr_simdesc;
		this.dr_savetime = dr_savetime;
		this.dr_desc = dr_desc;
		this.dr_factory = dr_factory;
		this.dr_direction = dr_direction;
		this.dr_remark = dr_remark;
		this.dr_state = dr_state;
	}
	
	
}
