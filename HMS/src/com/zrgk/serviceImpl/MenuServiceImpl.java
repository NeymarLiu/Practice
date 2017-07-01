package com.zrgk.serviceImpl;

import java.util.List;

import com.zrgk.bean.MenuBean;
import com.zrgk.dao.MenuDao;
import com.zrgk.daoImpl.MenuDaoImpl;
import com.zrgk.service.MenuService;
import com.zrgk.util.PartPage;

public class MenuServiceImpl extends PartPage implements MenuService {
	MenuDao md;
	public MenuServiceImpl(){
		md=new MenuDaoImpl();
	}
	@Override
	public List<MenuBean> getMenu(int id) {
		return md.getMenu(id);
	}
	@Override
	public List<MenuBean> getMenuList(String page) {
		// TODO Auto-generated method stub
		return md.getMenuList(page);
	}
	@Override
	public MenuBean getMenuInfo(String mid) {
		// TODO Auto-generated method stub
		return md.getMenuInfo(mid);
	}
	@Override
	public boolean updateMenuInfo(String mid, String mname, String murl,
			String mstate) {
		// TODO Auto-generated method stub
		return md.updateMenuInfo(mid, mname, murl, mstate);
	}
	@Override
	public int getAllMenus(String mname, String mid) {
		// TODO Auto-generated method stub
		return md.getAllMenus(mname, mid);
	}
	@Override
	public boolean deleteMenu(String[] mid) {
		// TODO Auto-generated method stub
		return md.deleteMenu(mid);
	}
	@Override
	public boolean insertMenu(String mname, String murl, String mstate) {
		// TODO Auto-generated method stub
		return md.insertMenu(mname, murl, mstate);
	}
	@Override
	public List<MenuBean> getMenuList(String mname,String page) {
		// TODO Auto-generated method stub
		return md.getMenuList(mname,page);
	}
	@Override
	public int getCount(String mname) {
		// TODO Auto-generated method stub
		return md.getCount(mname);
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
