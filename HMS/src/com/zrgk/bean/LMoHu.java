package com.zrgk.bean;

public class LMoHu {
	private String id;
	private String name;
	private String dor;
	private String keshi;
	private String qtime;
	private String htime;
	public LMoHu(String id, String dor, String keshi, String qtime, String htime) {
		super();
		this.id = id;
		this.dor = dor;
		this.keshi = keshi;
		this.qtime = qtime;
		this.htime = htime;
	}
	
	public LMoHu(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDor() {
		return dor;
	}
	public void setDor(String dor) {
		this.dor = dor;
	}
	public String getKeshi() {
		return keshi;
	}
	public void setKeshi(String keshi) {
		this.keshi = keshi;
	}
	public String getQtime() {
		return qtime;
	}
	public void setQtime(String qtime) {
		this.qtime = qtime;
	}
	public String getHtime() {
		return htime;
	}
	public void setHtime(String htime) {
		this.htime = htime;
	}
	
}
