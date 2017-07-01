package com.zrgk.service;

import java.util.List;

import com.zrgk.bean.DrugBean;

public interface DrugService {
	public int getAllDrug(String dr_name,String dr_type);
	public DrugBean lookOneDrug(String id);
	public List<DrugBean> getNowPageDrug(int nowPage,String dr_name,String dr_type);
	public boolean changeDrug(String id,int number);
	public boolean addDrug(List<String> lists);
	public boolean editDrug(List<String> lists);
}
