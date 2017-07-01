package com.zrgk.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.zrgk.bean.ChargeProjectBean;
import com.zrgk.bean.LHosrBean;
import com.zrgk.bean.WHosrBean;
import com.zrgk.daoImpl.WHosrDaoImpl;
import com.zrgk.service.ChargeProjectService;
import com.zrgk.service.WHosrService;
import com.zrgk.serviceImpl.ChargeProjectServiceImpl;
import com.zrgk.serviceImpl.WHosrServiceImpl;
import com.zrgk.util.DownloadUtils;
import com.zrgk.util.JDBCTemplate;
import com.zrgk.util.PartPage;

public class WHosrServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String method=request.getParameter("method");
		WHosrService whs=new WHosrServiceImpl();
		//展现所有病人信息
		if("initWHosr".equals(method)){
			String page=request.getParameter("page");
			int nowPage=0;
			if(page==null){
				nowPage=1;
				page="1";
			}else{
				nowPage=Integer.parseInt(page);
			}
			int count =whs.getAllWHosr();
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
			request.setAttribute("method", "initWHosr");
			List<WHosrBean>	list=whs.getAllWHosr(page);
			request.setAttribute("wHosrs", list);
			request.getRequestDispatcher("hospital/charge2.jsp").forward(request, response);
		//根据id获取某个病人的详细收费信息
		}else if ("WHosrinfo".equals(method)) {
			String id=request.getParameter("id");
			String page=request.getParameter("page");
			int nowPage=0;
			if(page==null){
				nowPage=1;
				page="1";
			}else{
				nowPage=Integer.parseInt(page);
			}
			int count =whs.getOneWHosr(id);
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
			request.setAttribute("method", "WHosrinfo");
			List<WHosrBean>	list2=whs.getOneWHosrs(id);
			List<WHosrBean>	list=whs.getOneWHosr(id,page);
			double tatleprice =0;
			double beh_antecedent=0;
			for(int i=0;i<list2.size();i++){
				tatleprice +=list2.get(i).getCp_price();
				//beh_antecedent=list.get(i).getBeh_antecedent();
			}
			//String sql1="select beh_antecedent from behospital where id="+id;
			double beh_leftmoney=0;
			//String sql2="select beh_leftmoney from behospital where id="+id;
			WHosrDaoImpl wdi=new WHosrDaoImpl();
			WHosrBean wb=wdi.getOneWHosrBean(id);
			beh_antecedent=wb.getBeh_antecedent();
			beh_leftmoney=beh_antecedent - tatleprice ;
			System.out.println(beh_leftmoney);
			request.setAttribute("M", tatleprice);
			request.setAttribute("N", beh_antecedent);
			request.setAttribute("K", beh_leftmoney);
			request.setAttribute("id", id);
			request.setAttribute("wHosr", list);
			request.getRequestDispatcher("hospital/account-look2.jsp").forward(request, response);
		//根据病人id添加收费项
		}else if ("insert".equals(method)) {
			String bid= request.getParameter("id");
			String bname= request.getParameter("name");
			bname=new String(bname.getBytes("ISO-8859-1"),"utf-8");
			String cid= request.getParameter("cps");
			Date chap_time= new Date();
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time= sdf.format(chap_time);
			boolean b= whs.insertOneWHosr(bid, cid, time);
			ChargeProjectService cps=new ChargeProjectServiceImpl();
			List<ChargeProjectBean> list=cps.getAllProjects();
			//都通过request带过去
			request.setAttribute("list", list);
			request.setAttribute("hors_id", bid);
			request.setAttribute("hors_name", bname);
			request.setAttribute("meg",b);
			request.getRequestDispatcher("hospital/charge-new.jsp").forward(request, response);
		}else if("add".equals(method)){
			String bid= request.getParameter("bid");
			String bname= request.getParameter("name");
			//System.out.println(URLDecoder.decode(bname));
			//所有的收费项目集合\
			//bname=new String(bname.getBytes("ISO-8859-1"),"utf-8");
			ChargeProjectService cps=new ChargeProjectServiceImpl();
			List<ChargeProjectBean> list=cps.getAllProjects();
			//都通过request带过去
			request.setAttribute("list", list);
			request.setAttribute("hors_id", bid);
			request.setAttribute("hors_name", bname);
			//跳转到相关页面
			request.getRequestDispatcher("hospital/charge-new.jsp").forward(request, response);
		}else if ("mohu".equals(method)) {
			String id = request.getParameter("inp");
			String name = request.getParameter("name");
			String page=request.getParameter("page");
			int nowPage=0;
			if(page==null){
				nowPage=1;
				page="1";
			}else{
				nowPage=Integer.parseInt(page);
			}
			int count =whs.moHu(id, name);
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
			List<WHosrBean> list=whs.moHu(id, name,page);
			request.setAttribute("id", id);
			System.out.println(id);
			request.setAttribute("name", name);
			request.setAttribute("wHosrs", list);
			request.getRequestDispatcher("hospital/charge2.jsp").forward(request, response);
		}else if("daoExcel".equals(method)){
			//index导出Excel
			daoExcel(request,response,whs);	
	}
	}

	private void daoExcel(HttpServletRequest request,
			HttpServletResponse response, WHosrService whs) throws IOException {
		// TODO Auto-generated method stub
		//火狐的编码是base64 IE的编码是URL
		//火狐的编码是base64 IE的编码是URL
		String page=request.getParameter("page");
		String agent = request.getHeader("user-agent");
		System.out.println(agent);
		//
		String id=request.getParameter("id");
		List<WHosrBean> list=whs.getOneWHosr(id,page);
		//字符串的截取
	System.out.println("id");
	//导出excel
	if(!list.isEmpty() && list != null){
		String path = this.getServletContext().getRealPath("\\excel");
		//String path = request.getServletContext().getRealPath("\\excel");
		//查询成功
		WritableWorkbook book = Workbook.createWorkbook(new File(path
				+ "/test.xls"));
		// 创建第一页工作簿
		WritableSheet sheet = book.createSheet("第一页", 0);
		// 第一个参数代表列 ，第二个参数代表行
		Label label01 = new Label(0, 0, "病历号");
		Label label02 = new Label(1, 0, "病人姓名");
		Label label03 = new Label(2, 0, "收费项目");
		Label label04 = new Label(3, 0, "收费金额");
		Label label05 = new Label(4, 0, "检查日期");
		try {
		for (int i = 0; i < list.size(); i++) {
			WHosrBean u = (WHosrBean)list.get(i);
			Label label11 = new Label(0, i + 1, u.getHosr_id()+"");
			System.out.println(u.getHosr_id());
			Label label12 = new Label(1, i + 1, u.getHosr_name());
			Label label13 = new Label(2, i + 1, u.getCp_name()+"");
			Label label14 = new Label(3, i + 1, u.getCp_price()+"");
			Label label15 = new Label(4, i + 1, u.getChap_time()+"");

			sheet.addCell(label11);
			sheet.addCell(label12);
			sheet.addCell(label13);
			sheet.addCell(label14);
			sheet.addCell(label15);
		}

	
			// 将格子放入工作簿中
			sheet.addCell(label01);
			sheet.addCell(label02);
			sheet.addCell(label03);
			sheet.addCell(label04);
			sheet.addCell(label05);
			book.write();
			book.close();
			System.out.println("成功");
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		// -------------------
		
		
		String fileName2= null;
		if(agent.contains("Firefox")){
			//火狐浏览器  base64的编码处理
			fileName2 = DownloadUtils.base64EncodeFileName("导出表格");
		}else{
			//ie 或者谷歌浏览器URL编码处理
			fileName2=URLEncoder.encode("导出表格","utf-8");
		}
		
		
		FileInputStream input = new FileInputStream(new File(path
				+ "/test.xls"));
		OutputStream out = response.getOutputStream();
		response.setHeader(
				"Content-Disposition",
				"attachment; filename="+fileName2+".xls"
								);
		response.setContentType("application/vnd.ms-excel");
		int flag = 0;
		while ((flag = input.read()) != -1) {
			out.write(flag);
		}
		out.flush();
		out.close();
		input.close();
	}
	}
}
