package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgk.bean.MenuBean;
import com.zrgk.bean.RoleBean;
import com.zrgk.dao.RoleDao;
import com.zrgk.util.JDBCTemplate;
import com.zrgk.util.PartPage;

public class RoleDaoImpl implements RoleDao {
	JDBCTemplate jt;
	public RoleDaoImpl() {
		jt=new JDBCTemplate();
	}
	/**
	 * 返回所有角色信息
	 * */
	@Override
	public List<RoleBean> getAllRoles(String page) {
		int start=(Integer.parseInt(page)-1)*PartPage.pageSize;
		List<RoleBean> list=new ArrayList<RoleBean>();
		String sql="select * from role where r_id!=34 and r_state!=2 limit "+start+","+PartPage.pageSize;
		ResultSet rs=jt.query(sql);//得到所有角色结果集
		try {
			while(rs.next()){//组装
				list.add(new RoleBean(rs.getInt(1),rs.getString(2),rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	/**
	 * 返回所有角色信息
	 * */
	@Override
	public List<RoleBean> getAllRoles() {
		List<RoleBean> list=new ArrayList<RoleBean>();
		String sql="select * from role where r_id!=34 and r_state!=2 ";
		ResultSet rs=jt.query(sql);//得到所有角色结果集
		try {
			while(rs.next()){//组装
				list.add(new RoleBean(rs.getInt(1),rs.getString(2),rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	/**
	 * 根据角色id返回对应的菜单信息
	 * */
	@Override
	public List<MenuBean> getRoleMenu(String rid) {
		List<MenuBean> list=new ArrayList<MenuBean>();
		String sql="SELECT menu.* FROM menu INNER JOIN role_menu_ralation rm ON rm.m_id=menu.m_id INNER JOIN role ON rm.r_id=role.r_id WHERE role.r_id=? AND m_state=?";
		String params[]={rid,0+""};
		ResultSet rs=jt.query(sql, params);
		try{
			while(rs.next()){
			list.add(new MenuBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		
		return list;
	}
	/**
	 * 返回所有的菜单信息
	 * */
	@Override
	public List<MenuBean> getRoleMenu() {
		List<MenuBean> list=new ArrayList<MenuBean>();
		String sql="SELECT * FROM menu WHERE m_state=?";
		String params[]={0+""};
		ResultSet rs=jt.query(sql, params);
		try{
			while(rs.next()){
			list.add(new MenuBean(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		
		return list;
	}
	/**
	 * 返回对应角色的信息
	 * */
	@Override
	public RoleBean getRoleInfo(String rid) {
		RoleBean rb = null;
		String sql="select * from role where r_id=?";
		String params[]={rid};
		ResultSet rs=jt.query(sql,params);
		try {
			while(rs.next()){
				rb=new RoleBean(rs.getInt(1),rs.getString(2),rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return rb;
	}
	/**
	 * 更改对应角色的信息及菜单资源
	 * */
	@Override
	public boolean changeMenu(String rid, String rname, String rstate,
			String[] menu) {
		List<String>list=new ArrayList<String>();
		String sql="";
		if("1".equals(rstate)){
			sql="update user set u_state=1 where u_role="+rid;
			list.add(sql);
		}else if("0".equals(rstate)){
			sql="update user set u_state=0 where u_role="+rid;
			list.add(sql);
		}
		sql="update role set r_name='"+rname+"',r_state="+rstate+" where r_id="+rid;
		list.add(sql);
		sql="delete from role_menu_ralation where r_id="+rid;
		list.add(sql);
		
		if (!"".equals(menu[0])) {//当有菜单被选择时
			for (int i = 0; i < menu.length; i++) {
				sql="insert into role_menu_ralation values("+rid+","+menu[i]+")";
				list.add(sql);
			}
		}
		boolean b=jt.executeBatch(list);
		
		return b;
	}
	/**
	 *根据角色姓名查询是否存在 
	 * */
	@Override
	public int getAllRoles(String rname,String rid) {
		String sql = null;
		if (rid!=null) {
			sql="select count(*) from role where r_name ='"+rname+"' and r_id !="+rid+" and r_state!=2";
		}else {
			sql="select count(*) from role where r_name ='"+rname+"' and r_state!=2";
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
	/**根据角色id删除对应角色*/
	@Override
	public boolean deleteRole(String []rid) {
		List<String>list =new ArrayList<String>();
		for (int i = 0; i < rid.length; i++) {
			//将角色删除
			list.add("update role set r_state=2 where r_id ="+rid[i]);
			//将此角色下的用户删除
			list.add("update user set u_state=2 where u_role="+rid[i]);
		}
		boolean b=jt.executeBatch(list);
		return b;
	}
	/**
	 * 添加角色及菜单
	 * */
	@Override
	public boolean insertRole(String rname,String rstate,String[] menu) {
		String sql="insert into role values(default,'"+rname+"',"+rstate+")";
		boolean b=jt.updateData(sql);
		if (b==true) {
			sql="select r_id from role where r_name='"+rname+"'";
			ResultSet rs=jt.query(sql);
			int rid=0;
			try {
				if (rs.next()) {
					rid=rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				jt.closeRes();
			}
			if (!"".equals(menu[0])) {
				List<String>list=new ArrayList<String>();
				for (int i = 0; i < menu.length; i++) {
					sql="insert into role_menu_ralation values("+rid+","+menu[i]+")";
					list.add(sql);
				}
				b=jt.executeBatch(list);
			}
		}
		return b;
	}
	/**
	 * 模糊查询
	 * */
	@Override
	public List<RoleBean> queryRole(String rname,String page) {
		int start=(Integer.parseInt(page)-1)*PartPage.pageSize;
		List<RoleBean> list=new ArrayList<RoleBean>();
		String sql="select * from role where  r_id!=34 and r_name like '%"+rname+"%' and r_state!=2 limit "+start+","+PartPage.pageSize;
		ResultSet rs=jt.query(sql);//得到所有角色结果集
		try {
			while(rs.next()){//组装
				list.add(new RoleBean(rs.getInt(1),rs.getString(2),rs.getInt(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return list;
	}
	/**
	 * 返回所有角色信息 降序
	 * */
	@Override
	public List<RoleBean> getAllRolesOrder() {
		List<RoleBean> list=new ArrayList<RoleBean>();
		String sql="select * from role where r_id!=34 and r_state!=2 order by r_id DESC";
		ResultSet rs=jt.query(sql);//得到所有角色结果集
		try {
			while(rs.next()){//组装
				list.add(new RoleBean(rs.getInt(1),rs.getString(2),rs.getInt(3)));
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
	public int getCount(String rname) {
		String sql="";
		int count=0;
		if(rname==""){
			sql="select count(*) from role where r_id!=34 and r_state!=2";
		}else {
			sql="select count(*) from role where r_id!=34 and r_state!=2 and r_name like '%"+rname+"%'";
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
