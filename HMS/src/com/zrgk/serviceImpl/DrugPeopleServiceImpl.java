package com.zrgk.serviceImpl;

import java.util.List;

import com.zrgk.bean.DrugPeopleBean;
import com.zrgk.dao.DrugPeopleDao;
import com.zrgk.daoImpl.DrugPeopleDaoImpl;
import com.zrgk.service.DrugPeopleService;

public class DrugPeopleServiceImpl implements DrugPeopleService {
	
	DrugPeopleDao dpd=null;
	public DrugPeopleServiceImpl(){
		dpd=new DrugPeopleDaoImpl();
	}
	public int getAllDrugPeople(String beh_id, String hosr_name) {
		return dpd.getAllDrugPeople(beh_id, hosr_name);
	}
	public int lookDrugPeople(String id) {
		return dpd.lookDrugPeople(id);
	}
	public List<DrugPeopleBean> getNowpageDrugPeople(int nowpage,String beh_id, String hosr_name){
		return dpd.getNowpageDrugPeople(nowpage, beh_id,hosr_name);
	}
	public List<DrugPeopleBean> lookNowpageDrugPeople(int nowpage, String id) {
		return dpd.lookNowpageDrugPeople(nowpage, id);
	}
	public List<DrugPeopleBean> giveDrugChooce(String beh_id) {

		return dpd.giveDrugChooce(beh_id);
	}
	public double getOutPrise(String dr_id) {
		return dpd.getOutPrise(dr_id);
	}
	public double getDrugNumber(String beh_id, String dr_id) {
		return dpd.getDrugNumber(beh_id, dr_id);
	}
	public double getLeftmoney(String beh_id) {
		return dpd.getLeftmoney(beh_id);
	}
	public boolean updateLeftmoney(int beh_id, double beh_leftmoney) {

		return dpd.updateLeftmoney(beh_id, beh_leftmoney);
	}
	public boolean updateDrugPeople(DrugPeopleBean dpb,int number) {

		return dpd.updateDrugPeople(dpb, number);
	}
	public boolean updateDrugNumber(String dr_id, int number) {

		return dpd.updateDrugNumber(dr_id, number);
	}
	public int getDrugHadNumber(int dr_id) {
		return dpd.getDrugHadNumber(dr_id);
	}
	@Override
	public List<DrugPeopleBean> givesDrugChooce(String id) {
		// TODO Auto-generated method stub
		return dpd.givesDrugChooce(id);
	}
	
	
}
