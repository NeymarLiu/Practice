package com.zrgk.dao;

import java.util.List;

import com.zrgk.bean.LBeHospBean;
import com.zrgk.bean.LChap;
import com.zrgk.bean.LMoHu;

public interface LHosrDao {

	//所有住院没有结算的
	public List<LBeHospBean> getAllHosr();
	//所有住院没有结算的
	public List<LBeHospBean> getAllHosr(String page);
	//模糊查询
	public List<LBeHospBean> moHu(LMoHu lm);
	//模糊查询
	public List<LBeHospBean> moHu(LMoHu lm,String page);
	//通过病历号得到自己的费用
	public List<LChap> details(String id);
	//通过病历号得到自己的费用
	public List<LChap> details(String id,String page);
	//通过病历号得到自己的押金
	public LChap money(String id);
	//通过病历号 的到相关信息
	public LBeHospBean getSomeInfo(String id);
	//通过病历号结算
	public boolean jieSuan(String id);
	
}
