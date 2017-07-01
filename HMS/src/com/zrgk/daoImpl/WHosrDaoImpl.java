package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.formula.functions.Count;

import sun.security.action.GetIntegerAction;

import com.zrgk.bean.ChargeProjectBean;
import com.zrgk.bean.WHosrBean;
import com.zrgk.dao.WHosrDao;
import com.zrgk.util.JDBCTemplate;
import com.zrgk.util.PartPage;

public class WHosrDaoImpl implements WHosrDao {

	JDBCTemplate jt;
	public WHosrDaoImpl(){
		jt=new JDBCTemplate();
	}
	@Override
	public int  getAllWHosr() {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) " 
				+ "FROM behospital be  RIGHT JOIN hosregister ho ON ho.hosr_id=be.beh_id WHERE hosr_state IN (0,1) ";
		int count=0;
		ResultSet rs = jt.query(sql);
		WHosrBean whb = null;
		try {
			while (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return count;
	}
	@Override
	public int getOneWHosr(String id) {
		// TODO Auto-generated method stub
		int count=0;
		String sql= "SELECT count(*) FROM chappeople INNER JOIN "
				+"chargeproject ON chargeproject.chap_id=chappeople.chap_id INNER JOIN hosregister ON chappeople.beh_id=hosregister.hosr_id INNER JOIN behospital ON behospital.beh_id=chappeople.beh_id"
				+" WHERE chappeople.chap_id=chargeproject.chap_id AND chappeople.beh_id=hosregister.hosr_id AND hosr_state IN (1,2,4,5) and hosr_id="+id;
		WHosrBean whb = null;
		ResultSet rs = jt.query(sql);
		try {
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return count;
		
	}
	
	@Override
	public List<WHosrBean> getOneWhosr(String cpname) {
		// TODO Auto-generated method stub
		String sql="select chap_name from chargeproject";
		List<WHosrBean> list=new ArrayList<WHosrBean>();
		ResultSet rs= jt.query(sql);
		WHosrBean whb= null;
		try {
			while(rs.next()){
				whb= new WHosrBean(rs.getString(1));
				list.add(whb);
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	@Override
	public boolean insertOneWHosr(String bid, String cid, String time) {
		// TODO Auto-generated method stub
		String sql ="insert into chappeople values ("+bid+","
				+ ""+cid+",'"+time+"')";
		boolean b =jt.updateData(sql);
		return b;
	}
	@Override
	public int moHu(String id, String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) "
				+ "FROM behospital be  RIGHT JOIN hosregister ho "
				+ "ON ho.hosr_id=be.beh_id WHERE hosr_state IN (1,2,4,5) ";
		List<WHosrBean> list= new ArrayList<WHosrBean>();
		if(id!= null||id!=""){
			sql+=" and hosr_id like '%"+id+"%'";
		}
		if(name!=null||name!=""){
			sql+=" and hosr_name like '%"+name+"%'";
		}
		ResultSet rs=jt.query(sql);
		WHosrBean whb=null;
		int count=0;
		try {
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
	}
		return count;
	

	}
	@Override
	public WHosrBean getOneWHosrBean(String id) {
		// TODO Auto-generated method stub
		String sql="select beh_antecedent from behospital where beh_id="+id;
		ResultSet rs=jt.query(sql);
		WHosrBean whb=new WHosrBean();
			try {
				if(rs.next()){
					whb.setBeh_antecedent(rs.getDouble(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
		return whb;
	}
	@Override
	public List<WHosrBean> getAllWHosr(String page) {
		// TODO Auto-generated method stub
		List<WHosrBean> list = new ArrayList<WHosrBean>();
		int a=(Integer.parseInt(page)-1)*PartPage.pageSize;
		int count =0;
		String sql = "SELECT hosr_id,hosr_name,beh_time,hosr_state " 
				+ "FROM behospital be  RIGHT JOIN hosregister ho ON "
				+ "ho.hosr_id=be.beh_id WHERE hosr_state IN (0,1) limit "+a+","+PartPage.pageSize;
		ResultSet rs = jt.query(sql);
		WHosrBean whb =new WHosrBean();
		try {
			while (rs.next()) {
				whb = new WHosrBean(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4));
				System.out.println(whb);
				list.add(whb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	@Override
	public 	List<WHosrBean>  getOneWHosr(String id, String page) {
		// TODO Auto-generated method stub
		int a=(Integer.parseInt(page)-1)*PartPage.pageSize;
		List<WHosrBean> list = new ArrayList<WHosrBean>();
		int count=0;
		String sql= "SELECT hosr_id,hosregister.hosr_name,chargeproject.chap_name, chargeproject.chap_price,chappeople.chap_time,beh_antecedent FROM chappeople INNER JOIN "
				+"chargeproject ON chargeproject.chap_id=chappeople.chap_id INNER JOIN hosregister ON chappeople.beh_id=hosregister.hosr_id INNER JOIN behospital ON behospital.beh_id=chappeople.beh_id"
				+" WHERE chappeople.chap_id=chargeproject.chap_id AND chappeople.beh_id=hosregister.hosr_id AND hosr_state IN (1,2,4,5) and hosr_id="+id +" limit "+a+","+PartPage.pageSize;
		WHosrBean whb = null;
		ResultSet rs = jt.query(sql);
		try {
			while(rs.next()){
				whb = new WHosrBean(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDouble(6));
				list.add(whb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	@Override
	public 	List<WHosrBean>  moHu(String id, String name, String page) {
		// TODO Auto-generated method stub
		int a=(Integer.parseInt(page)-1)*PartPage.pageSize;
		int count=0;
		String sql = "SELECT hosr_id,hosr_name,beh_time,hosr_state  "
				+ "FROM behospital be  RIGHT JOIN hosregister ho "
				+ "ON ho.hosr_id=be.beh_id WHERE hosr_state IN (1,2,4,5) ";
		List<WHosrBean> list= new ArrayList<WHosrBean>();
		if(id!= null&&id!=""){
			sql+=" and hosr_id like '%"+id+"%'";
		}
		if(name!=null&&name!=""){
			sql+=" and hosr_name like '%"+name+"%'";
		}
		sql+="limit "+a+","+PartPage.pageSize;
		ResultSet rs=jt.query(sql);
		WHosrBean whb=null;
		try {
			while(rs.next()){
				whb=new WHosrBean(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getInt(4));
				list.add(whb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
	}
		return list ;
	}
	@Override
	public List<WHosrBean> getAllWHosrs() {
		List<WHosrBean> list = new ArrayList<WHosrBean>();
		int count =0;
		String sql = "SELECT hosr_id,hosr_name,beh_time,hosr_state " 
				+ "FROM behospital be  RIGHT JOIN hosregister ho ON "
				+ "ho.hosr_id=be.beh_id WHERE hosr_state IN (1,2,4,5) ";
		ResultSet rs = jt.query(sql);
		WHosrBean whb =new WHosrBean();
		try {
			while (rs.next()) {
				whb = new WHosrBean(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4));
				System.out.println(whb);
				list.add(whb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	@Override
	public List<WHosrBean> getOneWHosrs(String id) {
		// TODO Auto-generated method stub
				List<WHosrBean> list = new ArrayList<WHosrBean>();
				int count=0;
				String sql= "SELECT hosr_id,hosregister.hosr_name,chargeproject.chap_name, chargeproject.chap_price,chappeople.chap_time,beh_antecedent FROM chappeople INNER JOIN "
						+"chargeproject ON chargeproject.chap_id=chappeople.chap_id INNER JOIN hosregister ON chappeople.beh_id=hosregister.hosr_id INNER JOIN behospital ON behospital.beh_id=chappeople.beh_id"
						+" WHERE chappeople.chap_id=chargeproject.chap_id AND chappeople.beh_id=hosregister.hosr_id AND hosr_state IN (1,2,4,5) and hosr_id="+id;
				WHosrBean whb = null;
				ResultSet rs = jt.query(sql);
				try {
					while(rs.next()){
						whb = new WHosrBean(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDouble(6));
						list.add(whb);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					jt.closeRes();
				}
				return list;
	}
	
	}


	


