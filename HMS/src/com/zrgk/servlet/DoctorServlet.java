package com.zrgk.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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
import com.zrgk.bean.DoctorBean;
import com.zrgk.daoImpl.HosregisterManagerImpl;
import com.zrgk.serviceImpl.ReigisterImpl;
import com.zrgk.util.DownloadUtils;
import com.zrgk.util.PartPage;

@SuppressWarnings("serial")
public class DoctorServlet extends HttpServlet {

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

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");*/
		String method=request.getParameter("method");
		HosregisterManagerImpl hmi=new HosregisterManagerImpl();
		//查询医生的个人信息
		ReigisterImpl rei=new ReigisterImpl();
		if("query".equals(method)){
			String now=request.getParameter("page");
			int nowPage;
			if(now != null && !"".equals(now)){
				nowPage=Integer.parseInt(now);
			}else{
				nowPage=1;//第一次初始化
			}
			int start =(nowPage-1)*PartPage.pageSize;
			String sql="select * from doctor limit "+start+","+PartPage.pageSize;
			String sql2="select * from doctor group by d_keshi";
			String sql3="select count(*) from doctor ";//查询总条数
			int count=hmi.getCount(sql3);
			List<DoctorBean> doctor2=hmi.getHosr(sql2);
			List<DoctorBean> doctor=hmi.getHosr(sql);
			int pageNumber=0;
			if(count%PartPage.pageSize==0){
				pageNumber=count/PartPage.pageSize;
			}else{
				pageNumber=count/PartPage.pageSize+1;
			}
			
			//查询到医生信息之后，将信息集合交给页面
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("dCount", count);
			request.setAttribute("pageSize",PartPage.pageSize);
			request.setAttribute("totalPage",pageNumber);
			request.setAttribute("method","query");
			request.getSession().setAttribute("groupkeshi",doctor2 );
			request.getSession().setAttribute("dlist", doctor);
			request.setAttribute("doctorlist", doctor);
			request.getRequestDispatcher("doctor/index.jsp").forward(request, response);
		}
		
		else if("information".equals(method)){
			//查看详情
			int id=-1;
			id=Integer.parseInt(request.getParameter("did"));
			String sql="select * from doctor where d_id="+id;
			List<DoctorBean> doctor=hmi.getHosr(sql);
			//查询到该医生的详细信息
			request.setAttribute("doctor",doctor);
			request.getRequestDispatcher("doctor/look.jsp").forward(request, response);
		}
		
