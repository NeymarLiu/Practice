package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zrgk.bean.UserBean;
import com.zrgk.dao.UserDao;
import com.zrgk.util.JDBCTemplate;
import com.zrgk.util.PartPage;

public class UserDaoImpl implements UserDao {
	JDBCTemplate jt;
	public UserDaoImpl(){
		jt=new JDBCTemplate();
	}
	/**
	 * 得到所有用户信息
	 * */
	@Override
	public List<UserBean> initUser(String page) {
		List<UserBean> list=new ArrayList<UserBean>();
		int start=(Integer.parseInt(page)-1)*PartPage.pageSize;
		String sql="select u.* from user u inner join role r on u.u_role=r.r_id where u_id!=21 and u_state!=2 and r_state!=2 limit "+start+","+PartPage.pageSize;
		ResultSet rs=jt.query(sql);
		try {
			while (rs.next()) {
				String time=rs.getString(10);
				time=time.split(" ")[0];
				String address=rs.getString(9);//datetime截取
				if (address.length()>6) {//当地址过长时，截取
					address=rs.getString(9).substring(0,6);
				}
				list.add(new UserBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),
						rs.getString(7),rs.getString(8),address,time,rs.getInt(11),rs.getInt(12)));
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
	 * 根据用户id获取用户详细信息
	 * */
	@Override
	public UserBean getUser(String uid) {
		String sql="select * from user where u_id=?";
		String params[]={uid};
		ResultSet rs=jt.query(sql, params);
		UserBean ub=null;
		try {
			while (rs.next()) {
				ub=new UserBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(12));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return ub;
	}
	/**
	 * 用户名唯一性验证
	 * */
	@Override
	public int getAllUsers(String uname,String uid) {
		String sql=null;
		int count=0;
		if (uid==null) {
			sql="select count(*) from user where u_loginname='"+uname+"'";
		}else {
			sql="select count(*) from user where u_loginname='"+uname+"'and u_id!="+uid;
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
	/***/
	@Override
	public boolean addUser(String u_loginname, String u_password,
			String u_truename, String u_idcard, String u_sex, String u_email,
			String u_phone, String u_address, String u_createtime, String u_state,
			String u_role) {
		String sql="insert into user values(default,?,?,?,?,?,?,?,?,?,?,?)";
		String params[]={u_loginname,u_password,u_truename,u_idcard
				,u_sex,u_email,u_phone,u_address,u_createtime,u_state,u_role};
		boolean b=jt.updateData(sql,params);
		return b;
	}
	@Override
	public boolean deleteUser(String[] uid) {
		List<String>list =new ArrayList<String>();
		for (int i = 0; i < uid.length; i++) {
			//将用户删除
			list.add("update user set u_state=2 where u_id ="+uid[i]);
		}
		boolean b=jt.executeBatch(list);
		return b;
	}
	/**
	 * 更改用户信息
	 * */
	@Override
	public boolean updateUser(String psw, String phone, String address,
			String email, String role, String state,String uid) {
		String sql="update user set u_password=?,u_email=?,u_phone=?,u_address=?,u_state=?,u_role=? where u_id=?";
		String params[]={psw,email,phone,address,state,role,uid};
		boolean b=jt.updateData(sql, params);
		return b;
	}
	/**
	 * 模糊查询
	 * */
	@Override
	public List<UserBean> fuzzyUser(String uname,String page) {
		List<UserBean> list=new ArrayList<UserBean>();
		int start=(Integer.parseInt(page)-1)*PartPage.pageSize;
		String sql="select * from user where u_id!=21 and u_loginname like '%"+uname+"%' limit "+start+","+PartPage.pageSize;
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				String time=rs.getString(10);
				time=time.split(" ")[0];
				String address=rs.getString(9);//datetime截取
				if (address.length()>6) {//当地址过长时，截取
					address=rs.getString(9).substring(0,6);
				}
				list.add(new UserBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),
						rs.getString(7),rs.getString(8),address,time,rs.getInt(11),rs.getInt(12)));
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
	public int getCount(String uname) {
		String sql="";
		int count=0;
		if(uname==""){
			sql="select count(*) from user where u_id!=21 and u_state!=2";
		}else {
			sql="select count(*) from user where u_id!=21 and u_state!=2 and u_loginname like '%"+uname+"%'";
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
	/**
	 * 根据身份证号查询记录数
	 * */
	@Override
	public int getIdCardCount(String idcard) {
		String sql=null;
		int count=0;
		sql="select count(*) from user where u_idcard='"+idcard+"'";
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
