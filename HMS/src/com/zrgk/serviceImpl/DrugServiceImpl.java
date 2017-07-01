package com.zrgk.serviceImpl;

import java.util.List;

import com.zrgk.bean.DrugBean;
import com.zrgk.dao.DrugDao;
import com.zrgk.daoImpl.DrugDaoImpl;
import com.zrgk.service.DrugService;

public class DrugServiceImpl implements DrugService {
	
	DrugDao dd=null;
	public DrugServiceImpl(){
		dd=new DrugDaoImpl();
	}

	@Override
	public int getAllDrug(String dr_name,String dr_type) {
		// TODO Auto-generated method stub
		return dd.getAllDrug(dr_name, dr_type);
	}
	@Override
	public DrugBean lookOneDrug(String id) {
		// TODO Auto-generated method stub
		return dd.lookOneDrug(id);
	}

	@Override
	public List<DrugBean> getNowPageDrug(int nowPage,String dr_name,String dr_type){
		// TODO Auto-generated method stub
		return dd.getNowPageDrug( nowPage, dr_name, dr_type);
	}

	@Override
	public  boolean changeDrug(String id,int number) {
		// TODO Auto-generated method stub
		return dd.changeDrug(id, number);
	}

	@Override
	public boolean addDrug(List<String> lists) {
		// TODO Auto-generated method stub
		return dd.addDrug(lists);
	}

	@Override
	public boolean editDrug(List<String> lists) {
		// TODO Auto-generated method stub
		return dd.editDrug(lists);
	}

}
