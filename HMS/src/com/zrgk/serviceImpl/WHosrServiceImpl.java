package com.zrgk.serviceImpl;


import java.sql.Date;
import java.util.List;

import com.zrgk.bean.WHosrBean;
import com.zrgk.dao.WHosrDao;
import com.zrgk.daoImpl.WHosrDaoImpl;
import com.zrgk.service.WHosrService;

public class WHosrServiceImpl implements WHosrService {

	WHosrDao whd;
	public WHosrServiceImpl(){
		whd=new WHosrDaoImpl();
	}
	@Override
	public List<WHosrBean> getAllWHosr(String page) {
		// TODO Auto-generated method stub	
		return whd.getAllWHosr(page);
	}
	@Override
	public List<WHosrBean> getOneWHosr(String id, String page) {
		// TODO Auto-generated method stub
		return whd.getOneWHosr(id,page);
	}
	@Override
	public List<WHosrBean> getOneWhosr(String cpname) {
		// TODO Auto-generated method stub
		return whd.getOneWhosr(cpname);
	}
	@Override
	public boolean insertOneWHosr(String bid, String cid, String time) {
		// TODO Auto-generated method stub
		return whd.insertOneWHosr(bid, cid, time);
	}
	@Override
	public List<WHosrBean> moHu(String id, String name, String page) {
		// TODO Auto-generated method stub
		return whd.moHu(id, name,page);
	}
	@Override
	public int getAllWHosr() {
		// TODO Auto-generated method stub
		return whd.getAllWHosr();
	}
	@Override
	public int getOneWHosr(String id) {
		// TODO Auto-generated method stub
		return whd.getOneWHosr(id);
	}
	@Override
	public int moHu(String id, String name) {
		// TODO Auto-generated method stub
		return whd.moHu(id, name);
	}
	@Override
	public List<WHosrBean> getAllWHosrs() {
		// TODO Auto-generated method stub
		return whd.getAllWHosrs();
	}
	@Override
	public List<WHosrBean> getOneWHosrs(String id) {
		// TODO Auto-generated method stub
		return whd.getOneWHosrs(id);
	}	

}
