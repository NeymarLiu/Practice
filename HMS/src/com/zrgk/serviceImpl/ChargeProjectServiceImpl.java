package com.zrgk.serviceImpl;

import java.util.List;

import com.zrgk.bean.ChargeProjectBean;
import com.zrgk.dao.ChargeProjectDao;
import com.zrgk.daoImpl.ChargeProjectDaoImpl;
import com.zrgk.service.ChargeProjectService;

public class ChargeProjectServiceImpl implements ChargeProjectService{
	ChargeProjectDao cpd;
	public  ChargeProjectServiceImpl() {
		cpd=new ChargeProjectDaoImpl();
	}
	@Override
	public int getAllProject() {
		// TODO Auto-generated method stub
		return cpd.getAllProject();
	}
	@Override
	public ChargeProjectBean getOneChargeProject(String id) {
		// TODO Auto-generated method stub
		return cpd.getOneChargeProject(id);
	}
	@Override
	public boolean editChargeProject(ChargeProjectBean cpb) {
		// TODO Auto-generated method stub
		return cpd.editChargeProject(cpb);
	}
	@Override
	public List<ChargeProjectBean> getAllProjects() {
		// TODO Auto-generated method stub
		return cpd.getAllProjects();
	}
	@Override
	public boolean delteOneChargeProject(String id) {
		// TODO Auto-generated method stub
		return cpd.delteOneChargeProject(id);
	}
	@Override
	public boolean insertOneChargeProject(String cpname, String price) {
		// TODO Auto-generated method stub
		return cpd.insertOneChargeProject(cpname, price);
	}
	@Override
	public int mohu(String name) {
		// TODO Auto-generated method stub
		return cpd.mohu(name);
	}
	@Override
	public List<ChargeProjectBean> getAllProject(String page) {
		// TODO Auto-generated method stub
		return cpd.getAllProject(page);
	}
	@Override
	public List<ChargeProjectBean> mohu(String name, String page) {
		// TODO Auto-generated method stub
		return cpd.mohu(name, page);
	}
	@Override
	public int getOneCHargeProject() {
		// TODO Auto-generated method stub
		return cpd.getOneCHargeProject();
	}
	@Override
	public List<String> getChargeProjectName() {
		// TODO Auto-generated method stub
		return cpd.getChargeProjectName();
	}
	@Override
	public List<String> getChargeProjectName(String id) {
		// TODO Auto-generated method stub
		return cpd.getChargeProjectName(id);
	}

}
