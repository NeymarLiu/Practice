package com.zrgk.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.zrgk.bean.UserBean;
import com.zrgk.dao.LoginDao;
import com.zrgk.util.JDBCTemplate;

public class LoginDaoImpl implements LoginDao{
	JDBCTemplate jt;
	public  LoginDaoImpl() {
		jt=new JDBCTemplate();
	}
	@Override
	public UserBean login(String uname, String upsw) {
		String sql="select * from user  where u_loginname=? and u_password=?";
		String []params={uname,upsw};
		ResultSet rs=jt.query(sql,params);
		UserBean ub=null;
		try {
			while(rs.next()){
				ub=new UserBean(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),
						rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getInt(11),rs.getInt(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.closeRes();
		}
		return ub;
	}
	@Override
	public int getCount(String uname) {
		String sql="";
		int count=0;
		sql="select count(*) from user where u_state!=2 and u_loginname ='"+uname+"'";
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
	//修改密码
		@Override
		public boolean passWord(int uid, String ps) {
			String sql="update user set u_password='"+ps+"' where u_id="+uid;
			boolean b=jt.updateData(sql);
			return b;
		}
}
