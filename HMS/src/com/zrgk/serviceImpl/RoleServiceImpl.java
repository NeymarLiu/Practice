package com.zrgk.serviceImpl;

import java.util.List;

import com.zrgk.bean.MenuBean;
import com.zrgk.bean.RoleBean;
import com.zrgk.dao.RoleDao;
import com.zrgk.daoImpl.RoleDaoImpl;
import com.zrgk.service.RoleService;
import com.zrgk.util.PartPage;

public class RoleServiceImpl implements RoleService{
	RoleDao rd;
	public RoleServiceImpl(){
		rd=new RoleDaoImpl();
	}
	@Override
	public List<RoleBean> getAllRoles(String page) {
		return rd.getAllRoles(page);
	}
	@Override
	public List<RoleBean> getAllRoles() {
		return rd.getAllRoles();
	}
	@Override
	public List<MenuBean> getRoleMenu(String rid) {
		 List<MenuBean> lists=rd.getRoleMenu();
		 List<MenuBean> list=rd.getRoleMenu(rid);
		 for (MenuBean menus : lists) {
			for (MenuBean menu : list) {
				if (menus.getM_id()==menu.getM_id()) {
					//如果相同 标记此菜单
					menus.setFlag(1);
				}
			}
		}
		return lists;
	}
	@Override
	public RoleBean getRoleInfo(String rid) {
		// TODO Auto-generated method stub
		return rd.getRoleInfo(rid);
	}
	@Override
	public boolean changeMenu(String rid, String rname, String rstate,
			String[] menu) {
		// TODO Auto-generated method stub
		return rd.changeMenu(rid, rname, rstate, menu);
	}
	@Override
	public int getAllRoles(String rname,String rid) {
		// TODO Auto-generated method stub
		return rd.getAllRoles(rname,rid);
	}
	@Override
	public boolean deleteRole(String[] rid) {
		// TODO Auto-generated method stub
		return rd.deleteRole(rid);
	}
	@Override
	public List<MenuBean> getMenus() {
		// TODO Auto-generated method stub
		return rd.getRoleMenu();
	}
	@Override
	public boolean insertRole(String rname, String rstate, String[] menu) {
		// TODO Auto-generated method stub
		return rd.insertRole(rname, rstate, menu);
	}
	@Override
	public List<RoleBean> queryRole(String rname,String page) {
		// TODO Auto-generated method stub
		return rd.queryRole(rname,page);
	}
	@Override
	public List<RoleBean> getAllRolesOrder() {
		// TODO Auto-generated method stub
		return rd.getAllRolesOrder();
	}
	@Override
	public int getCount(String rname) {
		// TODO Auto-generated method stub
		return rd.getCount(rname);
	}
	@Override
	public PartPage getPartPage(int count,int page) {
			PartPage p=new PartPage();
			int pages=0;
			if (count%PartPage.pageSize!=0) {
				pages=count/PartPage.pageSize+1;
			}else {
				pages=count/PartPage.pageSize;
			}
			p.setCount(count);
			p.setNowPage(page);
			p.setTotalPage(pages);
		return p;
	}
}
