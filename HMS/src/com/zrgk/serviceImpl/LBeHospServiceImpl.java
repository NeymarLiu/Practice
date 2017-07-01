package com.zrgk.serviceImpl;


import java.util.List;

import com.zrgk.bean.LBeHospBean;
import com.zrgk.bean.LHosrBean;
import com.zrgk.bean.LCanShu;
import com.zrgk.bean.LMoHu;
import com.zrgk.dao.LBeHospDao;
import com.zrgk.daoImpl.LBeHospDaoImpl;
import com.zrgk.service.LBeHospService;
import com.zrgk.util.PartPage;

public class LBeHospServiceImpl implements LBeHospService {

	LBeHospDao bhd;
	public LBeHospServiceImpl(){
		bhd=new LBeHospDaoImpl();
	}
	//查询所有的住院信息
	public List<LCanShu> getAllHosr(String nowPage) {
		return bhd.getAllHosr(nowPage);
	}
	@Override
	public List<LCanShu> getAllHosr() {
		return bhd.getAllHosr();
	}
	//通过病例号查询相关病人信息
	public LHosrBean getOneInfos(String id) {
		return bhd.getOneInfos(id);
	}
	//通过id修改相关病人的数据
	public boolean updateOneBe(LBeHospBean bhb) {
		return bhd.updateOneBe(bhb);
	}
	//通过id病历号查修病人的一部分信息
	public LCanShu getOneMany(String id) {
		return bhd.getOneMany(id);
	}
	//通过病例号修改押金
	@Override
	public boolean editMany(String id,double money) {
		return bhd.editMany(id,money);
	}
	//通过病例号得到病人的相关信息
	@Override
	public List<LHosrBean> addOne(String id) {
		return bhd.addOne(id);
	}
	//通过病例号出院
	@Override
	public boolean chuyuan(String id) {
		return bhd.chuyuan(id);
	}
	//通过病例号退院
	@Override
	public boolean tuiyuan(String id) {
		return bhd.tuiyuan(id);
	}
	//模糊查询
	@Override
	public List<LCanShu> moHu(LMoHu lm) {
		return bhd.moHu(lm);
	}

	@Override
	public List<LCanShu> moHu(LMoHu lm, String page) {
		// TODO Auto-generated method stub
		return bhd.moHu(lm,page);
	}
	//通过病例号添加住院信息
	@Override
	public boolean tianJia(LBeHospBean lbb) {
		return bhd.tianJia(lbb);
	}
	//根据病历号得到响应的数据
	@Override
	public LCanShu getSomeInfo(String id) {
		return bhd.getSomeInfo(id);
	}

	//通过病例号查询该人是否住过院
	@Override
	public boolean selectOne(String id) {
		return bhd.selectOne(id);
	}

	//通过病例号查询该人是否住过院
	@Override
	public boolean updateOne(LBeHospBean lbb) {
		return bhd.updateOne(lbb);
	}
	
	public PartPage getPartPage(int count,int page) {
		PartPage p=new PartPage();
		int pages=0;
		if (count%PartPage.pageSize!=0) {
			pages=count/PartPage.pageSize+1;
		}else {
			pages=count/PartPage.pageSize;
		}
		p.setCount(count);
		p.setNowPage(page);
		p.setTotalPage(pages);
	return p;
}
	//查询所有的住院的床位号
	@Override
	public List<LCanShu> getAllPbed( String cid) {
		return bhd.getAllPbed(cid);
	}
	//查询所有的住院的床位号
	@Override
	public List<LCanShu> getAllPbed() {
		return bhd.getAllPbed();
	}
}
