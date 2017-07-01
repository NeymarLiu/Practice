package com.zrgk.dao;

import java.util.List;

import com.zrgk.bean.MenuBean;

public interface MenuDao {
	//根据用户ID返回其拥有的菜单
	public List<MenuBean> getMenu(int id);
	//返回所有菜单
	public List<MenuBean> getMenuList(String page);
	//根据id查询
	public MenuBean getMenuInfo(String mid);
	//根据id更新菜单资源
	public boolean updateMenuInfo(String mid,String mname,String murl,String mstate);
	//根据菜单名称查询是否存在
	public int getAllMenus(String mname,String mid);
	//删除菜单
	public boolean deleteMenu(String[] mid);
	//添加菜单
	public boolean insertMenu(String mname,String murl,String mstate);
	//模糊查询
	public List<MenuBean> getMenuList(String mname,String page);
	//得到结果集总数
	public int getCount(String mname);
}
