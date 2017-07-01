package com.zrgk.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * jdbc 的链接数据库 通用类
 * 
 * 
 * 
 */
public class JDBCTemplate {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	// 声明链接数据对象变量
	Connection conn;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	static ComboPooledDataSource cpds=new ComboPooledDataSource();
	// 注册驱动
/*	static {
		// 加载连接数据库 配置信息   配置文件
		InputStream  in = JDBCTemplate.class.getClassLoader().getResourceAsStream("jdbc.properties");
		//  解析  属性文件 工具类型
		Properties   pro = new  Properties();
		try {
			pro.load(in);
			// 
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(driver);
		}
	}*/

	// 获得链接
	private void getConnection() {
		try {
			conn = cpds.getConnection();
			//  改成 手动提交
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("获得链接失败!");
		}
	}
	/**
	 * 基于状态通道 执行批处理
	 * @param sql
	 */
    public boolean  executeBatch(List<String>  sql){
    	  this.getStatement();
    	  boolean b=false;
			    try {
			    	//  添加状态通道中的一个批出缓存中
			    	for (int i = 0; i < sql.size(); i++) {
			    		st.addBatch(sql.get(i));
			    	}
			    	// 执行批处理
			    	st.executeBatch();
			    	this.myCommit();
			    	b=true;
				} catch (SQLException e) {
					e.printStackTrace();
					this.myRollBack();
				}finally{
					this.closeRes();
				}
		return b;
    }
	/**
	 * 创建状态通道
	 */
	private void getStatement() {
		this.getConnection();
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("创建状态通道失败！");
		}
	}

	/**
	 * 创建预备状态通道
	 */
	private void preStatment(String sql) {
		this.getConnection();
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("创建预备状态通道失败！");
		}
	}

	/**
	 * 在状态通道下 执行数据操作
	 * 
	 * @param sql
	 *            update insert delete
	 * @return boolean
	 */
	public boolean updateData(String sql) {
		System.out.println("update====>"+sql);
		this.getStatement();
		boolean isok = false;
		try {
			int result = st.executeUpdate(sql);
			if (result > 0) {
				isok = true;
			}
			myCommit();
		} catch (SQLException e) {
			myRollBack();
			e.printStackTrace();
			System.out.println("在状态通道下执行数据操作失败！");
		}
		return isok;
	}

	/**
	 * 基于预状态进行数据操作
	 * 
	 * @param sql
	 *            update ?? insert into ?? delete ??
	 */
	public boolean updateData(String sql, String[] params) {
		System.out.println("preStateupdate====>"+sql);
		this.preStatment(sql);
		boolean isok = false;
		try {
			this.bandle(params);
			// 执行完整sql 语句
			int result = ps.executeUpdate();
			if (result > 0) {
				isok = true;
			}
			myCommit();
		} catch (SQLException e) {
			myRollBack();
			e.printStackTrace();
			System.err.println("在预备状态通道下执行数据操作失败!");
		}
		return isok;
	}

	/**
	 * 绑定参数
	 * 
	 * @param params
	 * @throws SQLException
	 */
	private void bandle(String[] params) throws SQLException {
		// 绑定参数
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				ps.setString(i + 1, params[i]);
			}
		}
	}

	/**
	 * 查询 基于预备状态 通道
	 * 
	 * @param select
	 *       
	 */
	public ResultSet query(String sql, String[] params) {
		System.out.println("prestatequery=====>"+sql);
		this.preStatment(sql);
		// 绑定参数
		try {
			this.bandle(params);
			// 执行
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("在预备状态通道下执行查询失败！");
		}
		return rs;
	}
    /**
     * 基于状态通道 数据查询
     * @param sql   select
     * @return  ResultSet
     */
	public ResultSet query(String sql) {
		System.out.println("query=====>"+sql);
		  this.getStatement();
		  try {
			rs = st.executeQuery(sql);
		  } catch (SQLException e) {
			e.printStackTrace();
			System.err.println("在状态通道下执行数据查询失败！");
		  }
		  return  rs;
	}
   
	public   void  myCommit(){
		try {
			conn.commit();
			closeRes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public  void   myRollBack(){
		try {
			conn.rollback();
			closeRes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 关闭资源
	 */
	public void closeRes() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
