package com.zrgk.bean;

public class LChap {
	private int id;
	private String name;
	private String chap_name;
	private double chap_price;
	private String chap_time;
	private double huafei;
	private double yajin;
	private double yue;

	public LChap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LChap(double huafei, double yajin, double yue) {
		super();
		this.huafei = huafei;
		this.yajin = yajin;
		this.yue = yue;
	}
	public LChap( double yajin, double yue) {
		super();
		this.yajin = yajin;
		this.yue = yue;
	}

	public LChap(int id, String name, String chap_name, double chap_price,
			String chap_time) {
		super();
		this.id = id;
		this.name = name;
		this.chap_name = chap_name;
		this.chap_price = chap_price;
		this.chap_time = chap_time;
	}

	public double getHuafei() {
		return huafei;
	}

	public void setHuafei(double huafei) {
		this.huafei = huafei;
	}

	public double getYajin() {
		return yajin;
	}

	public void setYajin(double yajin) {
		this.yajin = yajin;
	}

	public double getYue() {
		return yue;
	}

	public void setYue(double yue) {
		this.yue = yue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChap_name() {
		return chap_name;
	}

	public void setChap_name(String chap_name) {
		this.chap_name = chap_name;
	}

	public double getChap_price() {
		return chap_price;
	}

	public void setChap_price(double chap_price) {
		this.chap_price = chap_price;
	}

	public String getChap_time() {
		return chap_time;
	}

	public void setChap_time(String chap_time) {
		this.chap_time = chap_time;
	}

}
