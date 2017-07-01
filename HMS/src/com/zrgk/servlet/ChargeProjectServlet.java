package com.zrgk.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.zrgk.bean.ChargeProjectBean;
import com.zrgk.bean.WHosrBean;
import com.zrgk.service.ChargeProjectService;
import com.zrgk.service.WHosrService;
import com.zrgk.serviceImpl.ChargeProjectServiceImpl;
import com.zrgk.util.DownloadUtils;
import com.zrgk.util.PartPage;

/**
 * 收费项目管理Servlet
 * */
public class ChargeProjectServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		ChargeProjectService cps=new ChargeProjectServiceImpl();
		String method=request.getParameter("method");
		if("initCharge".equals(method)){
			//所有收费项目展示
			String page=request.getParameter("page");
			int nowPage=0;
			if(page==null){
				nowPage=1;
				page="1";
			}else{
				nowPage=Integer.parseInt(page);
			}
			int count =cps.getAllProject();
			int pages=0;
			if(count%PartPage.pageSize==0){
				pages=count/PartPage.pageSize;
			}else{
				pages=count/PartPage.pageSize+1;
			}
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("pageSize", PartPage.pageSize);
			request.setAttribute("count", count);
			request.setAttribute("pages", pages);
			request.setAttribute("method", "initCharge");
			List<ChargeProjectBean>	list=cps.getAllProject(page);
			request.setAttribute("chargeProjects", list);
			request.getRequestDispatcher("hospital/charge.jsp").forward(request, response);
		}else if ("chargeInfo".equals(method)) {
			//根据id获取某一个收费项目的详细信息
			String did=request.getParameter("did");
			ChargeProjectBean cpb=cps.getOneChargeProject(did);
			request.setAttribute("chargeProject", cpb);
			request.getRequestDispatcher("hospital/charge-info.jsp").forward(request, response);
			
		}else if ("editCharge".equals(method)) {
			//根据id获取某一个收费项目的详细信息进行修改
			String did=request.getParameter("did");
			ChargeProjectBean cpb=cps.getOneChargeProject(did);
			request.setAttribute("chargeProject", cpb);
			request.getRequestDispatcher("hospital/charge-edit.jsp").forward(request, response);
		}else if("editAdd".equals(method)){
			//保存修改之后的数据
			String id= request.getParameter("id");
			//name
			String cp_name= request.getParameter("cp_name");
			//price
			String cp_price= request.getParameter("cp_price");
			double p=0;
			int did=0;
			if(cp_price!= null){
				p=Double.parseDouble(cp_price);
			}
			if(id!= null){
				did=Integer.parseInt(id);
			}
			ChargeProjectBean cpb = new ChargeProjectBean(did,cp_name,p);
			//service调用方法
			boolean b = cps.editChargeProject(cpb);
			cpb=cps.getOneChargeProject(id);
			request.setAttribute("chargeProject", cpb);
			request.setAttribute("meg", b);
			request.getRequestDispatcher("hospital/charge-edit.jsp").forward(request, response);
		}else if ("delete".equals(method)) {
			//取被删除的id
			String id = request.getParameter("id");
			int number=0;
			if(id != null){
				number= Integer.parseInt(id);
			}
			boolean b = cps.delteOneChargeProject(id);
			String s = "";
			if(b){
				//删除成功    展现用户信息  弹出框删除 成功
				s="0";
				
			}else{
				//删除失败
				s="1";
			}
			List<ChargeProjectBean>	list=cps.getAllProjects();
			request.getRequestDispatcher("ChargeProjectServlet.do?method=initCharge").forward(request, response);
		}else if("idinsert".equals(method)){
			int id=cps.getOneCHargeProject();
			request.setAttribute("aid", id+1100);
			request.getRequestDispatcher("hospital/charge-add.jsp").forward(request, response);
		}else if("onlyname".equals(method)){
			List<String> list=cps.getChargeProjectName();
			String oname =request.getParameter("name");
			int  flag=0;
			for(int i=0;i<list.size();i++){
				if(list.get(i).equals(oname)){
					flag=1;
				}
			}
			PrintWriter pw=response.getWriter();
			pw.print(flag);
			pw.flush();
			pw.close();
		}else if("onlyname2".equals(method)){
			String id =request.getParameter("id");
			List<String> list=cps.getChargeProjectName(id);
			String oname =request.getParameter("name");
			int  flag=0;
			for(int i=0;i<list.size();i++){
				if(list.get(i).equals(oname)){
					flag=1;
				}
			}
			PrintWriter pw=response.getWriter();
			pw.print(flag);
			pw.flush();
			pw.close();
		}
		
		else if ("insert".equals(method)) {
			//添加收费项目
			String cpname = request.getParameter("cpname");
			String price= request.getParameter("price");
			boolean b= cps.insertOneChargeProject(cpname, price);
			request.setAttribute("bbb", b);
			//这里不应该回显
			/*request.setAttribute("cpname", cpname);
			request.setAttribute("price", price);*/
			request.getRequestDispatcher("ChargeProjectServlet.do?method=idinsert").forward(request, response);
		}else if ("mohu".equals(method)) {
			String name= request.getParameter("name");
			String page=request.getParameter("page");
			int nowPage=0;
			if(page==null){
				nowPage=1;
				page="1";
			}else{
				nowPage=Integer.parseInt(page);
			}
			int count =cps.mohu(name);
			int pages=0;
			if(count%PartPage.pageSize==0){
				pages=count/PartPage.pageSize;
			}else{
				pages=count/PartPage.pageSize+1;
			}
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("pageSize", PartPage.pageSize);
			request.setAttribute("count", count);
			request.setAttribute("pages", pages);
			request.setAttribute("method", "mohu");
		
			List<ChargeProjectBean> list=cps.mohu(name,page);
			request.setAttribute("name", name);
			request.setAttribute("chargeProjects", list);
			request.getRequestDispatcher("hospital/charge.jsp").forward(request, response);
			}
	}

}
