package com.zrgk.service;

import java.util.List;

import com.zrgk.bean.DoctorBean;
import com.zrgk.bean.HosrBean;

public interface Reigister {
	public List<HosrBean> allHosr(int start,int pageSize);
	public HosrBean conSecHosr(String id);
	public DoctorBean selDoctorName(int doctorId);
	public List<DoctorBean> selAllDoctorOffice(String hid);
	public List<DoctorBean> selAllDoctor(String hosrId);
	public boolean updateHosr(String hid,String name,String idcard,String medical,String money,String phone,String self,String sex,String age,String word,String look,String keshi,int did,String desc);
	public DoctorBean selDoctorId(String dname);
	public boolean deleteOneHosr(String hid);
	public List<HosrBean> selectQuery(String name,String illid,String room,String gotimeMin,String gotimeMax,int start,int pageSize);
	public boolean plSellect(String []id);
	public boolean addHosrInfo(String []information);
	public List<DoctorBean> selectAllDoctor();
	public List<DoctorBean> slectDoctorName(String office);
	public boolean selHosrIdcard(String idcard);
	public List<HosrBean> putExcal(String id);
	public boolean selDoctor(String idcard);
	public List<DoctorBean> putExcalDoctor(String id);
	public int selectHosrCount();
	public int selHosrCountLike(String hid,String name,String keshi,String mTimeMin,String mTimeMax);
}
