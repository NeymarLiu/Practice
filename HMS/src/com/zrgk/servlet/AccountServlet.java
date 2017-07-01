package com.zrgk.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.zrgk.bean.LBeHospBean;
import com.zrgk.bean.LCanShu;
import com.zrgk.bean.LChap;
import com.zrgk.bean.LMoHu;
import com.zrgk.service.LBeHospService;
import com.zrgk.service.LHosrService;
import com.zrgk.serviceImpl.LHosrServiceImpl;
import com.zrgk.util.DownloadUtils;
import com.zrgk.util.PartPage;

/**
 * 住院结算
 * @author 龙汶宇
 *
 */
public class AccountServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//乱码问题将格式全设置为utf-8
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		LHosrService bhs=new LHosrServiceImpl();
		String type=request.getParameter("type");
		if("mohu".equals(type)){
			moHu(request,response,bhs);
		}else if("xinxi".equals(type)){
			//通过病例号查询用户的费用的详细信息
			xinXi(request,response,bhs);
			
		}else if("jiesuan".equals(type)){
			//结算
			jieSuan(request,response,bhs);
		}else if("daoChuExcel".equals(type)){
			//index导出Excel  住院结算
			daoChuExcel(request,response,bhs);
		}else if("daoChuExcel1".equals(type)){
			//index导出Excel
			daoChuExcel1(request,response,bhs);
		}
				
	}
	public void xinXi(HttpServletRequest request, HttpServletResponse response,LHosrService bhs)
			throws ServletException, IOException {
		//通过病例号查询用户的费用的详细信息
		String id=request.getParameter("myid");
		String page=request.getParameter("page");
		if(page == null){
			page="1";
		}
		int count =bhs.details(id).size();
		
		List<LChap> list=bhs.details(id,page);
		int pa=Integer.parseInt(page);
		PartPage p=bhs.getPartPage(count, pa);
		request.setAttribute("page", p);
		request.setAttribute("type","xinxi");
		List<LChap> list1=bhs.details(id);
		double sum=0;
		for(int i=0;i<list1.size();i++){
			sum +=list1.get(i).getChap_price();
		}
		request.setAttribute("lc", list);
		LChap l=bhs.money(id);
		double yue=l.getYue()-sum;
		LChap l2=new LChap(sum, l.getYue(),yue );
		request.setAttribute("ll", l2);
		request.setAttribute("idd", id);
		//返回页面
		request.getRequestDispatcher("hospital/account-look.jsp").forward(request, response);
		
		
	}
	public void moHu(HttpServletRequest request, HttpServletResponse response,LHosrService bhs)
			throws ServletException, IOException {
		String id =request.getParameter("id");
		if(id==null){
			id=request.getParameter("params");
		}
		if(id != null && !"".equals(id)){
			id=id.replaceAll(" ", "");
		}
		String name =request.getParameter("name");
		if(name==null){
			name=request.getParameter("params2");
		}
		if(name != null && !"".equals(name)){
			name=name.replaceAll(" ", "");
		}
		LMoHu lm=new LMoHu(id, name);
		String page=request.getParameter("page");
		if(page == null){
			page="1";
		}
		int count=bhs.moHu(lm).size();
		int pa=Integer.parseInt(page);
		PartPage p=bhs.getPartPage(count, pa);
		request.setAttribute("page", p);
		request.setAttribute("type","mohu");

		request.setAttribute("id", id);
		request.setAttribute("name",name);
		List<LBeHospBean> list=bhs.moHu(lm,page);
		
		request.setAttribute("lm", lm);
		request.setAttribute("lb", list);
		//返回页面
		request.getRequestDispatcher("hospital/account.jsp").forward(request, response);
		
	}
public void jieSuan(HttpServletRequest request, HttpServletResponse response,LHosrService bhs)
			throws ServletException, IOException {
		//结算
		String id=request.getParameter("cid");
		boolean b=bhs.jieSuan(id);
		request.setAttribute("js", b);
		request.getRequestDispatcher("AccountServlet.do?type=mohu").forward(request, response);
		
	}
public void daoChuExcel(HttpServletRequest request, HttpServletResponse response,LHosrService bhs)
		throws ServletException, IOException {
	
	//火狐的编码是base64 IE的编码是URL
	String agent = request.getHeader("user-agent");
	//
	String ids = request.getParameter("id");
	//字符串的截取
	LBeHospBean ub= null;
	List<LBeHospBean> list = new ArrayList<LBeHospBean>();
	String id[] = ids.split(",");
	for (int i = 0; i < id.length; i++) {
		if(id[i] != ""){
			ub = (LBeHospBean) bhs.getSomeInfo(id[i]);
			list.add(ub);
		}
	}
	
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
		Label label02 = new Label(1, 0, "姓名");
		Label label03 = new Label(2, 0, "押金");
		Label label04 = new Label(3, 0, "当前余额");
		Label label05 = new Label(4, 0, "状态");
		try {
		for (int i = 0; i < list.size(); i++) {
			LBeHospBean u = (LBeHospBean)list.get(i);
			Label label11 = new Label(0, i + 1, u.getBeh_id()+"");
			System.out.println(u.getBeh_id());
			Label label12 = new Label(1, i + 1, u.getName());
			Label label13 = new Label(2, i + 1, u.getBeh_antecedent()+"");
			Label label14 = new Label(3, i + 1, u.getBeh_leftmoney()+"");
			Label label15 = new Label(4, i + 1, u.getBeh_state()+"");

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
			fileName2 = DownloadUtils.base64EncodeFileName("导出信息");
		}else{
			//ie 或者谷歌浏览器URL编码处理
			fileName2=URLEncoder.encode("导出信息哈哈","utf-8");
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
public void daoChuExcel1(HttpServletRequest request, HttpServletResponse response,LHosrService bhs)
		throws ServletException, IOException {
	
	//火狐的编码是base64 IE的编码是URL
	String agent = request.getHeader("user-agent");
	System.out.println(agent);
	//
	String id=request.getParameter("id");
	List<LChap> list=bhs.details(id);
	//字符串的截取
	
	
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
		Label label02 = new Label(1, 0, "姓名");
		Label label03 = new Label(2, 0, "收费项目");
		Label label04 = new Label(3, 0, "收费金额");
		Label label05 = new Label(4, 0, "收费日期");
		try {
		for (int i = 0; i < list.size(); i++) {
			LChap u = (LChap)list.get(i);
			Label label11 = new Label(0, i + 1, u.getId()+"");
			Label label12 = new Label(1, i + 1, u.getName());
			Label label13 = new Label(2, i + 1, u.getChap_name());
			Label label14 = new Label(3, i + 1, u.getChap_price()+"");
			Label label15 = new Label(4, i + 1, u.getChap_time());

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
			fileName2 = DownloadUtils.base64EncodeFileName("导出信息");
		}else{
			//ie 或者谷歌浏览器URL编码处理
			fileName2=URLEncoder.encode("导出信息哈哈","utf-8");
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

