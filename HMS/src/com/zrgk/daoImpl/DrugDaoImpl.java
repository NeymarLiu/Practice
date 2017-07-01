package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgk.bean.DrugBean;
import com.zrgk.bean.DrugPage;
import com.zrgk.dao.DrugDao;
import com.zrgk.util.JDBCTemplate;

public class DrugDaoImpl implements DrugDao {
	JDBCTemplate jt;
	public DrugDaoImpl(){
		jt=new JDBCTemplate();
	}
	@Override
	public List<DrugBean> getNowPageDrug(int nowpage,String dr_name,String dr_type) {
		// TODO Auto-generated method stub
		List<DrugBean> dr_list=new ArrayList<DrugBean>();
		String sql="SELECT * FROM drug  where 1=1";
		if (dr_name!=null && dr_name!="") {
			sql+=" and drug.dr_name like '%"+dr_name+"%'";
		}
		if (dr_type!=null && dr_type!="") {
			if("1".equals(dr_type)){
				dr_type="中药";
			}else if("2".equals(dr_type)){
				dr_type ="西药";
			}else if("3".equals(dr_type)){
				dr_type = "处方";
			}else if("4".equals(dr_type)){
				dr_type ="非处方";
			}else{
				dr_type ="";
			}
			sql+=" and drug.dr_type like '%"+dr_type+"%'";
		} 
		sql +=" limit "+(nowpage-1)*DrugPage.getSize()+" ,"+DrugPage.getSize() ;
		ResultSet rs=jt.query(sql);
		String contend="";
		try {
			while (rs.next()) {
				if(rs.getString(7)!=null&&rs.getString(7).length()>18){
					contend=rs.getString(7).substring(0,18);
				}else{
					contend=rs.getString(7);
				}
				DrugBean db=new DrugBean(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getDouble(4),rs.getDouble(5),
						rs.getString(6),contend,rs.getInt(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getInt(13),rs.getInt(14));
				dr_list.add(db);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return dr_list;
	}
	
	@Override
	public DrugBean lookOneDrug(String id) {
		// TODO Auto-generated method stub
		DrugBean db=null;
		String sql="SELECT * FROM drug where dr_id="+id;
		ResultSet rs=jt.query(sql);
		try {
			while (rs.next()) {
				 db=new DrugBean(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getDouble(4),rs.getDouble(5),
						rs.getString(6),rs.getString(7),rs.getInt(8),
						rs.getString(9),rs.getString(10),rs.getString(11),
						rs.getString(12),rs.getInt(13),rs.getInt(14));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return db;
	}
	@Override
	public int getAllDrug(String dr_name,String dr_type) {
		// TODO Auto-generated method stub
		int count=0;
		String sql="SELECT count(*) FROM drug  where 1=1";
		if (dr_name!=null && dr_name!="") {
			sql+=" and drug.dr_name like '%"+dr_name+"%'";
		}
		
		if (dr_type!=null && dr_type!="") {
			if("1".equals(dr_type)){
				dr_type="中药";
			}else if("2".equals(dr_type)){
				dr_type ="西药";
			}else if("3".equals(dr_type)){
				dr_type = "处方";
			}else if("4".equals(dr_type)){
				dr_type ="非处方";
			}
			sql+=" and drug.dr_type like '%"+dr_type+"%'";
		} 
		try {
			ResultSet rs=jt.query(sql);
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
	public boolean changeDrug(String id,int number) {
		// TODO Auto-generated method stub
		boolean b=false;
		String sql="update drug set dr_number=dr_number+"+number+" where dr_id="+id;
		b=jt.updateData(sql);
		return b;
	}
	@Override
	public boolean addDrug(List<String> lists) {
		// TODO Auto-generated method stub
		boolean b=false;
		String sql= "insert into drug values("+lists.get(0)+",'"+lists.get(1)+"','"+lists.get(2)+"',"+lists.get(3)+","+lists.get(4)+",'"+lists.get(5)+"','"+lists.get(6)+"',"
					+lists.get(7)+",'"+lists.get(8)+"','"+lists.get(9)+"','"+lists.get(10)+"','"+lists.get(11)+"','0','0')";
		b=jt.updateData(sql);
		return b;
	}
	@Override
	public boolean editDrug(List<String> lists) {
		// TODO Auto-generated method stub
		boolean b=false;
			String sql="update drug set dr_name='"+lists.get(5)+"', dr_url='"+lists.get(2)+"', dr_inprice="
					+lists.get(3)+", dr_outprice="+lists.get(4)+", dr_type='"+lists.get(6)
					+"', dr_simdesc='"+lists.get(7)+"', dr_savetime="+lists.get(8)+", dr_desc='"
					+lists.get(9)+"', dr_factory='"+lists.get(10)+"', dr_direction='"+lists.get(11)
					+"', dr_remark='"+lists.get(12)+"', dr_state="+lists.get(1)+" where dr_id="+lists.get(0);
		
		b=jt.updateData(sql);
		return b;
	}
	
}
