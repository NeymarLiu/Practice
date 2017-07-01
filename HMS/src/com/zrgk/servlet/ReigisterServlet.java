package com.zrgk.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.google.gson.Gson;
import com.zrgk.bean.DoctorBean;
import com.zrgk.bean.HosrBean;
import com.zrgk.service.Reigister;
import com.zrgk.serviceImpl.ReigisterImpl;
import com.zrgk.util.DownloadUtils;
import com.zrgk.util.PartPage;



/**
 * 获取doctor的名字和所属科室
 * @author weiyangming
 *
 */
@SuppressWarnings("serial")
public class ReigisterServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/**
		 * 查询doctor表获取doctor信息
		 */
		Reigister rei=new ReigisterImpl();
		String method=request.getParameter("method");
		if ("query".equals(method)) {
			
			//查询全部的挂号信息
			String now=request.getParameter("page");
			int nowPage;
			if(now != null && !"".equals(now)){
				nowPage=Integer.parseInt(now);
			}else{
				nowPage=1;//第一次初始化
			}
			int start =(nowPage-1)*PartPage.pageSize;
			//System.out.println(PartPage.pageSize);
			//查询count
			int count=rei.selectHosrCount();
			int pageNumber=0;
			if(count%PartPage.pageSize==0){
				pageNumber=count/PartPage.pageSize;
			}else{
				pageNumber=count/PartPage.pageSize+1;
			}
			List<HosrBean> hList=rei.allHosr(start,PartPage.pageSize);
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("dCount", count);
			request.setAttribute("pageSize",PartPage.pageSize);
			request.setAttribute("totalPage",pageNumber);
			request.setAttribute("method","query");
			request.setAttribute("allHosr", hList);
			request.getRequestDispatcher("registration/index.jsp").forward(request, response);
		}else if("like".equals(method)){
			//做模糊查询
			String hid=request.getParameter("mNumber");
			String doctorName=request.getParameter("mname");
			String office=request.getParameter("mKeShi");
			String mTimeMin=request.getParameter("mTimeMin");
			String mTimeMax=request.getParameter("mTimeMax");
			String now=request.getParameter("page");
			int nowPage;
			if(now != null && !"".equals(now)){
				nowPage=Integer.parseInt(now);
			}else{
				nowPage=1;//第一次初始化5
			}
			int start =(nowPage-1)*PartPage.pageSize;
			
			//查询count
			int count=rei.selHosrCountLike(hid,doctorName,office,mTimeMin,mTimeMax);
			int pageNumber=0;
			if(count%PartPage.pageSize==0){
				pageNumber=count/PartPage.pageSize;
			}else{
				pageNumber=count/PartPage.pageSize+1;
			}
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("dCount", count);
			request.setAttribute("pageSize",PartPage.pageSize);
			request.setAttribute("totalPage",pageNumber);
			request.setAttribute("method","like");
			List<HosrBean> hblist=rei.selectQuery(doctorName, hid, office, mTimeMin, mTimeMax,start,PartPage.pageSize);
			request.setAttribute("allHosr", hblist);
			request.setAttribute("mNumber", hid);
			request.setAttribute("mname", doctorName);
			request.setAttribute("mKeShi", office);
			request.setAttribute("mTimeMin", mTimeMin);
			request.setAttribute("mTimeMax", mTimeMax);
			request.getRequestDispatcher("registration/index.jsp").forward(request, response);
			
		}else if("look".equals(method)){
			//查看详情
			String hosrId=request.getParameter("mid");
			HosrBean hb=rei.conSecHosr(hosrId);
			request.setAttribute("allHosr", hb);
			DoctorBean db=rei.selDoctorName(hb.getD_id());
			request.setAttribute("doctor", db);
			request.getRequestDispatcher("registration/look.jsp").forward(request, response);
		}else if("edit".equals(method)){
			//更改挂号信息初始数据处理
			String hosrId=request.getParameter("mid");
			HosrBean hb=rei.conSecHosr(hosrId);
			request.setAttribute("hosr", hb);
			List<DoctorBean> db=rei.selAllDoctorOffice(hosrId);
			List<DoctorBean> dname=rei.selAllDoctor(hosrId);
			request.setAttribute("dname", dname);
			request.setAttribute("keshi", db);
			request.getRequestDispatcher("registration/edit.jsp").forward(request, response);
		}else if("update".equals(method)){
			//更改挂号信息
			String hid=request.getParameter("hid");
			String name=request.getParameter("pname");
			String idcard=request.getParameter("pidcard");
			String medical=request.getParameter("pmedocal");
			String money=request.getParameter("pmoney");
			String phone=request.getParameter("pphone");
			String self=request.getParameter("pslef");
			String sex=request.getParameter("psex");
			String age=request.getParameter("page");
			String word=request.getParameter("pwork");
			String look=request.getParameter("plook");
			String office=request.getParameter("ofc");
			String doctor=request.getParameter("pdoctor");
			String desc=request.getParameter("pdesc");
			DoctorBean db=rei.selDoctorId(doctor);
			boolean b=rei.updateHosr(hid,name,idcard,medical,money,phone,self,sex,age,word,look,office,db.getD_id(),desc);
			if(b){
				response.sendRedirect("ReigisterServlet.do?method=query");
			}else{
				request.getRequestDispatcher("ReigisterServlet.do?method=edit").forward(request, response);
			}
			
		}else if("delete".equals(method)){
			//退号
			String hid=request.getParameter("mid");
			boolean b=rei.deleteOneHosr(hid);
			if(b){
				//退号成功
				request.getRequestDispatcher("ReigisterServlet.do?method=query").forward(request, response);
			}else{
				request.getRequestDispatcher("ReigisterServlet.do?method=query").forward(request, response);
			}
		}else if("all".equals(method)){
			//批量退号
			String []id=request.getParameter("allId").split(",");
			boolean list=rei.plSellect(id);
			PrintWriter out=response.getWriter();
			if(list){
				out.print("true");

			}else{
				out.print("false");
			}
			out.flush();
			out.close();
		}else if("insert".equals(method)){
			//添加挂号信息
			String information=request.getParameter("info");
			String addInformation[]=information.split(",");
			//System.out.println(Arrays.toString(addInformation));
			boolean boo=rei.addHosrInfo(addInformation);
			PrintWriter out=response.getWriter();
			if(boo){
				out.print("true");
			}else{
				out.print("false");
			}
			out.flush();
			out.close();
		}else if("linkage".equals(method)){
			//查询dcotor信息，获取所有的科室
			List<DoctorBean> list=rei.selectAllDoctor();
			request.setAttribute("dList", list);
			request.getRequestDispatcher("registration/add.jsp").forward(request, response);
		}else if("liandong".equals(method)){
			//科室与医生
			String office=request.getParameter("office");
			//查询doctor表获取该科室下的所有医生信息
			List<DoctorBean> doctorName=rei.slectDoctorName(office);
			if(doctorName != null && doctorName.size() >0){
				Gson gson=new Gson();
				String dName=gson.toJson(doctorName).toString();
				PrintWriter out=response.getWriter();
				out.print(dName);
				out.flush();
				out.close();
			}else{
				PrintWriter out=response.getWriter();
				out.print("false");
				out.flush();
				out.close();
			}
		}else if("selIdcard".equals(method)){
			//查询身份证号码是否已经存在,true则不存在，false则存在
			String idCard=request.getParameter("idcard");
			boolean boo=rei.selHosrIdcard(idCard);
			if(boo){
				PrintWriter out=response.getWriter();
				out.print("true");
				out.flush();
				out.close();
			}else{
				PrintWriter out=response.getWriter();
				out.print("false");
				out.flush();
				out.close();
			}
		}else if("excal".equals(method)){
			//导出excal
			String agent = request.getHeader("user-agent");
			String dr_id = request.getParameter("allId");
			//查询被选中的用户
			List<HosrBean> list = rei.putExcal(dr_id);
			System.out.println(list);
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
				Label label01 = new Label(0, 0, "挂号id");
				Label label02 = new Label(1, 0, "患者姓名");
				Label label03 = new Label(2, 0, "身份证号");
				Label label04 = new Label(3, 0, "社保号");
				Label label05 = new Label(4, 0, "挂号费");
				Label label06 = new Label(5, 0, "性别");
				Label label07 = new Label(6, 0, "年龄");
				Label label08 = new Label(7, 0, "职业");
				Label label09 = new Label(8, 0, "初复诊");
				Label label010 = new Label(9, 0, "科室");
				Label label011 = new Label(10, 0, "医生id");
				Label label012 = new Label(11, 0, "备注");
				Label label013 = new Label(12, 0, "挂号时间");
				Label label014 = new Label(13, 0, "挂号状态");
				// 第3行第2列
				try {
				for (int i = 0; i < list.size(); i++) {
					HosrBean db = (HosrBean)list.get(i);
					Label label11 = new Label(0, i + 1, db.getHosr_id()+"");
					Label label12 = new Label(1, i + 1, db.getHosr_name());
					Label label13 = new Label(2, i + 1, db.getHosr_idcar());
					Label label14 = new Label(3, i + 1, db.getHosr_medical());
					Label label15 = new Label(4, i + 1, Double.toString(db.getHosr_selfprice()));
					Label label16 = new Label(5, i + 1, db.getHosr_sex());
					Label label17 = new Label(6, i + 1, db.getHosr_age()+"");
					Label label18 = new Label(7, i + 1, db.getHosr_word());
					Label label19 = new Label(8, i + 1, db.getHosr_lookdoctor()+"");
					Label label110 = new Label(9, i + 1, db.getKeshi());
					Label label111 = new Label(10, i + 1, db.getD_id()+"");
					Label label112 = new Label(11, i + 1, db.getHosr_remark());
					Label label113 = new Label(12, i + 1, db.getHosr_time());
					Label label114 = new Label(13, i + 1, db.getHosr_state()+"");
		
					sheet.addCell(label11);
					sheet.addCell(label12);
					sheet.addCell(label13);
					sheet.addCell(label14);
					sheet.addCell(label15);
					sheet.addCell(label16);
					sheet.addCell(label17);
					sheet.addCell(label18);
					sheet.addCell(label19);
					sheet.addCell(label110);
					sheet.addCell(label111);
					sheet.addCell(label112);
					sheet.addCell(label113);
					sheet.addCell(label114);
				}

			
					// 将格子放入工作簿中
					sheet.addCell(label01);
					sheet.addCell(label02);
					sheet.addCell(label03);
					sheet.addCell(label04);
					sheet.addCell(label05);
					sheet.addCell(label06);
					sheet.addCell(label07);
					sheet.addCell(label08);
					sheet.addCell(label09);
					sheet.addCell(label010);
					sheet.addCell(label011);
					sheet.addCell(label012);
					sheet.addCell(label013);
					sheet.addCell(label014);
					
					book.write();
					book.close();
				} catch (Exception e) {
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
				System.out.println("加载完毕");
				out.flush();
				out.close();
				input.close();
			}
		}
	}

}
