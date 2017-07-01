package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgk.bean.LBeHospBean;
import com.zrgk.bean.LHosrBean;
import com.zrgk.bean.LCanShu;
import com.zrgk.bean.LMoHu;
import com.zrgk.dao.LBeHospDao;
import com.zrgk.util.JDBCTemplate;

public class LBeHospDaoImpl implements LBeHospDao {

	JDBCTemplate jt;

	public LBeHospDaoImpl() {
		jt = new JDBCTemplate();
	}

	// 得到所有的住院信息
	public List<LCanShu> getAllHosr(String nowPage) {
		// sql
		int a=(Integer.parseInt(nowPage)-1)*5;
		String sql = "SELECT beh_id,hosr_name,beh_patbed,hosr_phone,beh_antecedent,d_name,beh_time,keshi,hosr_state "
				+ "FROM behospital be  LEFT JOIN hosregister ho ON ho.hosr_id=be.beh_id "
				+ "LEFT JOIN doctor d ON ho.d_id=d.d_id WHERE hosr_state IN (1,2,4,5) limit "
				+a+",5";
		List<LCanShu> list = new ArrayList<LCanShu>();
		ResultSet rs = jt.query(sql);
		try {
			while (rs.next()) {
				LCanShu bhb = new LCanShu(rs.getInt(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getInt(9));
				list.add(bhb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	// 得到所有的住院信息
		public List<LCanShu> getAllHosr() {
			// sql
			String sql = "SELECT beh_id,hosr_name,beh_patbed,hosr_phone,beh_antecedent,d_name,beh_time,keshi,hosr_state "
					+ "FROM behospital be  LEFT JOIN hosregister ho ON ho.hosr_id=be.beh_id "
					+ "LEFT JOIN doctor d ON ho.d_id=d.d_id WHERE hosr_state IN (1,2,4,5) ";
			List<LCanShu> list = new ArrayList<LCanShu>();
			ResultSet rs = jt.query(sql);
			try {
				while (rs.next()) {
					LCanShu bhb = new LCanShu(rs.getInt(1), rs.getString(2),
							rs.getInt(3), rs.getString(4), rs.getDouble(5),
							rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getInt(9));
					list.add(bhb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
			return list;
		}
	// 通过病例号查询相关病人信息
	public LHosrBean getOneInfos(String id) {
		// sql
		String sql = "SELECT beh_id,hosr_name,hosr_idcar,hosr_medical,hosr_phone,hosr_selfprice,hosr_age,"
				+ "hosr_sex,hosr_work,hosr_lookdoctor,keshi,d_name,beh_leftmoney,hosr_remark,beh_nursepeople,beh_patbed,beh_illness "
				+ "FROM behospital be  RIGHT JOIN hosregister ho ON ho.hosr_id=be.beh_id "
				+ "LEFT JOIN doctor d ON ho.d_id=d.d_id WHERE beh_id=" + id;
		LHosrBean hb = null;
		ResultSet rs = jt.query(sql);
		try {
			while (rs.next()) {
				hb = new LHosrBean(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getString(8),
						rs.getString(9), rs.getInt(10), rs.getString(11),
						rs.getString(12), rs.getDouble(13), rs.getString(14),
						rs.getString(15), rs.getShort(16), rs.getString(17));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}

		return hb;
	}

	// 通过id修改相关病人的数据
	public boolean updateOneBe(LBeHospBean bhb) {
		String sql = "UPDATE behospital SET beh_nursepeople='"
				+ bhb.getBeh_nursepeople() + "',beh_patbed="
				+ bhb.getBeh_patbed() + ",beh_illness='" + bhb.getBeh_illness()
				+ "' WHERE beh_id=" + bhb.getBeh_id();
		boolean a = jt.updateData(sql);
		jt.closeRes();
		return a;
	}

	// 通过id病历号查修病人的一部分信息
	public LCanShu getOneMany(String id) {
		String sql = "SELECT hosr_id,hosr_name,hosr_idcar,beh_antecedent,beh_leftmoney FROM hosregister ho LEFT JOIN behospital be ON be.beh_id=ho.hosr_id WHERE hosr_id="
				+ id;
		ResultSet rs = jt.query(sql);
		LCanShu lc = null;
		try {
			while (rs.next()) {
				lc = new LCanShu(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getDouble(4), rs.getDouble(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return lc;
	}

	// 通过病例号修改押金
	public boolean editMany(String id, double money) {
		String sql = "UPDATE behospital SET beh_antecedent=beh_antecedent+"
				+ money + ",beh_leftmoney=beh_leftmoney+" + money
				+ " WHERE beh_id=" + id;
		boolean b = jt.updateData(sql);
		jt.closeRes();
		return b;
	}

	// 通过病例号得到病人的相关信息
	public List<LHosrBean> addOne(String id) {
		String sql = "SELECT hosr_id,hosr_name,hosr_idcar,hosr_medical,hosr_regprice,"
				+ "hosr_phone,hosr_selfprice,hosr_age,hosr_sex,hosr_work,hosr_lookdoctor,"
				+ "keshi,d_name,hosr_remark,hosr_state FROM hosregister ho"
				+ " LEFT JOIN doctor d ON ho.d_id=d.d_id WHERE hosr_state=0 and hosr_id like '%"+id+"%'";
		ResultSet rs = jt.query(sql);
		List<LHosrBean> list = new ArrayList<LHosrBean>();
		try {
			while (rs.next()) {
				LHosrBean hb = new LHosrBean(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getDouble(5),
						rs.getString(6), rs.getInt(7), rs.getString(9),
						rs.getInt(8), rs.getString(10), rs.getInt(11),
						rs.getString(12), rs.getString(13), rs.getString(14),
						rs.getInt(15));
				list.add(hb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}

	//通过病例号出院
	public boolean chuyuan(String id) {
		boolean b=false;
		String sql="UPDATE hosregister,behospital SET hosr_state=4,beh_state=4 WHERE hosr_id in"+id +" and beh_id in"+id;
		b=jt.updateData(sql);
		jt.closeRes();
		return b;
	}

	//通过病例号退院
	public boolean tuiyuan(String id) {
		boolean b=false;
		String sql="UPDATE hosregister,behospital SET hosr_state=5,beh_state=5  WHERE hosr_id in"+id+" and beh_id in"+id;
		b=jt.updateData(sql);
		jt.closeRes();
		return b;
	}

	//模糊查询
	@Override
	public List<LCanShu> moHu(LMoHu lm) {
		String sql = "SELECT beh_id,hosr_name,beh_patbed,hosr_phone,beh_antecedent,d_name,beh_time,keshi,hosr_state "
				+ "FROM behospital be  LEFT JOIN hosregister ho ON ho.hosr_id=be.beh_id "
				+ "LEFT JOIN doctor d ON ho.d_id=d.d_id WHERE hosr_state IN (1,2,4,5)";
		if(lm.getId() != null && !"".equals(lm.getId())){
			sql+=" and beh_id like '%"+lm.getId()+"%'";
		}
		if(lm.getDor() != null && !"".equals(lm.getDor())){
			sql+=" and d_name like '%"+lm.getDor()+"%'";
		}
		if(lm.getKeshi() != null && !"".equals(lm.getKeshi())){
			sql+=" and keshi like '%"+lm.getKeshi()+"%'";
		}
		if(lm.getQtime() !=null && !"".equals(lm.getQtime())){
			sql+= " and beh_time >= '"+lm.getQtime()+"'";
		}
		if(lm.getHtime() !=null &&  !"".equals(lm.getHtime())){
			sql+= " and beh_time <= '"+lm.getHtime()+"'";
		}
		
		List<LCanShu> list = new ArrayList<LCanShu>();
		ResultSet rs = jt.query(sql);
		try {
			while (rs.next()) {
				LCanShu bhb = new LCanShu(rs.getInt(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getInt(9));
				list.add(bhb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	//模糊查询
		@Override
		public List<LCanShu> moHu(LMoHu lm,String page) {
			int a=(Integer.parseInt(page)-1)*5;
			String sql = "SELECT beh_id,hosr_name,beh_patbed,hosr_phone,beh_antecedent,d_name,beh_time,keshi,hosr_state "
					+ "FROM behospital be  LEFT JOIN hosregister ho ON ho.hosr_id=be.beh_id "
					+ "LEFT JOIN doctor d ON ho.d_id=d.d_id WHERE hosr_state IN (1,2,4,5) ";
			if(lm.getId() != null && !"".equals(lm.getId())){
				sql+=" and beh_id like '%"+lm.getId()+"%'";
			}
			if(lm.getDor() != null && !"".equals(lm.getDor())){
				sql+=" and d_name like '%"+lm.getDor()+"%'";
				
			}
			if(lm.getKeshi() != null && !"".equals(lm.getKeshi())){
				sql+=" and keshi like '%"+lm.getKeshi()+"%'";
			}
			if(lm.getQtime() !=null && !"".equals(lm.getQtime())){
				sql+= " and beh_time >= '"+lm.getQtime()+" 00:00:00"+"'";
			}
			if(lm.getHtime() !=null &&  !"".equals(lm.getHtime())){
				sql+= " and beh_time <= '"+lm.getHtime()+" 23:59:59"+"' ";
			}
			sql+="limit "+a+",5";
			List<LCanShu> list = new ArrayList<LCanShu>();
			ResultSet rs = jt.query(sql);
			try {
				while (rs.next()) {
					LCanShu bhb = new LCanShu(rs.getInt(1), rs.getString(2),
							rs.getInt(3), rs.getString(4), rs.getDouble(5),
							rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getInt(9));
					list.add(bhb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
			return list;
		}

	//通过病历号添加住院信息
	@Override
	public boolean tianJia(LBeHospBean lbb) {
		String sql="insert into behospital values('"+lbb.getBeh_id()+"','"+lbb.getBeh_nursepeople()+"','"+lbb.getBeh_patbed()+"','"+lbb.getBeh_antecedent()+"','"+lbb.getBeh_leftmoney()+"','"+lbb.getBeh_illness()+"','"+lbb.getBeh_time()+"',0,1)";
		boolean b1= jt.updateData(sql);
		String sql1="update hosregister ho set hosr_state=1 where hosr_id="+lbb.getBeh_id();
		boolean b2=jt.updateData(sql1);
		boolean b=false;
		if(b2&&b1){
			b=true;
		}
		return b;
	}

	//根据病历号得到响应的数据
	@Override
	public LCanShu getSomeInfo(String id) {
		String sql = "SELECT beh_id,hosr_name,beh_patbed,hosr_phone,beh_antecedent,d_name,beh_time,keshi,hosr_state "
				+ "FROM behospital be  LEFT JOIN hosregister ho ON ho.hosr_id=be.beh_id "
				+ "LEFT JOIN doctor d ON ho.d_id=d.d_id WHERE hosr_state IN (1,2,4,5) and beh_id ="+id;
		
		ResultSet rs = jt.query(sql);
		LCanShu bhb=null;
		try {
			while (rs.next()) {
				bhb = new LCanShu(rs.getInt(1), rs.getString(2),
						rs.getInt(3), rs.getString(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return bhb;
	}

	//通过病例号查询该人是否住过院
	@Override
	public boolean selectOne(String id) {
		boolean b=false;
		int a=0;
		String sql="select count(*) from behospital where beh_id="+id;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				a=rs.getInt(1);
				if(a>0){
					b=true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return b;
	}


	//通过病例号查询该人是否住过院
	@Override
	public boolean updateOne(LBeHospBean lbb) {
		String sql = "UPDATE behospital be,hosregister ho SET beh_nursepeople='"
				+ lbb.getBeh_nursepeople() + "',beh_patbed="
				+ lbb.getBeh_patbed() + ",beh_illness='" + lbb.getBeh_illness()
				+"',beh_time='"+lbb.getBeh_time()+"',beh_antecedent="+lbb.getBeh_antecedent()
				+",beh_leftmoney="+lbb.getBeh_leftmoney()+",beh_state=1,beh_closeprice=0"
				+",hosr_state=1"
				+ " WHERE be.beh_id=" + lbb.getBeh_id()+" and ho.hosr_id="+lbb.getBeh_id();
		boolean a = jt.updateData(sql);
		jt.closeRes();
		return a;
	}

	//查询所有的住院的床位号
	@Override
	public List<LCanShu> getAllPbed(String cid) {
		List<LCanShu> list= new ArrayList<LCanShu>();
		
		String sql="select beh_id,beh_patbed from behospital where beh_state in(1,2) and beh_id !="+cid;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				LCanShu lc=new LCanShu(rs.getInt(1), rs.getInt(2));
				list.add(lc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//查询所有的住院的床位号
		@Override
		public List<LCanShu> getAllPbed() {
			List<LCanShu> list= new ArrayList<LCanShu>();
			
			String sql="select beh_id,beh_patbed from behospital where beh_state in(1,2) ";
			ResultSet rs=jt.query(sql);
			try {
				while(rs.next()){
					LCanShu lc=new LCanShu(rs.getInt(1), rs.getInt(2));
					list.add(lc);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		}
}
