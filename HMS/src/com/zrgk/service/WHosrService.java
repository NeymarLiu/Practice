package com.zrgk.service;

import java.sql.Date;
import java.util.List;

import com.zrgk.bean.LHosrBean;
import com.zrgk.bean.WHosrBean;

public interface WHosrService {
	//展现所有病人信息
	public List<WHosrBean> getAllWHosr(String page);
	//根据id展现病人收费项目和缴费的详情
	public List<WHosrBean> getOneWHosr(String id, String page);
	//添加病人的收费项目
	public boolean insertOneWHosr(String bid,String cid,String time);
	public List<WHosrBean> getOneWhosr(String cpname);
	//模糊查询
	public List<WHosrBean> moHu(String id,String name, String page);
	//展现所有病人信息
	public int getAllWHosr() ;
	//根据id展现病人收费项目和缴费的详情
	public int getOneWHosr(String id);
	//模糊查询
	public int moHu(String id,String name);
	//展现所有病人信息
	public List<WHosrBean>  getAllWHosrs() ;
	//根据id展现病人收费项目和缴费的详情
	public List<WHosrBean>  getOneWHosrs(String id);
}
