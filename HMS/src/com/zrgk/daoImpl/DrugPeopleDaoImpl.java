package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgk.bean.DrugPage;
import com.zrgk.bean.DrugPeopleBean;
import com.zrgk.dao.DrugPeopleDao;
import com.zrgk.util.JDBCTemplate;

public class DrugPeopleDaoImpl implements DrugPeopleDao {
	JDBCTemplate jt;
	public DrugPeopleDaoImpl(){
		jt=new JDBCTemplate();
	}

	@Override
	public int getAllDrugPeople(String beh_id, String hosr_name){
		int count=0;
		String sql="SELECT count(*) FROM hosregister ho "
				+"INNER JOIN behospital beh ON beh.beh_id = ho.hosr_id "
				+"INNER JOIN doctor d ON ho.d_id = d.d_id where beh.beh_closeprice=0 and 1=1";
		if (beh_id!=null && beh_id!="") {
			sql+=" and ho.hosr_id like '%"+beh_id+"%'";
		}
		if (hosr_name!=null && hosr_name!="") {
			sql+=" and ho.hosr_name like '%"+hosr_name+"%'";
		} 
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return count;
	}
	public int lookDrugPeople(String id){
		int count=0;
		String sql="SELECT count(*) FROM drugpeople dr INNER JOIN behospital beh ON dr.beh_id = beh.beh_id "
				+"INNER JOIN hosregister ho ON beh.beh_id = ho.hosr_id "
				+"INNER JOIN drug ON dr.dr_id = drug.dr_id where beh.beh_closeprice=0 and ho.hosr_id="+id;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return count;
	}
	@Override
	public List<DrugPeopleBean> lookNowpageDrugPeople(int nowpage,String id) {
		List<DrugPeopleBean> dpblist=new ArrayList<DrugPeopleBean>();
		String sql="SELECT ho.hosr_id,dr.dr_id,dr.dr_number,dr.dr_hadsent,"
				+"dr.dr_notsent,ho.hosr_name,drug.dr_name FROM drugpeople dr INNER JOIN behospital beh ON dr.beh_id = beh.beh_id "
				+"INNER JOIN hosregister ho ON beh.beh_id = ho.hosr_id "
				+"INNER JOIN drug ON dr.dr_id = drug.dr_id where beh.beh_closeprice=0 and ho.hosr_id="+id;
		sql +=" limit "+(nowpage-1)*DrugPage.getSize()+" ,"+DrugPage.getSize() ;
		ResultSet rs=jt.query(sql);
		try {
			while (rs.next()) {
				DrugPeopleBean dpb=new DrugPeopleBean(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7));
				dpblist.add(dpb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return dpblist;
	}

	@Override
	public List<DrugPeopleBean> getNowpageDrugPeople(int nowpage,String beh_id, String hosr_name) {
		List<DrugPeopleBean> dpblist=new ArrayList<DrugPeopleBean>();
		String sql="SELECT ho.hosr_id,ho.hosr_name,d.d_name FROM hosregister ho "
				+"INNER JOIN behospital beh ON beh.beh_id = ho.hosr_id "
				+"INNER JOIN doctor d ON ho.d_id = d.d_id where beh.beh_closeprice=0 and 1=1";
		if (beh_id!=null && beh_id!="") {
			sql+=" and ho.hosr_id like '%"+beh_id+"%'";
		}
		if (hosr_name!=null && hosr_name!="") {
			sql+=" and ho.hosr_name like '%"+hosr_name+"%'";
		} 
		sql +=" limit "+(nowpage-1)*DrugPage.getSize()+" ,"+DrugPage.getSize() ;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				DrugPeopleBean dpb=new DrugPeopleBean(rs.getInt(1),rs.getString(2),rs.getString(3));
				dpblist.add(dpb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return dpblist;
	}

	@Override
	public List<DrugPeopleBean> giveDrugChooce(String beh_id) {
		List<DrugPeopleBean> dpblist=new ArrayList<DrugPeopleBean>();
		String sql="SELECT ho.hosr_id,drug.dr_id,dr.dr_number,dr.dr_hadsent,"
				+"dr.dr_notsent,drug.dr_name,ho.hosr_name FROM drugpeople dr INNER JOIN behospital beh ON dr.beh_id = beh.beh_id "
				+"INNER JOIN hosregister ho ON beh.beh_id = ho.hosr_id "
				+"INNER JOIN drug ON dr.dr_id = drug.dr_id where beh.beh_closeprice=0 and ho.hosr_id="+beh_id;
			ResultSet rs=jt.query(sql);
			try {
				while (rs.next()) {
					DrugPeopleBean dpb=new DrugPeopleBean(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7));
					dpblist.add(dpb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
		return dpblist;
	}
	//得到药品售价
	public double getOutPrise(String dr_id) {
		double outprise = 0;
		String sql = "select dr_outprice from drug where dr_id="+dr_id;
		try {
			ResultSet rs = jt.query(sql);
			while(rs.next()){
			outprise = rs.getDouble(1);
				}
			} catch (SQLException e) {
					e.printStackTrace();
			}finally{
					jt.closeRes();
			}
				return outprise;
			}
	//得到病人未发数量
	public double getDrugNumber(String beh_id, String dr_id) {
		double dr_notsent = 0;
		String sql = "select dr_notsent from drugpeople where beh_id in("+beh_id+") and dr_id='"+dr_id+"' and dr_notsent>0 order by dr_notsent limit 0,1";
		try {
			ResultSet rs = jt.query(sql);
			while(rs.next()){
						dr_notsent = rs.getInt(1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					jt.closeRes();
				}
				return dr_notsent;
			}
	//得到病人押金余额
	public double getLeftmoney(String beh_id) {
		double leftmoney = 0;
		String sql = "select beh_leftmoney from behospital where beh_id="+beh_id;
		try {
			ResultSet rs = jt.query(sql);
			while(rs.next()){
				leftmoney = rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
			}
		return leftmoney;
		}
			//1
			//发药之后，修改余额
	public boolean updateLeftmoney(int beh_id, double beh_leftmoney) {
		boolean b = false;
		String sql = "update behospital set beh_leftmoney="+beh_leftmoney+" where beh_id="+beh_id;
			try {
				b = jt.updateData(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
			return b;
			}
	//修改病人住院药品已发未发资料
	public boolean updateDrugPeople(DrugPeopleBean dpb,int number) {
		String sql = "update drugpeople set dr_hadsent=dr_hadsent+"+number+",dr_notsent=dr_notsent-"+number+",dr_time='"+dpb.getDr_time()+"' where beh_id="+dpb.getBeh_id()+" and dr_id="+dpb.getDr_id();
		boolean b = false;
		try {
			b= jt.updateData(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
			}
		return b;
		}
	//修改库存
	public boolean updateDrugNumber(String dr_id,int number){
		boolean b = false;
		String sql = "update drug set dr_number=dr_number-"+number+" where dr_id="+dr_id;
		try {
			b= jt.updateData(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
			}
		return b;
	}
	//得到药品数量
	public int getDrugHadNumber(int dr_id) {
		int drugNumber = 0;
		String sql = "select dr_number from drug where dr_id="+dr_id+"";
		try {
			ResultSet rs = jt.query(sql);
			while(rs.next()){
			drugNumber = rs.getInt(1);
			}
			} catch (SQLException e) {
			e.printStackTrace();
			}finally{
			jt.closeRes();
			}
			return drugNumber;
	}
	public List<DrugPeopleBean> givesDrugChooce(String id){
		String ids[]=id.split(",");
		String sql="SELECT dr_id,dr_name FROM drug WHERE dr_id IN (SELECT dr_id FROM drugpeople WHERE beh_id IN ("+id+") GROUP BY dr_id HAVING COUNT(*)="+ids.length+")";
		List<DrugPeopleBean> names=new ArrayList<DrugPeopleBean>();
		try {
			ResultSet rs = jt.query(sql);
			while(rs.next()){
				DrugPeopleBean dpb=new DrugPeopleBean(rs.getInt(1),rs.getString(2));
				names.add(dpb);
			}
			} catch (SQLException e) {
			e.printStackTrace();
			}finally{
			jt.closeRes();
			}
		return names;
	}
	
}
