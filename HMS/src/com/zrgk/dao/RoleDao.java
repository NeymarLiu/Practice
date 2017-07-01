package com.zrgk.dao;

import java.util.List;

import com.zrgk.bean.MenuBean;
import com.zrgk.bean.RoleBean;

public interface RoleDao {
	//返回所有角色
	public List<RoleBean> getAllRoles(String page);
	//返回所有角色
	public List<RoleBean> getAllRoles();
	//返回角色信息
	public RoleBean getRoleInfo(String rid);
	//根据角色id返回对应的菜单
	public List<MenuBean> getRoleMenu(String rid);
	//返回所有菜单
	public List<MenuBean> getRoleMenu();
	//更改对应角色的信息及菜单
	public boolean changeMenu(String rid,String rname,String rstate,String menu[]);
	//根据角色姓名查询是否存在
	public int getAllRoles(String rname,String rid);
	//删除角色
	public boolean deleteRole(String[] rid);
	//添加角色以及菜单
	public boolean insertRole(String rname,String rstate,String[] menu);
	//模糊查询
	public List<RoleBean> queryRole(String rname,String page);
	//降序
	public List<RoleBean> getAllRolesOrder();
	//得到结果集总数
	public int getCount(String rname);
}
