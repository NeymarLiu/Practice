package com.zrgk.serviceImpl;

import java.util.List;

import com.zrgk.bean.LBeHospBean;
import com.zrgk.bean.LChap;
import com.zrgk.bean.LMoHu;
import com.zrgk.dao.LHosrDao;
import com.zrgk.daoImpl.LHosrDaoImpl;
import com.zrgk.service.LHosrService;
import com.zrgk.util.PartPage;

public class LHosrServiceImpl implements LHosrService{

	LHosrDao hd;
	public LHosrServiceImpl(){
		hd=new LHosrDaoImpl();
	}
	//查询全部没有结算
	@Override
	public List<LBeHospBean> getAllHosr() {
		// TODO Auto-generated method stub
		return hd.getAllHosr();
	}
	@Override
	public List<LBeHospBean> getAllHosr(String page) {
		// TODO Auto-generated method stub
		return hd.getAllHosr(page);
	}
	//模糊查询
	@Override
	public List<LBeHospBean> moHu(LMoHu lm) {
		// TODO Auto-generated method stub
		return hd.moHu(lm);
	}
	//模糊查询
	@Override
	public List<LBeHospBean> moHu(LMoHu lm,String page) {
		// TODO Auto-generated method stub
		return hd.moHu(lm,page);
	}
	//通过病历号得到自己的费用
	@Override
	public List<LChap> details(String id) {
		// TODO Auto-generated method stub
		return hd.details(id);
	}
	//通过病历号得到自己的费用
	@Override
	public List<LChap> details(String id,String page) {
		// TODO Auto-generated method stub
		return hd.details(id,page);
	}
	//通过病历号得到自己的押金
	@Override
	public LChap money(String id) {
		// TODO Auto-generated method stub
		return hd.money(id);
	}
	//通过病历号 的到相关信息
	@Override
	public LBeHospBean getSomeInfo(String id) {
		return hd.getSomeInfo(id);
	}
	//通过病例号结算
	@Override
	public boolean jieSuan(String id) {
		return hd.jieSuan(id);
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
	

}
