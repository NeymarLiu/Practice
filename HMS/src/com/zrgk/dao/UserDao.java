package com.zrgk.dao;

import java.util.List;

import com.zrgk.bean.UserBean;

public interface UserDao {
	//获取所有用户
	public List<UserBean> initUser(String page);
	//根据用户id查询用户详细信息
	public UserBean getUser(String uid);
	//根据用户名查询总记录数
	public int getAllUsers(String uname,String uid);
	//根据身份证查询总记录数
	public int getIdCardCount(String idcard);
	//添加用户
	public boolean addUser(String u_loginname,String u_password,
			String u_truename, String u_idcard,String u_sex,String u_email,
			String u_phone,String u_address, String u_createtime,
			String u_state,String u_role);
	//删除用户
	public boolean deleteUser(String[] uid);
	//更改用户信息
	public boolean updateUser(String psw,String phone,String address,
					String email,String role,String state,String uid);
	//模糊查询
	public List<UserBean> fuzzyUser(String uname,String page);
	//得到结果集总数
	public int getCount(String uname);
}