		else if("updated".equals(method)){
			String did=request.getParameter("did");
			String sql="select * from doctor where d_id="+did;
			String sql1="select * from doctor group by d_xueli";
			List<DoctorBean> doctor2=hmi.getHosr(sql1);
			List<DoctorBean> doctor=hmi.getHosr(sql);
			@SuppressWarnings("unchecked")
			List<DoctorBean> doctor3=(List<DoctorBean>) request.getSession().getAttribute("groupkeshi");
			for (int i = 0; i < doctor3.size(); i++) {
				String keshi1=doctor.get(0).getD_keshi();
				String keshi2=doctor3.get(i).getD_keshi();
				if(keshi1.equals(keshi2)){
					doctor3.get(i).setSta(1);
				}
			}
			for (int j = 0; j < doctor2.size(); j++) {
				String xueli1=doctor2.get(j).getD_xueli();
				String xueli2=doctor.get(0).getD_xueli();
				if(xueli1.equals(xueli2)){
					doctor2.get(j).setXsta(1);
				}
			}
			request.setAttribute("gDoctor", doctor2);
			request.setAttribute("doctor", doctor.get(0));
			request.getRequestDispatcher("doctor/edit.jsp").forward(request, response);
		}
		else if("select".equals(method)){
			//模糊查询
			String name=request.getParameter("pname");//取到查询名字的信息
			String code=request.getParameter("pcode");//取到查询医生编号的信息
			String keshi=request.getParameter("pkeshi");//取到查询医生所属科室的信息
			String now=request.getParameter("page");
			int nowPage;
			if(now != null && !"".equals(now)){
				nowPage=Integer.parseInt(now);
			}else{
				nowPage=1;//第一次初始化
			}
			int start =(nowPage-1)*PartPage.pageSize;
			
			//调取拼接sql语句的方法
			int pageNumber=0;
			//查询count记录数
			int count=hmi.selectCount(name, code, keshi);
			if(count%PartPage.pageSize==0){
				pageNumber=count/PartPage.pageSize;
			}else{
				pageNumber=count/PartPage.pageSize+1;
			}
			List<DoctorBean> doclist=hmi.selectDoctor(name,code,keshi,start,PartPage.pageSize);
			request.setAttribute("doctorlist", doclist);
			
			//查询到医生信息之后，将信息集合交给页面
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("dCount", count);
			request.setAttribute("pageSize",PartPage.pageSize);
			request.setAttribute("totalPage",pageNumber);
			request.setAttribute("method","select");
			request.setAttribute("pname", name);
			request.setAttribute("pcode", code);
			request.setAttribute("pkeshi", keshi);
			request.getRequestDispatcher("doctor/index.jsp").forward(request, response);
		}
		else if("dup".equals(method)){
			//修改数据
			String did=request.getParameter("did");//id
			String name=request.getParameter("pname");//名字
			String card=request.getParameter("pcard");//身份证
			String phone=request.getParameter("pphone");//手机号码
			String tel=request.getParameter("ptel");//座机号码
			String sex=request.getParameter("psex");//性别
			String age=request.getParameter("page");//年龄
			String year=request.getParameter("ptime");//出生年月
			String email=request.getParameter("pemail");//邮箱
			String keshi=request.getParameter("pkeshi");//所属科室
			String sql="update doctor set d_name='"+name+"',d_idcar='"+card+"',d_telphone='"+phone+"',d_phone='"+tel+"',d_sex='"+sex+"',d_age="+age+",d_intime='"+year+"',d_email='"+email+"',d_keshi='"+keshi+"' where d_id="+did;
			boolean boo=hmi.addHosr(sql);
			if(boo){
				//修改状态数据成功，返回登录页面
				response.sendRedirect("DoctorServlet.do?method=query");
			}
			else{
				//修改状态数据失败，加载错误页面
				System.out.println("修改失败");
			}
		}
		/*else if("yan1".equals(method)){
			//验证身份证号是否合法
			//int count=0;
			String idcard=request.getParameter("idcard");
			char card[]=idcard.toCharArray();
			for (int i = 0; i < card.length; i++) {
				if((byte)card[0] == 48){
					break;
				}else if(((byte)card[card.length-1] < 48 || (byte)card[card.length-1] >57) && (byte)card[card.length-1] != 120){//第一位数不为0,判断最后一位是否为数字
					break;
				}else if(i>=0 && i<=card.length-1){//判断其他位是否为数字
					if(i>=0 && i<card.length-1){
						if((byte)card[i] >= 48 && (byte)card[i] <= 57){
							//为数字
							count++;
						}
						else{
							break;
						}
					}else{
						if((byte)card[i] == 120 || ((byte)card[i] >= 48 && (byte)card[i] <= 57)){
							count++;
						}
						else{break;}
					}
					
				}
			}if(count == card.length){
				//count等于card。length表明所有为是的数据都合格，即身份证合法
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
			boolean boo=rei.selDoctor(idcard);//true不存在false存在
			if(boo){
				//不存在
				PrintWriter out=response.getWriter();
				out.print("true");
				out.flush();
				out.close();
			}else{//存在
				PrintWriter out=response.getWriter();
				out.print("false");
				out.flush();
				out.close();
			}
		}*/
		
