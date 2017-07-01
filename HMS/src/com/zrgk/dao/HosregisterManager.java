package com.zrgk.dao;

import java.util.Date;
import java.util.List;

import com.zrgk.bean.DoctorBean;
import com.zrgk.bean.HosrBean;

public interface HosregisterManager {
	//查询挂号信息
	public List<DoctorBean> getHosr(String sql);
	public List<HosrBean> getHosrInformation(String sql);
	public List<HosrBean> allhosr(int start,int pageSize);
	public HosrBean conSecHosr(int id);
	public DoctorBean selDoctorName(int doctorId);
	public List<DoctorBean> selAllDoctorOffice();
	public List<DoctorBean> selAllDoctor(String office);
	public boolean updateHosr(String hid,String name,String idcard,String medical,String money,String phone,String self,String sex,String age,String word,String look,String keshi,int did,String desc);
	public DoctorBean selDoctorId(String dname);
	public boolean deleteOneHosr(String hid);
	public List<HosrBean> selectQuery(String name,String illid,String room,String gotimeMin,String gotimeMax,int start,int pageSize);
	public List<DoctorBean> sDoctorId(String name);
	public boolean plSellect(String []id);
	public boolean addHosrInfo(String []information);
	public List<DoctorBean> selectAllDoctor();
	public List<DoctorBean> slectDoctorName(String office);
	public boolean selHosrIdcard(String idcard);
	public List<HosrBean> putExcal(String id);
	public boolean selDoctor(String id);
	public List<DoctorBean> putExcalDoctor(String id);
	public int selectHosrCount();
	public int selHosrCountLike(String hid,String name,String keshi,String mTimeMin,String mTimeMax);
}
