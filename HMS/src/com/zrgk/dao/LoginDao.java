package com.zrgk.dao;

import com.zrgk.bean.UserBean;

public interface LoginDao {
	//登陆
	public UserBean login(String uname,String upsw);
	//登录名是否存在
	public int getCount(String uname);
	//修改密码
	public boolean passWord(int uid,String ps);
}
