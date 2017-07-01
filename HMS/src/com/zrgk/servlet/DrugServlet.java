package com.zrgk.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.zrgk.bean.DrugBean;
import com.zrgk.bean.DrugPage;
import com.zrgk.bean.UserBean;
import com.zrgk.service.DrugService;
import com.zrgk.serviceImpl.DrugServiceImpl;

/**
 * 药品管理
 * @author 任兵
 *
 */
@SuppressWarnings("serial")
public class DrugServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		DrugService ds=new DrugServiceImpl();
		String method=request.getParameter("method");
		if ("lookOneDrug".equals(method)) {
			//详情
			String id= request.getParameter("id");
			DrugBean db=ds.lookOneDrug(id);
			request.setAttribute("db", db);
			request.getRequestDispatcher("medicine/look.jsp").forward(request, response);
		}else if("addMedicine".equals(method)){
			//添加
			String id= request.getParameter("id");
			DrugBean db=ds.lookOneDrug(id);
			request.setAttribute("db", db);
			request.getRequestDispatcher("medicine/add_medicine.jsp").forward(request, response);
		}else if("daoChuExcel".equals(method)){
			//导出Excel
			String ids= request.getParameter("id");
			DrugBean db=null;
			List<DrugBean> list=new ArrayList<DrugBean>();
			String id[] = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				if(id[i] != ""){
					db=ds.lookOneDrug(id[i]);
					list.add(db);
				}
			}
			if(!list.isEmpty() && list != null){
				String path = this.getServletContext().getRealPath("\\excel");
				WritableWorkbook book = Workbook.createWorkbook(new File(path
						+ "/test.xls"));
				WritableSheet sheet = book.createSheet("第一页", 0);
				Label label01 = new Label(0, 0, "药品编号");
				Label label02 = new Label(1, 0, "药品名称");
				Label label03 = new Label(2, 0, "药品类型");
				Label label04 = new Label(3, 0, "简单描述");
				Label label05 = new Label(4, 0, "状态");
				Label label06 = new Label(5, 0, "剩余量");
				try {
				for (int i = 0; i < list.size(); i++) {
					db = (DrugBean)list.get(i);
					Label label11 = new Label(0, i + 1, db.getDr_id()+"");
					Label label12 = new Label(1, i + 1, db.getDr_name());
					Label label13 = new Label(2, i + 1, db.getDr_type());
					Label label14 = new Label(3, i + 1, db.getDr_simdesc());
					Label label15 = new Label(4, i + 1, db.getDr_state()+"");
					Label label16 = new Label(5, i + 1, db.getDr_number()+"");
		
					sheet.addCell(label11);
					sheet.addCell(label12);
					sheet.addCell(label13);
					sheet.addCell(label14);
					sheet.addCell(label15);
					sheet.addCell(label16);
				}
					sheet.addCell(label01);
					sheet.addCell(label02);
					sheet.addCell(label03);
					sheet.addCell(label04);
					sheet.addCell(label05);
					sheet.addCell(label06);
					book.write();
					book.close();
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
				FileInputStream input = new FileInputStream(new File(path
						+ "/test.xls"));
				OutputStream out = response.getOutputStream();
				response.setHeader(
						"Content-Disposition",
						"attachment; filename="
								+ java.net.URLEncoder.encode("导出信息.xls",
										"UTF-8"));
				response.setContentType("application/vnd.ms-excel");
				int flag = 0;
				while ((flag = input.read()) != -1) {
					out.write(flag);
				}
				out.flush();
				out.close();
				input.close();
			}
		}else if("yanzheng".equals(method)){
			//验证编号
			String id= request.getParameter("id");
			DrugBean db=ds.lookOneDrug(id);
			List<DrugBean> list=new ArrayList<DrugBean>();
			if(db!=null){
			list.add(db);
			}
			int a = -1;
			int b = list.size();
			if(b ==0){
				a = 1;
				response.setContentType("txt/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(a);
				out.flush();
				out.close();
			}else{
				a = 0;
				response.setContentType("txt/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(a);
				out.flush();
				out.close();
			}
		}else if("editChoose".equals(method)){
			//编辑选择
			String id= request.getParameter("id");
			DrugBean db=ds.lookOneDrug(id);
			request.setAttribute("db", db);
			request.getRequestDispatcher("medicine/edit.jsp").forward(request, response);
		}else if("editDrug".equals(method)){
			//编辑
			List<String> lists=new ArrayList<String>();
			String id = request.getParameter("pid");
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(dfif);
			try {
				List<FileItem> list = sfu.parseRequest(request);
				for(FileItem fOne:list){
					if(fOne.isFormField()){
						String name = fOne.getFieldName();
						String value =fOne.getString("utf-8");
						lists.add(value);
					}else{
						String path = this.getServletContext().getRealPath("/medicine/drug");
						String fileName= fOne.getName();
						if(fileName !=null && fileName != ""){
							lists.remove(2);
							String url=null;
							if(fileName!=null&&!"".equals(fileName)){
								url="medicine"+"/"+"drug"+"/"+fileName;
								lists.add(url);
							}
							File f = new File(path+"/"+fileName);
							InputStream in = fOne.getInputStream();
							OutputStream out = new FileOutputStream(f);
							byte b[] = new byte[1024];
							int len=0;
							while((len=in.read(b)) != -1){
								out.write(b,0,len);
							}
							in.close();
							out.close();
						}
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean b=ds.editDrug(lists);
			if (b) {
				request.getRequestDispatcher("DrugServlet.do?method=getAllDrug").forward(request, response);
			} else {
				DrugBean db=ds.lookOneDrug(id);
				request.setAttribute("db", db);
				request.getRequestDispatcher("medicine/edit.jsp").forward(request, response);
			}
		}else if("changeDrug".equals(method)){
			//更改库存
			String id= request.getParameter("id");
			String n=request.getParameter("number");
			int number=Integer.parseInt(n);
			boolean b=ds.changeDrug(id,number);
			request.setAttribute("id", id);
			request.getRequestDispatcher("DrugServlet.do?method=addMedicine").forward(request, response);
		}else if("addDrug".equals(method)){
			//添加药品
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(dfif);
			List<String> lists=new ArrayList<String>();
			try {
				List<FileItem> list = sfu.parseRequest(request);
				for(FileItem fOne:list){
					if(fOne.isFormField()){
						String name = fOne.getFieldName();
						String value =fOne.getString("utf-8");
						lists.add(value);
					}else{
						String path = this.getServletContext().getRealPath("/medicine/drug");
						String fileName= fOne.getName();
						String url=null;
						if(fileName!=null&&!"".equals(fileName)){
							url="medicine"+"/"+"drug"+"/"+fileName;
							lists.add(url);
						}
						File f = new File(path+"/"+fileName);
						InputStream in = fOne.getInputStream();
						OutputStream out = new FileOutputStream(f);
						byte b[] = new byte[1024];
						int len=0;
						while((len=in.read(b)) != -1){
							out.write(b,0,len);
						}
						in.close();
						out.close();
					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean b=ds.addDrug(lists);
			if (b) {
				
				request.getRequestDispatcher("DrugServlet.do?method=getAllDrug").forward(request, response);
			} else {
				
				request.getRequestDispatcher("medicine/add.jsp").forward(request, response);
			}
			
		}  else if("getAllDrug".equals(method)){
			//查询所有
			String name= request.getParameter("pname");
			String s = request.getParameter("sss");
			if(s !=null){
				if(name != "" && name != null){
					name = new String(name.getBytes("ISO-8859-1"),"utf-8");
				}
			}
			
			String type= request.getParameter("types");
			if(type != "" && type != null){
				type = new String(type.getBytes("ISO-8859-1"),"utf-8");
			}
			int count=ds.getAllDrug(name, type);
			int nowpage = 0;
			String np= request.getParameter("nowpage");
			if(np ==null || np == ""){
				nowpage = 1;
			}else{
				nowpage = Integer.parseInt(np);
			}
			DrugPage dp = new DrugPage(count,nowpage);
			List<DrugBean> list= ds.getNowPageDrug(nowpage, name, type);
			request.setAttribute("dlist", list);
			request.setAttribute("dp", dp);
			int pageMax = dp.getCount()%dp.getSize()==0?dp.getCount()/dp.getSize():(dp.getCount()/dp.getSize()+1);
			request.setAttribute("pageMax",pageMax);
			request.setAttribute("dr_name", name);
			request.setAttribute("dr_type", type);
			request.getRequestDispatcher("medicine/index.jsp").forward(request, response);
		}
	}

}
