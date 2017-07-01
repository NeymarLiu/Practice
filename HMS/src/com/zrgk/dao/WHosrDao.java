package com.zrgk.dao;

import java.sql.Date;
import java.util.List;

import com.zrgk.bean.WHosrBean;

public interface WHosrDao {
	//展现所有病人信息
	public int  getAllWHosr() ;
	//展现所有病人信息
	public List<WHosrBean>  getAllWHosr(String page) ;
	//展现所有病人信息
	public List<WHosrBean>  getAllWHosrs() ;
	//根据id展现病人收费项目和缴费的详情
	public int getOneWHosr(String id);
	//根据id展现病人收费项目和缴费的详情
	public List<WHosrBean>  getOneWHosr(String id,String page);
	//根据id展现病人收费项目和缴费的详情
	public List<WHosrBean>  getOneWHosrs(String id);
	//添加病人的收费项目
	public boolean insertOneWHosr(String bid,String cid,String time);
	public List<WHosrBean> getOneWhosr(String cpname);
	//模糊查询
	public int moHu(String id,String name);
	//模糊查询
	public List<WHosrBean>  moHu(String id,String name,String page);
	//根据id获取他的押金
	public WHosrBean getOneWHosrBean(String id);
	
}
