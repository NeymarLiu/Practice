package com.zrgk.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zrgk.bean.DoctorBean;
import com.zrgk.bean.HosrBean;
import com.zrgk.dao.HosregisterManager;
import com.zrgk.daoImpl.HosregisterManagerImpl;
import com.zrgk.service.Reigister;
import com.zrgk.util.JDBCTemplate;

public class ReigisterImpl implements Reigister {

	HosregisterManager hm=new HosregisterManagerImpl();
	@Override
	public List<HosrBean> allHosr(int start,int pageSize) {
		// 查询所有用户信息
		List<HosrBean> hList=hm.allhosr(start,pageSize);
		return hList;
	}

	@Override
	public HosrBean conSecHosr(String id) {
		// 查询详细信息
		HosrBean hb=null;
		int hid=Integer.parseInt(id);
		hb=hm.conSecHosr(hid);
		return hb;
	}

	@Override
	public DoctorBean selDoctorName(int doctorId) {
		// 查询医生姓名
		DoctorBean db=null;
		db=hm.selDoctorName(doctorId);
		return db;
	}

	@Override
	public List<DoctorBean> selAllDoctorOffice(String hid) {
		// 查询所有科室
		List<DoctorBean> db=new ArrayList<DoctorBean>();
		db=hm.selAllDoctorOffice();
		HosrBean hb=conSecHosr(hid);
		for (int i = 0; i < db.size(); i++) {
			if(db.get(i).getD_keshi().equals(hb.getKeshi())){
				db.get(i).setSta(1);
			}
		}
		return db;
	}

	@Override
	public List<DoctorBean> selAllDoctor(String hosrId) {
		// 查询医生姓名
		List<DoctorBean> db=new ArrayList<DoctorBean>();
		//List<DoctorBean> db2=this.selAllDoctorOffice(hosrId);
		HosrBean hb=conSecHosr(hosrId);
		db=hm.selAllDoctor(hb.getKeshi());
		for (int i = 0; i < db.size(); i++) {
			if(db.get(i).getD_id() == hb.getD_id()){
				db.get(i).setDsta(1);
			}
		}
		return db;
	}

	@Override
	public boolean updateHosr(String hid,String name,String idcard,String medical,String money,String phone,String self,String sex,String age,String word,String look,String keshi,int did,String desc) {
		// 修改挂号信息
		boolean boo=false;
		boo=hm.updateHosr( hid,name,idcard,medical,money,phone,self,sex,age,word,look,keshi,did,desc);
		return boo;
	}

	@Override
	public DoctorBean selDoctorId(String dname) {
		// 查询医生id
		DoctorBean db=null;
		db=hm.selDoctorId(dname);
		return db;
	}

	@Override
	public boolean deleteOneHosr(String hid) {
		// TODO Auto-generated method stub
		boolean b=false;
		b=hm.deleteOneHosr(hid);
		return b;
	}

	@Override
	public List<HosrBean> selectQuery(String name,String illid,String room,String gotimeMin,String gotimeMax,int start,int pageSize) {
		// 添加挂号信息,将String的数据类型转化成date数据类型
		List<HosrBean> hosrList=new ArrayList<HosrBean>();
		DoctorBean db=new DoctorBean();
		List<DoctorBean> dlist=new ArrayList<DoctorBean>();
		hosrList=hm.selectQuery(name, illid, room, gotimeMin, gotimeMax,start,pageSize);
		return hosrList;
	}

	@Override
	public boolean plSellect(String[] id) {
		// TODO Auto-generated method stub
		boolean list=hm.plSellect(id);
		return list;
	}

	@Override
	public boolean addHosrInfo(String[] information) {
		// TODO Auto-generated method stub
		boolean boo=hm.addHosrInfo(information);
		return boo;
	}

	@Override
	public List<DoctorBean> selectAllDoctor() {
		// TODO Auto-generated method stub
		List<DoctorBean> list=hm.selectAllDoctor();
		return list;
	}

	@Override
	public List<DoctorBean> slectDoctorName(String office) {
		// 从指定科室中查询医生
		List<DoctorBean> list=new ArrayList<DoctorBean>();
		list=hm.slectDoctorName(office);
		return list;
	}

	@Override
	public boolean selHosrIdcard(String idcard) {
		// TODO Auto-generated method stub
		boolean boo=hm.selHosrIdcard(idcard);
		return boo;
	}

	/**
	 * service,excal语句导出
	 */
	public List<HosrBean> putExcal(String id) {
		// TODO Auto-generated method stub
		List<HosrBean> list=hm.putExcal(id);
		return list;
	}

	@Override
	public boolean selDoctor(String idcard) {
		// TODO Auto-generated method stub
		boolean boo=hm.selDoctor(idcard);
		return boo;
	}

	@Override
	public List<DoctorBean> putExcalDoctor(String id) {
		// TODO Auto-generated method stub
		List<DoctorBean> list=hm.putExcalDoctor(id);
		return list;
	}

	@Override
	public int selectHosrCount() {
		// TODO Auto-generated method stub
		int count=hm.selectHosrCount();
		return count;
	}

	@Override
	public int selHosrCountLike(String hid, String name, String keshi,
			String mTimeMin, String mTimeMax) {
		// TODO Auto-generated method stub
		int count=hm.selHosrCountLike(hid, name, keshi, mTimeMin, mTimeMax);
		return count;
	}
	
	

}
