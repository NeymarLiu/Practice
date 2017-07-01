package com.zrgk.dao;

import java.util.List;

import com.zrgk.bean.LBeHospBean;
import com.zrgk.bean.LHosrBean;
import com.zrgk.bean.LCanShu;
import com.zrgk.bean.LMoHu;

public interface LBeHospDao {

	//查询所有的住院信息
	public List<LCanShu> getAllHosr(String nowPage);
	//查询所有的住院信息
	public List<LCanShu> getAllHosr();
	//通过病例号查询相关病人信息
	public LHosrBean getOneInfos(String id);
	//通过id修改相关病人的数据
	public boolean updateOneBe(LBeHospBean bhb);
	//通过id病历号查修病人的一部分信息
	public LCanShu getOneMany(String id);
	//通过病例号修改押金
	public boolean editMany(String id,double money);
	//通过病例号得到病人的相关信息
	public List<LHosrBean> addOne(String id);
	//通过病例号出院
	public boolean chuyuan(String id);
	//通过病例号退院
	public boolean tuiyuan(String id);
	//模糊查询
	public List<LCanShu> moHu(LMoHu lm);
	//模糊查询
	public List<LCanShu> moHu(LMoHu lm,String page);
	//通过病历号添加住院信息
	public boolean tianJia(LBeHospBean lbb);
	//根据病历号得到响应的数据
	public LCanShu getSomeInfo(String id);
	//通过病例号查询该人是否住过院
	public boolean selectOne(String id);
	//通过病例号查询该人是否住过院
	public boolean updateOne(LBeHospBean lbb);
	//查询所有的住院的床位号
	public List<LCanShu> getAllPbed(String cid);
	//查询所有的住院的床位号
	public List<LCanShu> getAllPbed();
}
