package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgk.bean.LBeHospBean;
import com.zrgk.bean.LChap;
import com.zrgk.bean.LMoHu;
import com.zrgk.dao.LHosrDao;
import com.zrgk.util.JDBCTemplate;

public class LHosrDaoImpl implements LHosrDao {

	JDBCTemplate jt;
	public LHosrDaoImpl(){
		jt=new JDBCTemplate();
	}
	//住院没结算
	@Override
	public List<LBeHospBean> getAllHosr() {
		String sql="SELECT beh_id,hosr_name,beh_antecedent,beh_leftmoney FROM behospital be "
					+"LEFT JOIN hosregister h ON be.beh_id=h.hosr_id  WHERE beh_closeprice=0";
		List<LBeHospBean> list=new ArrayList<LBeHospBean>();
		
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				LBeHospBean bhb=new LBeHospBean(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
				list.add(bhb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	//住院没结算
	@Override
	public List<LBeHospBean> getAllHosr(String page) {
		int a=(Integer.parseInt(page)-1)*5;
		String sql="SELECT beh_id,hosr_name,beh_antecedent,beh_leftmoney FROM behospital be "
					+"LEFT JOIN hosregister h ON be.beh_id=h.hosr_id  WHERE beh_closeprice=0 limit "
				+a+",5";
		List<LBeHospBean> list=new ArrayList<LBeHospBean>();
		
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				LBeHospBean bhb=new LBeHospBean(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
				list.add(bhb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	//模糊查询
	@Override
	public List<LBeHospBean> moHu(LMoHu lm) {
		String sql ="SELECT beh_id,hosr_name,beh_antecedent,beh_leftmoney FROM behospital be "
				+"LEFT JOIN hosregister h ON be.beh_id=h.hosr_id  WHERE beh_closeprice=0";
		if(lm.getId() != null && lm.getId() != ""){
			sql+=" and beh_id like '%"+lm.getId()+"%'";
		}
		if(lm.getName() != null && lm.getName() != ""){
			sql+=" and hosr_name like '%"+lm.getName()+"%'";
		}
		List<LBeHospBean> list =new ArrayList<LBeHospBean>();
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				LBeHospBean bhb=new LBeHospBean(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
				list.add(bhb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	//模糊查询
		@Override
		public List<LBeHospBean> moHu(LMoHu lm,String page) {
			int a=(Integer.parseInt(page)-1)*5;
			String sql ="SELECT beh_id,hosr_name,beh_antecedent,beh_leftmoney FROM behospital be "
					+"LEFT JOIN hosregister h ON be.beh_id=h.hosr_id  WHERE beh_closeprice=0 ";
			if(lm.getId() != null && lm.getId() != ""){
				sql+=" and beh_id like '%"+lm.getId()+"%'";
			}
			if(lm.getName() != null && lm.getName() != ""){
				sql+=" and hosr_name like '%"+lm.getName()+"%'";
			}
			sql+=" limit "+a+",5";
			List<LBeHospBean> list =new ArrayList<LBeHospBean>();
			ResultSet rs=jt.query(sql);
			try {
				while(rs.next()){
					LBeHospBean bhb=new LBeHospBean(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
					list.add(bhb);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
			return list;
		}
	//通过病历号得到自己的费用
	@Override
	public List<LChap> details(String id) {
		String sql="SELECT  beh_id,hosr_name,chap_name,chap_price,ch.chap_time FROM chappeople ch" 
					+" LEFT JOIN hosregister ho ON ch.beh_id=ho.hosr_id "
					+"LEFT JOIN chargeproject cha ON ch.chap_id=cha.chap_id WHERE beh_id = "+id;
		List<LChap> list=new ArrayList<LChap>();
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				LChap lc=new LChap(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5));
				list.add(lc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	//通过病历号得到自己的费用
		@Override
		public List<LChap> details(String id,String page) {
			int a=(Integer.parseInt(page)-1)*5;
			String sql="SELECT  beh_id,hosr_name,chap_name,chap_price,ch.chap_time FROM chappeople ch" 
						+" LEFT JOIN hosregister ho ON ch.beh_id=ho.hosr_id "
						+"LEFT JOIN chargeproject cha ON ch.chap_id=cha.chap_id WHERE beh_id = "+id
						+" limit "+a+",5";
			List<LChap> list=new ArrayList<LChap>();
			ResultSet rs=jt.query(sql);
			try {
				while(rs.next()){
					LChap lc=new LChap(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5));
					list.add(lc);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
			return list;
		}
	//通过病历号得到自己的押金
	@Override
	public LChap money(String id) {
		String sql="SELECT beh_antecedent,beh_leftmoney FROM behospital WHERE beh_id ="+id;
		ResultSet rs=jt.query(sql);
		LChap lc=null;
		try {
			while(rs.next()){
				lc =new LChap( rs.getDouble(1), rs.getDouble(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lc;
	}
	//通过病历号 的到相关信息
	@Override
	public LBeHospBean getSomeInfo(String id) {
		String sql="SELECT beh_id,hosr_name,beh_antecedent,beh_leftmoney FROM behospital be "
				+"LEFT JOIN hosregister h ON be.beh_id=h.hosr_id  WHERE beh_closeprice=0 and beh_id="+id;
	
	ResultSet rs=jt.query(sql);
	LBeHospBean bhb=null;
	try {
		while(rs.next()){
			 bhb=new LBeHospBean(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4));
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		jt.closeRes();
	}
	return bhb;
	}
	//通过病例号结算
	@Override
	public boolean jieSuan(String id) {
		
		List<LChap> list=details(id);
		String sql1="select beh_leftmoney from behospital WHERE beh_id="+id;
		int a=0;
		try {
			ResultSet rs=jt.query(sql1);
			while(rs.next()){
				a=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double sum=0;
		for(int i=0;i<list.size();i++){
			sum +=list.get(i).getChap_price();
		}
		boolean b=false;
		if((a-sum)>0){
			String sql="UPDATE behospital be,hosregister h SET beh_leftmoney=beh_leftmoney-"
					+sum+",beh_closeprice =1,beh_state=2,hosr_state=2 WHERE h.hosr_id= "
					+id+" AND be.beh_id="+id;
		b=jt.updateData(sql);
		}
		
		return b;
	}

}
