package com.zrgk.serviceImpl;

import org.w3c.dom.ls.LSException;

import com.zrgk.bean.UserBean;
import com.zrgk.dao.LoginDao;
import com.zrgk.daoImpl.LoginDaoImpl;
import com.zrgk.service.LoginService;

public class LoginServiceImpl implements LoginService{
	LoginDao ld;
	public  LoginServiceImpl() {
		ld=new LoginDaoImpl();
	}
	
	@Override
	public UserBean login(String uname, String upsw) {
		// TODO Auto-generated method stub
		return ld.login(uname, upsw);
	}

	@Override
	public int getCount(String uname) {
		// TODO Auto-generated method stub
		return ld.getCount(uname);
	}

	@Override
	public boolean passWord(int uid, String ps) {
		// TODO Auto-generated method stub
		return ld.passWord(uid, ps);
	}

}
