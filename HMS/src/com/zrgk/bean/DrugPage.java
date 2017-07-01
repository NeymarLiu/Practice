package com.zrgk.bean;

public class DrugPage {
	private int count;
	private int nowpage;
	private static int size=5;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNowpage() {
		return nowpage;
	}
	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}
	public static int getSize() {
		return size;
	}
	public static void setSize(int size) {
		DrugPage.size = size;
	}
	public DrugPage(int count, int nowpage) {
		super();
		this.count = count;
		this.nowpage = nowpage;
	}
	public DrugPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
