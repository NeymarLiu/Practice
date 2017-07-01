package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgk.bean.DoctorBean;
import com.zrgk.bean.HosrBean;
import com.zrgk.dao.HosregisterManager;
import com.zrgk.util.JDBCTemplate;


/**
 *
 * 挂号管理
 *
 * @author 魏洋明
 */

public class HosregisterManagerImpl implements HosregisterManager{

	JDBCTemplate jt=new JDBCTemplate();
	/**
	 * 通过查询医生信息
	 */
	
	public List<DoctorBean> getHosr(String sql) {
		// TODO Auto-generated method stub
		List<DoctorBean> list=new ArrayList<DoctorBean>();
		DoctorBean hb=null;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				hb=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
						,rs.getInt(9),rs.getString(10),rs.getString(11)
						,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
				list.add(hb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			jt.closeRes();
		}
		return list;
	}
	
	
	/**
	 * 查询挂号信息
	 */
	public List<HosrBean> getHosrInformation(String sql) {
		List<HosrBean> hosrList=new ArrayList<HosrBean>();
		HosrBean hb=null;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				hb=new HosrBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getInt(16));
				hosrList.add(hb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			jt.closeRes();
		}
		return hosrList;
	}
	

	/**
	 * 添加挂号信息
	 */
	public boolean addHosr(String sql){
		boolean a=false;
		a=jt.updateData(sql);
		return a;
	}
	
	/**
	 * sql语句拼接,模糊查询挂号信息
	 */
	public List<HosrBean> selectQuery(String name,String illid,String room,String gotimeMin,String gotimeMax,int start,int pageSize){
		List<HosrBean> hosrList=new ArrayList<HosrBean>();
		hosrList=new ArrayList<HosrBean>();
		String sql="select * from hosregister where 1=1";
		if(name != null && !"".equals(name)){
			sql+=" and d_id in(select d_id from doctor where d_name like'%"+name+"%')";
		}
		if(illid != null && !"".equals(illid)){
			sql+=" and hosr_id like '%"+illid+"%'";
		}
		if(room != null && !"".equals(room)){
			sql+=" and keshi like '%"+room+"%'";
		}
		if(gotimeMin != null && !"".equals(gotimeMin)){
			try {
				//Date gotimeone=sdf.parse(gotimeMin);
				//System.out.println(gotimeone);
				sql+=" and hosr_time>'"+gotimeMin+"'";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(gotimeMax != null && !"".equals(gotimeMax)){
			sql+=" and hosr_time<'"+gotimeMax+"'";
		}
		sql+=" order by hosr_state limit "+start+","+pageSize;
		//System.out.println(sql);
		HosrBean hb=null;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				DoctorBean db=selDoctorName(rs.getInt(13));
				hb=new HosrBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getInt(16));
				hb.setD_name(db.getD_name());
				hosrList.add(hb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return hosrList;
	}
	
	/**
	 * 与状态通道下的删除
	 * 		delete  ?
	 * @author 魏洋明
	 */
	public boolean deleteData(String[] params){
		boolean a=false;
		String sql1="";
		List<String> sql=new ArrayList<String>();
		for (int i = 0; i < params.length; i++) {
			sql1="delete from hosregister where hosr_id="+params[i];
			sql.add(sql1);
		}
		a= jt.executeBatch(sql);
		return a;
	}
	
	/**
	 * 
	 * @param 模糊查询医生信息
	 * @author weiyangming
	 */
	public List<DoctorBean> selectDoctor(String name,String code,String keshi,int start,int pageSize){
		
		List<DoctorBean> list=new ArrayList<DoctorBean>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from doctor where 1=1");
		//做字符串拼接
		if(name != null && !"".equals(name)){
			sql.append(" and d_name like '%"+name+"%'");
		}
		if(code != null && !"".equals(code)){
			sql.append(" and d_code like '%"+code+"%'");
		}
		if(keshi != null && !"".equals(keshi)){
			sql.append(" and d_keshi like '%"+keshi+"%'");
		}
		sql.append(" limit "+start+","+pageSize);
		ResultSet rs=jt.query(sql.toString());
		DoctorBean hb=null;
		try {
			while(rs.next()){
				//取出结果集中的元素
				hb=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
						,rs.getInt(9),rs.getString(10),rs.getString(11)
						,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
				list.add(hb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			jt.closeRes();
		}
		return list;
		
	}
	
public int selectCount(String name,String code,String keshi){
		
		int a=0;
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) from doctor where 1=1");
		//做字符串拼接
		if(name != null && !"".equals(name)){
			sql.append(" and d_name like '%"+name+"%'");
		}
		if(code != null && !"".equals(code)){
			sql.append(" and d_code like '%"+code+"%'");
		}
		if(keshi != null && !"".equals(keshi)){
			sql.append(" and d_keshi like '%"+keshi+"%'");
		}
		ResultSet rs=jt.query(sql.toString());
		try {
			while(rs.next()){
				a=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return a;
	}
	
	public int getCount(String sql){
		int a=-1;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				a=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return a;
		
	}

/**
 * 查询用户信息
 * 
 */
	public List<HosrBean> allhosr(int start,int pageSize) {
		List<HosrBean> hosrList=new ArrayList<HosrBean>();
		HosrBean hb=null;
		// TODO Auto-generated method stub
		String sql="select * from hosregister order by hosr_state limit "+start+","+pageSize;
		ResultSet rs=jt.query(sql);
		try {
			if(rs != null){
				while(rs.next()){
					DoctorBean db=selDoctorName(rs.getInt(13));
					hb=new HosrBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getInt(16));
					hb.setD_name(db.getD_name());
					hosrList.add(hb);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return hosrList;
	}


public HosrBean conSecHosr(int id) {
	// TODO Auto-generated method stub
	String sql="select * from hosregister where hosr_id="+id;
	HosrBean hb=null;
	ResultSet rs=jt.query(sql);
	try {
		while(rs.next()){
			hb=new HosrBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getInt(16));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		jt.closeRes();
	}
	return hb;
}


@Override
public DoctorBean selDoctorName(int doctorId) {
	// TODO Auto-generated method stub
	String sql="select * from doctor where d_id="+doctorId;
	DoctorBean db=null;
	ResultSet rs=jt.query(sql);
	try {
		while(rs.next()){
			db=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
					,rs.getInt(9),rs.getString(10),rs.getString(11)
					,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
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
public List<DoctorBean> selAllDoctorOffice() {
	// 查询所有的科室
	List<DoctorBean> list=new ArrayList<DoctorBean>();
	String sql="select * from doctor group by d_keshi";
	DoctorBean db=null;
	ResultSet rs=jt.query(sql);
	try {
		while(rs.next()){
			db=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
					,rs.getInt(9),rs.getString(10),rs.getString(11)
					,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
			list.add(db);
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
public List<DoctorBean> selAllDoctor(String office) {
	// 查询医生姓名,按科室排序
	List<DoctorBean> list=new ArrayList<DoctorBean>();
	String sql="select * from doctor where d_keshi='"+office+"'";
	DoctorBean db=null;
	ResultSet rs=jt.query(sql);
	try {
		while(rs.next()){
			db=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
					,rs.getInt(9),rs.getString(10),rs.getString(11)
					,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
			list.add(db);
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
public boolean updateHosr(String hid,String name,String idcard,String medical,String money,String phone,String self,String sex,String age,String word,String look,String keshi,int did,String desc) {
	// 修改挂号信息
	boolean a=false;
	String sql="update hosregister set hosr_name='"+name+"', hosr_idcar='"
			+idcard+"', hosr_medical='"+medical+"', hosr_regprice="+money+
			", hosr_phone='"+phone+"' ,hosr_selfprice="+self+", hosr_sex='"+sex+"', hosr_age="+age+", hosr_work='"+word+"', hosr_lookdoctor="+look+" ,keshi='"+keshi+"', d_id="+did+" ,hosr_remark='"+desc+
			"' where hosr_id="+hid;
	a=jt.updateData(sql);
	return a;
}


@Override
public DoctorBean selDoctorId(String dname) {
	// 查询医生ID
	String sql="select * from doctor where d_name='"+dname+"'";
	DoctorBean db=null;
	ResultSet rs=jt.query(sql);
	try {
		while(rs.next()){
			db=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
					,rs.getInt(9),rs.getString(10),rs.getString(11)
					,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
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
public boolean deleteOneHosr(String hid) {//挂号状态    0挂号    1已住院    2已结算     3已退号   4已出院   5已退院
	// 退某一个人的号
	String sql="update hosregister set hosr_state=3 where hosr_id="+hid;
	boolean a=false;
	a=jt.updateData(sql);
	return a;
}
	
	
	/**
	 * 模糊查询医生id
	 */

	public List<DoctorBean> sDoctorId(String name){
		List<DoctorBean> list=new ArrayList<DoctorBean>();
		if(name != null && !"".equals(name)){
			String sql="select * from doctor where d_name like'%"+name+"%'";
			DoctorBean db=null;
			ResultSet rs=jt.query(sql);
			try {
				while(rs.next()){
					db=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
							rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
							,rs.getInt(9),rs.getString(10),rs.getString(11)
							,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
					list.add(db);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
		}
		
		return list;
	}


	@Override
	public boolean plSellect(String []id) {
		// 批处理退号
		boolean boo=false;
		String sql="update hosregister set hosr_state=3 where hosr_id in(";
		for (int i = 0; i < id.length; i++) {
			if(i == id.length-1){
				sql+=id[i]+")";
			}else{
				sql+=id[i]+",";
			}
			
		}
		//System.out.println(sql);
		boo=jt.updateData(sql);
		return boo;
	}


	@Override
	public boolean addHosrInfo(String[] information) {
		// TODO Auto-generated method stub
		boolean boo=false;
		String sql="insert into hosregister values(default,";
		//循环取出数组
		for (int i = 0; i < information.length; i++) {
			
				try {
					if(i == information.length-1){
						sql+="'"+information[i]+"','2016-07-07',0)";
					}/*else if(i ==information.length-2){
						 DoctorBean db=DoctorId(information[information.length-2]);
						 if(db != null){
							 sql+=db.getD_id()+",";
						 }else{
							 sql+="'"+ -1+"',";
						 }
					
					}*/else{
						sql+="'"+information[i]+"',";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
		}
		boo=jt.updateData(sql);
		return boo;
	}

	public DoctorBean DoctorId(String name){
		DoctorBean db=null;
		if(name != null && !"".equals(name)){
			String sql="select * from doctor where d_name='"+name+"'";
			ResultSet rs=jt.query(sql);
			try {
				while(rs.next()){
					db=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
							rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
							,rs.getInt(9),rs.getString(10),rs.getString(11)
							,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
		}
		return db;
	}


	@Override
	public List<DoctorBean> selectAllDoctor() {
		// TODO Auto-generated method stub
		List<DoctorBean> list=new ArrayList<DoctorBean>();
		String sql="select * from doctor group by d_keshi";
		DoctorBean db=null;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				db=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
						,rs.getInt(9),rs.getString(10),rs.getString(11)
						,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
				list.add(db);
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
	public List<DoctorBean> slectDoctorName(String office) {
		// 通过指定科室获取相应科室下的医生
		List<DoctorBean> list=new ArrayList<DoctorBean>();
		String sql="select * from doctor where d_keshi='"+office+"'";
		DoctorBean db=null;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				db=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
						,rs.getInt(9),rs.getString(10),rs.getString(11)
						,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
				list.add(db);
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
	public boolean selHosrIdcard(String idcard) {
		// TODO Auto-generated method stub
		boolean boo=false;
		String sql="select * from hosregister where hosr_idcar='"+idcard+"'";
		ResultSet rs=jt.query(sql);
		try {
			if(rs != null && rs.next()){
				boo=false;
			}else{
				boo=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return boo;
	}


	@Override
	public List<HosrBean> putExcal(String id) {
		// TODO Auto-generated method stub
		List<HosrBean> list=new ArrayList<HosrBean>();
		HosrBean hb=null;
		String sql="select * from hosregister where hosr_id in("+id+")";
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				hb=new HosrBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getInt(11),rs.getString(12),rs.getInt(13),rs.getString(14),rs.getString(15),rs.getInt(16));
				list.add(hb);
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
	public boolean selDoctor(String idcard) {
		// TODO Auto-generated method stub
		boolean boo=false;
		String card[]={};
		String sql="";
		if(idcard !=null || "".equals(idcard)){
			card=idcard.split(",");
			sql="select * from doctor where d_idcar in(";
			for (int i = 0; i < card.length; i++) {
				if(i==card.length-1){
					sql+="'"+card[i]+"'";
				}else{
					sql+="'"+card[i]+"',";
				}
			}
			sql+=")";
			ResultSet rs=jt.query(sql);
			try {
				if(rs != null && rs.next()){
					boo=false;
				}else{
					boo=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
		}else{
			boo=false;
		}
		
		return boo;
	}
	
	public List<DoctorBean> putExcalDoctor(String id) {
		// TODO Auto-generated method stub
		List<DoctorBean> list=new ArrayList<DoctorBean>();
		DoctorBean hb=null;
		String sql="select * from doctor where d_id in("+id+")";
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				hb=new DoctorBean(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)
						,rs.getInt(9),rs.getString(10),rs.getString(11)
						,rs.getString(12),rs.getString(13),rs.getString(14),rs.getInt(15));
				list.add(hb);
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
	public int selectHosrCount() {
		// TODO Auto-generated method stub
		int count=0;
		String sql="select count(*) from hosregister";
		ResultSet rs=jt.query(sql);
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
	public int selHosrCountLike(String hid, String name, String keshi,
			String mTimeMin, String mTimeMax) {
		// TODO Auto-generated method stub
		int count=0;
		String sql="select count(*) from hosregister where 1=1";
		if(name != null && !"".equals(name)){
			sql+=" and d_id in(select d_id from doctor where d_name like'%"+name+"%')";
		}
		if(hid != null && !"".equals(hid)){
			sql+=" and hosr_id like '%"+hid+"%'";
		}
		if(keshi != null && !"".equals(keshi)){
			sql+=" and keshi like '%"+keshi+"%'";
		}
		if(mTimeMin != null && !"".equals(mTimeMin)){
			try {
				//Date gotimeone=sdf.parse(gotimeMin);
				//System.out.println(gotimeone);
				sql+=" and hosr_time>'"+mTimeMin+"'";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(mTimeMax != null && !"".equals(mTimeMax)){
			sql+=" and hosr_time<'"+mTimeMax+"'";
		}
		//System.out.println(sql);
		ResultSet rs=jt.query(sql);
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
	
}
