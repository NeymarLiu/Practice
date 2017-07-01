package com.zrgk.serviceImpl;

import java.util.List;

import com.zrgk.bean.UserBean;
import com.zrgk.dao.RoleDao;
import com.zrgk.dao.UserDao;
import com.zrgk.daoImpl.RoleDaoImpl;
import com.zrgk.daoImpl.UserDaoImpl;
import com.zrgk.service.UserService;
import com.zrgk.util.PartPage;

public class UserServiceImpl implements UserService{

	UserDao ud;
	public UserServiceImpl(){
		ud=new UserDaoImpl();
	}
	@Override
	public List<UserBean> initUser(String page) {
		return ud.initUser(page);
	}
	@Override
	public UserBean getUser(String uid) {
		return ud.getUser(uid);
	}
	@Override
	public int getAllUsers(String uname, String uid) {
		// TODO Auto-generated method stub
		return ud.getAllUsers(uname, uid);
	}
	@Override
	public boolean addUser(String u_loginname, String u_password,
			String u_truename, String u_idcard, String u_sex, String u_email,
			String u_phone, String u_address, String u_createtime,
			String u_state, String u_role) {
		// TODO Auto-generated method stub
		return ud.addUser(u_loginname, u_password, u_truename, u_idcard, u_sex, 
				u_email, u_phone, u_address, u_createtime, u_state, u_role);
	}
	@Override
	public boolean deleteUser(String[] uid) {
		// TODO Auto-generated method stub
		return ud.deleteUser(uid);
	}
	@Override
	public boolean updateUser(String psw, String phone, String address,
			String email, String role, String state,
			String uid) {
		// TODO Auto-generated method stub
		return ud.updateUser(psw, phone, address, email, role, state,uid);
	}
	@Override
	public List<UserBean> fuzzyUser(String uname,String page) {
		// TODO Auto-generated method stub
		return ud.fuzzyUser(uname,page);
	}
	@Override
	public int getCount(String uname) {
		// TODO Auto-generated method stub
		return ud.getCount(uname);
	}
	@Override
	public int getIdCardCount(String idcard) {
		// TODO Auto-generated method stub
		return ud.getIdCardCount(idcard);
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