		/*else if("yan2".equals(method)){
			int count=0;
			String phone=request.getParameter("phone");
			char ph[]=phone.toCharArray();
			for (int i = 0; i < ph.length; i++) {
				if((byte)ph[0] != 49){
					break;
				}else if(((byte)ph[ph.length-1] < 48 || (byte)ph[ph.length-1] >57) && (byte)ph[ph.length-1] != 120){//第一位数不为0,判断最后一位是否为数字
					break;
				}else if(i>=0 && i<=ph.length-1){//判断其他位是否为数字
					
						if((byte)ph[i] >= 48 && (byte)ph[i] <= 57){
							//为数字
							count++;
						}
						else{
							break;
						}
				}
			}if(count == ph.length){
				//count等于card。length表明所有为是的数据都合格，即身份证合法
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
		}
		
		else if("yan3".equals(method)){
			//验证座机号码是否合法
			int count=0;
			String tphone=request.getParameter("zphone");
			String s[]=tphone.split("-");
			String qu=s[0];
			String number=s[1];
			char q[]=qu.toCharArray();
			char n[]=number.toCharArray();
			for (int i = 0; i < q.length; i++) {
				//取出区号中的每一个数
				if((byte)q[0] != 48){//判断第一位数是否不为0
					break;
				}else if(q.length != 3 && q.length != 4){
					break;
				}else if((byte)q[i] >= 48 && (byte)q[i] <= 57){
					//区号合法，再判断电话号码是否合法
					for (int j = 0; j < n.length; j++) {
						if(n[0] == 48){
							break;
						}else if(n.length != 7 && n.length != 8){
							break;
						}else if((byte)n[j] >= 48 && (byte)n[j] <= 57){
							//为数字，count+1
							count++;
						}else{break;}
					}
				}
				
			}
			if(count == q.length*n.length){
				//表明所有的字符都合法
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
		}
		
		else if("yan4".equals(method)){
			String email=request.getParameter("email");
			String QQEmail="[1-9]\\d{4,9}@qq.com";
			String sinaMail="[a-zA-Z]\\w{10}@sina.com";
			if(email.matches(QQEmail)){
				PrintWriter out=response.getWriter();
				out.print("true");
				out.flush();
				out.close();
			}else if(email.matches(sinaMail)){
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
		}*/else if("addInfor".equals(method)){
			//添加医生信息
			String name=request.getParameter("pname");
			//name=new String(name.getBytes("ISO-8859-1"),"utf-8");
			String idcard=request.getParameter("pcard");
			String phone=request.getParameter("pphone");
			String tel=request.getParameter("ptel");
			String sex=request.getParameter("psex");
			//sex=new String(sex.getBytes("ISO-8859-1"),"utf-8");
			String year=request.getParameter("pyear");
			String age=request.getParameter("page");
			String email=request.getParameter("pemail");
			//email=new String(email.getBytes("ISO-8859-1"),"utf-8");
			String keshi=request.getParameter("keshi");
			//keshi=new String(keshi.getBytes("ISO-8859-1"),"utf-8");
			String xueli=request.getParameter("xueli");
			//xueli=new String(xueli.getBytes("ISO-8859-1"),"utf-8");
			String desc=request.getParameter("desc");
			//desc=new String(desc.getBytes("ISO-8859-1"),"utf-8");
			String time=request.getParameter("time");
			if(time == null || "".equals(time)){
				Date d = new Date();
				String s = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat sdf = new SimpleDateFormat(s);
				time = sdf.format(d);
				System.out.println(time);
			}
			String sql="insert into doctor values(default,1,'"+name+"','"+idcard+"','"+phone+"','"+tel+"','"+sex+"','"+year+"',"+age+",'"+email+"','"+keshi+"','"+xueli+"','"+desc+"','"+time+"',0)";
			boolean boo=hmi.addHosr(sql);
			if(boo){
				response.sendRedirect("doctor/add.jsp");
			}else{System.out.println("失败");}
		}else if("selDoctor".equals(method)){
			//查询医生科室
			String sql2="select * from doctor group by d_keshi";
			List<DoctorBean> doctor=hmi.getHosr(sql2);
			request.setAttribute("groupkeshi", doctor);
			request.getRequestDispatcher("doctor/add.jsp").forward(request, response);
		}else if("excal".equals(method)){
			//导出excel
			String agent = request.getHeader("user-agent");
			String dr_id = request.getParameter("allId");
			//查询被选中的用户
			List<DoctorBean> list = rei.putExcalDoctor(dr_id);
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
				Label label01 = new Label(0, 0, "医生id");
				Label label02 = new Label(1, 0, "医生姓名");
				Label label03 = new Label(2, 0, "身份证号");
				Label label04 = new Label(3, 0, "手机号码");
				Label label05 = new Label(4, 0, "座机号码");
				Label label06 = new Label(5, 0, "性别");
				Label label07 = new Label(6, 0, "生日");
				Label label08 = new Label(7, 0, "年龄");
				Label label09 = new Label(8, 0, "邮箱");
				Label label010 = new Label(9, 0, "所属科室");
				Label label011 = new Label(10, 0, "学历");
				Label label012 = new Label(11, 0, "描述");
				Label label013 = new Label(12, 0, "入职时间");
				Label label014 = new Label(13, 0, "状态");
				// 第3行第2列
				try {
				for (int i = 0; i < list.size(); i++) {
					DoctorBean db = list.get(i);
					Label label11 = new Label(0, i + 1, db.getD_id()+"");
					Label label12 = new Label(1, i + 1, db.getD_name());
					Label label13 = new Label(2, i + 1, db.getD_idcar());
					Label label14 = new Label(3, i + 1, db.getD_telphone());
					Label label15 = new Label(4, i + 1, db.getD_phone());
					Label label16 = new Label(5, i + 1, db.getD_sex());
					Label label17 = new Label(6, i + 1, db.getD_birthday()+"");
					Label label18 = new Label(7, i + 1, db.getD_age()+"");
					Label label19 = new Label(8, i + 1, db.getD_email());
					Label label110 = new Label(9, i + 1, db.getD_keshi());
					Label label111 = new Label(10, i + 1, db.getD_xueli());
					Label label112 = new Label(11, i + 1, db.getD_desc());
					Label label113 = new Label(12, i + 1, db.getD_intime());
					Label label114 = new Label(13, i + 1, db.getD_state()+"");
		
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
