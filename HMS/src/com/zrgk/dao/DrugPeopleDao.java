package com.zrgk.dao;

import java.util.List;

import com.zrgk.bean.DrugPeopleBean;

public interface DrugPeopleDao {
	public int getAllDrugPeople(String beh_id, String hosr_name);
	public int lookDrugPeople(String id);
	public List<DrugPeopleBean> lookNowpageDrugPeople(int nowpage,String id);
	public List<DrugPeopleBean> getNowpageDrugPeople(int nowpage,String beh_id, String hosr_name);
	public List<DrugPeopleBean> giveDrugChooce(String beh_id);
	public double getOutPrise(String dr_id);
	public double getDrugNumber(String beh_id, String dr_id);
	public double getLeftmoney(String beh_id);
	public boolean updateLeftmoney(int beh_id, double beh_leftmoney);
	public boolean updateDrugPeople(DrugPeopleBean dpb,int number);
	public boolean updateDrugNumber(String dr_id,int number);
	public int getDrugHadNumber(int dr_id);
	public List<DrugPeopleBean> givesDrugChooce(String id);
}
