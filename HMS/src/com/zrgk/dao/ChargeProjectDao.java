package com.zrgk.dao;

import java.util.List;

import com.zrgk.bean.ChargeProjectBean;

public interface ChargeProjectDao {
	//返回所有可用的收费项目集合
	public int getAllProject();
	//根据项目id获取
	public ChargeProjectBean getOneChargeProject(String id);
	//修改收费项目
	public boolean editChargeProject(ChargeProjectBean cpb);
	//根据id删除某条项目
	public boolean delteOneChargeProject(String id);
	//创建一条新的收费项目
	public boolean insertOneChargeProject(String cpname,String price);
	//模糊查询
	public int mohu(String name);
	//返回所有可用的收费项目集合
	public List<ChargeProjectBean> getAllProject(String page);
	//返回所有可用的收费项目集合
	public List<ChargeProjectBean> getAllProjects();
	//模糊查询
	public List<ChargeProjectBean> mohu(String name,String page);
	//获取id
	public int getOneCHargeProject();
	//获取所有项目名称
	public List<String> getChargeProjectName();
	//获取所有项目名称
	public List<String> getChargeProjectName(String id);
}
