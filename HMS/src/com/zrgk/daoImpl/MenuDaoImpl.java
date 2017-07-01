package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;














import com.zrgk.bean.MenuBean;
import com.zrgk.dao.MenuDao;
import com.zrgk.util.JDBCTemplate;
import com.zrgk.util.PartPage;

public class MenuDaoImpl implements MenuDao{
	JDBCTemplate jt;
	public MenuDaoImpl() {
		jt=new JDBCTemplate();
	}
	/**
	 * 根据用户ID返回其拥有的菜单
	 * */
	@Override
	public List<MenuBean> getMenu(int id) {
		List<MenuBean> list=new ArrayList<MenuBean>();
		String sql="SELECT  m.* FROM   USER u  INNER  JOIN  role  r  ON  u.u_role = r.r_id  JOIN  role_menu_ralation rm ON r.r_id = rm.r_id  JOIN  menu m  ON  rm.m_id =  m.m_id WHERE  u.u_id =? and m.m_state!=?";
		String params[]={id+"",2+""};
		ResultSet rs=jt.query(sql, params);
		try {
			while(rs.next()){
				//添加menubean对象
				list.add(new MenuBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();//关闭资源
			
		}
		return list;
	}
	/**
	 * 得到所有可用的菜单
	 * */
	@Override
	public List<MenuBean> getMenuList(String page) {
		List<MenuBean> list=new ArrayList<MenuBean>();
		int start=(Integer.parseInt(page)-1)*PartPage.pageSize;
		String sql="select * from menu where m_state!=2 limit "+start+","+PartPage.pageSize;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				//添加menubean对象
				list.add(new MenuBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();//关闭资源
			
		}
		return list;
	}
	
	/**
	 * 根据菜单id查询记录
	 * */
	@Override
	public MenuBean getMenuInfo(String mid) {
		String sql="select * from menu where m_id=?";
		String params[]={mid};
		ResultSet rs=jt.query(sql, params);
		MenuBean mb=null;
		try {
			while(rs.next()){
				mb=new MenuBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return mb;
	}
	
	/**
	 * 根据菜单id更新菜单资源
	 * */
	@Override
	public boolean updateMenuInfo(String mid, String mname, String murl,
			String mstate) {
		String sql="update menu set m_name=?,m_url=?,m_state=? where m_id="+mid;
		String params[]={mname,murl,mstate};
		boolean b=jt.updateData(sql, params);
		return b;
	}
	@Override
	public int getAllMenus(String mname, String mid) {
		String sql="";
		if(mid!=null){
			sql="select count(*) from menu where m_name ='"+mname+"' and m_id !="+mid;
		}else {
			sql="select count(*) from menu where m_name ='"+mname+"'";	
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
	/**
	 * 删除一个或多个菜单
	 * */
	@Override
	public boolean deleteMenu(String []mid) {
		List<String>list =new ArrayList<String>();
		for (int i = 0; i < mid.length; i++) {
			//将菜单删除
			list.add("update menu set m_state=2 where m_id ="+mid[i]);
		}
		boolean b=jt.executeBatch(list);
		return b;
	}
	
	/**
	 * 添加菜单
	 * */
	@Override
	public boolean insertMenu(String mname, String murl, String mstate) {
		String sql="insert into menu values(default,?,?,?)";
		String params[]={mname,mstate,murl};
		boolean b=jt.updateData(sql,params);
		return b;
	}
	@Override
	public List<MenuBean> getMenuList(String mname,String page) {
		List<MenuBean> list=new ArrayList<MenuBean>();
		int start=(Integer.parseInt(page)-1)*PartPage.pageSize;
		String sql="SELECT * from menu where m_state!=2 and m_name like '%"+mname+"%' limit "+start+","+PartPage.pageSize;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				//添加menubean对象
				list.add(new MenuBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();//关闭资源
			
		}
		return list;
	}
	/**
	 * 得到结果集总数
	 * */
	@Override
	public int getCount(String mname) {
		String sql="";
		int count=0;
		if(mname==""){
			sql="select count(*) from menu where m_state!=2";
		}else {
			sql="select count(*) from menu where m_state!=2 and m_name like '%"+mname+"%'";
		}
		ResultSet rs=jt.query(sql);
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
	

}
