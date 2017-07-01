package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zrgk.bean.ChargeProjectBean;
import com.zrgk.bean.WHosrBean;
import com.zrgk.dao.ChargeProjectDao;
import com.zrgk.util.JDBCTemplate;
import com.zrgk.util.PartPage;

public class ChargeProjectDaoImpl implements ChargeProjectDao {

	JDBCTemplate jt;
	public  ChargeProjectDaoImpl() {
		jt=new JDBCTemplate();
	}
	/**
	 * 返回所有可用的收费项目集合
	 * */
	@Override
	public int getAllProject() {
		
		String sql="select count(*) from chargeproject where chap_state=0";
		ResultSet rs=jt.query(sql);
		int count=0;
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
	public ChargeProjectBean getOneChargeProject(String id) {
		String sql="select * from chargeproject where chap_id=?";
		String params[]={id};
		ResultSet rs=jt.query(sql, params);
		ChargeProjectBean cpb=null;
		try {
			while (rs.next()) {
				cpb=new ChargeProjectBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return cpb;
	}
	@Override
	public boolean editChargeProject(ChargeProjectBean cpb) {
		// TODO Auto-generated method stub
		String sql="update chargeproject set chap_name='"+cpb.getCp_name()+"'"
				+ ",chap_price="+cpb.getCp_price()+" where chap_id="+cpb.getCp_id();
		boolean b =jt.updateData(sql);
		return b;
	}
	@Override
	public boolean delteOneChargeProject(String id) {
		// TODO Auto-generated method stub
		boolean b = false;
		String sql = "update chargeproject set chap_state=1 where chap_id="+id;
		b= jt.updateData(sql);
		return b;
	}
	@Override
	public boolean insertOneChargeProject( String cpname,
			String price) {
		// TODO Auto-generated method stub
		boolean b = false;
		Date chap_time= new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time =sdf.format(chap_time);
		String sql ="insert into chargeproject values (default,'"+cpname+"',"+price+",'"+time+"',0)";
		b= jt.updateData(sql);
		return b;
	}
	@Override
	public int mohu(String name) {
		// TODO Auto-generated method stub
		
		String sql="select count(*) from chargeproject where chap_state=0 ";
		List<ChargeProjectBean> list= new ArrayList<ChargeProjectBean>();
		if(name!=null){
			sql+=" and chap_name like '%"+name+"%'";
		}
		ResultSet rs=jt.query(sql);
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
	public List<ChargeProjectBean> getAllProject(String page) {
		// TODO Auto-generated method stub
		List<ChargeProjectBean> list=new ArrayList<ChargeProjectBean>();
		int a=(Integer.parseInt(page)-1)*PartPage.pageSize;
		String sql="select * from chargeproject where chap_state=0 limit "+a+","+PartPage.pageSize;
		ResultSet rs=jt.query(sql);
		ChargeProjectBean cpb=null;
		try {
			while (rs.next()) {
				cpb=new ChargeProjectBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
				list.add(cpb);
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
	public List<ChargeProjectBean> getAllProjects() {
		// TODO Auto-generated method stub
		List<ChargeProjectBean> list=new ArrayList<ChargeProjectBean>();
		String sql="select * from chargeproject where chap_state=0 ";
		ResultSet rs=jt.query(sql);
		ChargeProjectBean cpb=null;
		try {
			while (rs.next()) {
				cpb=new ChargeProjectBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
				list.add(cpb);
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
	public List<ChargeProjectBean> mohu(String name, String page) {
		// TODO Auto-generated method stub
		int a=(Integer.parseInt(page)-1)*PartPage.pageSize;
		String sql="select * from chargeproject where chap_state=0 ";
		List<ChargeProjectBean> list= new ArrayList<ChargeProjectBean>();
		if(name!=null){
			sql+=" and chap_name like '%"+name+"%'";
		}
		sql+="limit "+a+","+PartPage.pageSize;
		ResultSet rs=jt.query(sql);
		ChargeProjectBean cpb=null;
		try {
			while(rs.next()){
				cpb=new ChargeProjectBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5));
				list.add(cpb);
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
	public int getOneCHargeProject() {
		// TODO Auto-generated method stub
		String sql="SELECT MAX(chargeproject.chap_id) FROM chargeproject ";
		int a=0;
		ResultSet rs= jt.query(sql);
		try {
			if(rs.next()){
				a=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return a+1;
	}
	@Override
	public List<String> getChargeProjectName() {
		// TODO Auto-generated method stub
		String sql="SELECT chap_name FROM chargeproject";
		ResultSet rs=jt.query(sql);
		List<String> list= new ArrayList<String>();
		try {
			while(rs.next()){
				String oname=rs.getString(1);
				list.add(oname);
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
	public List<String> getChargeProjectName(String id) {
		// TODO Auto-generated method stub
		String sql="SELECT chap_name FROM chargeproject where chap_id !="+id;
		ResultSet rs=jt.query(sql);
		List<String> list= new ArrayList<String>();
		try {
			while(rs.next()){
				String oname=rs.getString(1);
				list.add(oname);
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
