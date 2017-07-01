package com.zrgk.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
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

import com.google.gson.Gson;
import com.zrgk.bean.LBeHospBean;
import com.zrgk.bean.LHosrBean;
import com.zrgk.bean.LCanShu;
import com.zrgk.bean.LMoHu;
import com.zrgk.service.LBeHospService;
import com.zrgk.serviceImpl.LBeHospServiceImpl;
import com.zrgk.util.DownloadUtils;
import com.zrgk.util.PartPage;

/**
 * 住院办理
 * 
 * @author 龙汶宇
 * 
 */
public class BeHospitalServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 乱码问题将格式全设置为utf-8
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		LBeHospService bhs = new LBeHospServiceImpl();
		String type = request.getParameter("type");
		if ("lookOneInfos".equals(type)) {
			// 得到参数
			String id = request.getParameter("id");
			// 查询相关比例号的信息
			LHosrBean hb = bhs.getOneInfos(id);
			request.setAttribute("hbList", hb);
			// 返回页面
			request.getRequestDispatcher("hospital/look.jsp").forward(request,
					response);
		} else if ("lookEditInfos".equals(type)) {
			// 跳转到修改页面
			// 得到参数
			String id = request.getParameter("id");
			// 查询相关比例号的信息
			LHosrBean hb = bhs.getOneInfos(id);
			request.setAttribute("tList", hb);
			// 返回页面
			request.getRequestDispatcher("hospital/edit.jsp").forward(request,
					response);
		} else if ("editInfos".equals(type)) {
			// 修改住院者的信息
			// 病例号
			String id = request.getParameter("eid");
			int beh_id = -1;
			if (id != null){
				beh_id = Integer.parseInt(id);
			}
			// 护理人
			String name = request.getParameter("nursepeople");
			// 床位号
			String bed = request.getParameter("patbed");
			int patbed = -1;
			if (bed != null) {
				patbed = Integer.parseInt(bed);
			}
			// 病情
			String illness = request.getParameter("illness");
			LBeHospBean bhb = new LBeHospBean(beh_id, name, patbed, illness);
			// 修改
			boolean b = bhs.updateOneBe(bhb);
			if (b) {
				request.setAttribute("edit", b);
				request.getRequestDispatcher("BeHospitalServlet.do?type=mohu")
						.forward(request, response);
			} else {
				request.setAttribute("edit", b);
				request.getRequestDispatcher(
						"BeHospitalServlet.do?type=lookEditInfos").forward(
						request, response);
			}

		} else if ("lookOneMany".equals(type)) {
			// 跳转到缴纳押金的页面
			String id = request.getParameter("id");
			LCanShu lcan = bhs.getOneMany(id);
			request.setAttribute("lcan", lcan);
			// 返回页面
			request.getRequestDispatcher("hospital/add_many.jsp").forward(
					request, response);

		} else if ("editOneMany".equals(type)) {
			// 充值----缴纳押金
			String id = request.getParameter("id");
			String many = request.getParameter("money");
			if(many != null && !"".equals(many)){
				many=many.replaceAll(" ", "");
			}
			double money = 0;
			if (many != null && many != "") {
				money = Double.parseDouble(many);
			}
			boolean b = bhs.editMany(id, money);
			request.setAttribute("mList", b);
			request.getRequestDispatcher(
					"BeHospitalServlet.do?type=lookOneMany").forward(request,
					response);
		} else if ("addOne".equals(type)) {
			// 根据病例号添加住院信息
			String id = request.getParameter("cid");
			// 查询相关比例号的信息
			List<LHosrBean> list = bhs.addOne(id);
			request.setAttribute("zList", list);
			// 设置响应输出流
			PrintWriter out = response.getWriter();
			if (list == null) {
				out.print("0");
			} else {
				for (LHosrBean hb : list) {
					out.print("<div style='cursor: pointer; background-color: #F0F0F0;' onmouseover='over(this)' onmouseout='out(this)' "
							+"onclick=cil(\'"
							+ hb.getHosr_id()
							+ "\',\'"
							+ hb.getHosr_name()
							+ "\',\'"
							+ hb.getHosr_idcar()
							+ "\',\'"
							+ hb.getHosr_medical()
							+ "\',\'"
							+ hb.getHosr_phone()
							+ "\',\'"
							+ hb.getHosr_selfprice()
							+ "\',\'"
							+ hb.getHosr_sex()
							+ "\',\'"
							+ hb.getHosr_age()
							+ "\',\'"
							+ hb.getHosr_lookdoctor()
							+ "\',\'"
							+ hb.getKeshi()
							+ "\',\'"
							+ hb.getD_name()
							+ "\') >" + hb.getHosr_id() + "</div>");
				}
				out.flush();
				out.close();
			}
		} else if ("tuiyuan".equals(type)) {
			// 退院
			String ids[] = request.getParameterValues("ids");
			String id = "(";
			for (int i = 0; i < ids.length; i++) {
				if (i != ids.length - 1) {
					id = id + ids[i] + ",";
				}
				id = id + ids[i] + ")";
			}
			boolean b2 = bhs.tuiyuan(id);
			request.setAttribute("tui", b2);
			request.getRequestDispatcher("BeHospitalServlet.do?type=mohu")
					.forward(request, response);
		} else if ("chuyuan".equals(type)) {
			// 出院
			String ids[] = request.getParameterValues("ids");
			String id = "(";
			for (int i = 0; i < ids.length; i++) {
				if (i != ids.length - 1) {
					id = id + ids[i] + ",";
				}
				id = id + ids[i] + ")";
			}
			boolean b = bhs.chuyuan(id);
			request.setAttribute("chu", b);
			request.getRequestDispatcher("BeHospitalServlet.do?type=mohu")
					.forward(request, response);
		} else if ("tuiy".equals(type)) {
			String id = "(" + request.getParameter("mid") + ")";
			boolean b = bhs.tuiyuan(id);
			request.setAttribute("tui", b);
			index(request, response, bhs);
		} else if ("chuy".equals(type)) {
			String id = "(" + request.getParameter("mid") + ")";
			boolean b1 = bhs.chuyuan(id);
			request.setAttribute("chu", b1);
			index(request, response, bhs);
		} else if ("tianjia".equals(type)) {
			// 添加住院信息
			tianJia(request, response, bhs);

		} else if ("mohu".equals(type)) {
			// 模糊查询
			moHu(request, response, bhs);
		} else if ("daoChuExcel1".equals(type)) {
			// index导出Excel
			daoChuExcel1(request, response, bhs);
		} else if ("cwh".equals(type)) {
			String cwh = request.getParameter("cid");
			String pbed = request.getParameter("pbed");
			int p = 0;
			if (pbed != null) {
				p = Integer.parseInt(pbed);
			}
			boolean b = false;
			List<LCanShu> list = bhs.getAllPbed(cwh);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBeh_patbed() == p) {
					b = true;
				}
			}
			PrintWriter pw = response.getWriter();
			pw.print(b);
			pw.flush();
			pw.close();
		} else if ("addCWH".equals(type)) {
			//String cwh = request.getParameter("cid");
			String pbed = request.getParameter("pbed");
			int p = 0;
			if (pbed != null) {
				p = Integer.parseInt(pbed);
			}
			boolean b = false;
			List<LCanShu> list = bhs.getAllPbed();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getBeh_patbed() == p) {
					b = true;
				}
			}
			PrintWriter pw = response.getWriter();
			pw.print(b);
			pw.flush();
			pw.close();
		}

	}

	public void index(HttpServletRequest request, HttpServletResponse response,
			LBeHospService bhs) throws ServletException, IOException {
		// 查询所有的住院信息

		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		List<LCanShu> list = bhs.getAllHosr(page);
		int count = bhs.getAllHosr().size();
		int pa = Integer.parseInt(page);
		PartPage p = bhs.getPartPage(count, pa);
		request.setAttribute("page", p);
		request.setAttribute("type", "mohu");
		request.setAttribute("behList", list);
		// 返回页面
		request.getRequestDispatcher("hospital/index.jsp").forward(request,
				response);

	}

	public void moHu(HttpServletRequest request, HttpServletResponse response,
			LBeHospService bhs) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		/*String regex="/\s+/g";
		id=id.replace(regex,"");*/
		if (id == null) {
			id = request.getParameter("params");
		}
		if(id != null && !"".equals(id)){
			id=id.replaceAll(" ", "");
		}
		String doctor = request.getParameter("doctor");
		if (doctor == null) {
			doctor = request.getParameter("params2");
		}
		if(doctor != null && !"".equals(doctor)){
			doctor=doctor.replaceAll(" ", "");
		}
		String keshi = request.getParameter("keshi");
		if (keshi == null) {
			keshi = request.getParameter("params3");
		}
		if(keshi != null && !"".equals(keshi)){
			keshi=keshi.replaceAll(" ", "");
		}
		String qtime = request.getParameter("qtime");
		if (qtime == null) {
			qtime = request.getParameter("params4");
		}
		String htime = request.getParameter("htime");
		if (htime == null) {
			htime = request.getParameter("params5");
		}

		String page = request.getParameter("page");
		if (page == null) {
			page = "1";
		}
		LMoHu lm = new LMoHu(id, doctor, keshi, qtime, htime);
		int count = bhs.moHu(lm).size();
		int pa = Integer.parseInt(page);
		PartPage p = bhs.getPartPage(count, pa);
		request.setAttribute("page", p);
		request.setAttribute("type", "mohu");
		/*request.setAttribute("id", id);
		request.setAttribute("doctor", doctor);
		request.setAttribute("keshi", keshi);
		request.setAttribute("qtime", qtime);
		request.setAttribute("htime", htime);*/
		List<LCanShu> list = bhs.moHu(lm, page);
		request.setAttribute("behList", list);
		request.setAttribute("lm", lm);
		// 返回页面
		request.getRequestDispatcher("hospital/index.jsp").forward(request,
				response);

	}

	public void tianJia(HttpServletRequest request,
			HttpServletResponse response, LBeHospService bhs)
			throws ServletException, IOException {
		// 添加住院信息
		Date date = new Date();
		String s = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(s);
		String time = sdf.format(date);// 入院时间
		String id = request.getParameter("id");// 病例号
		// 通过病例号查询该人是否住过院
		boolean b1 = bhs.selectOne(id);
		int beh_id = -1;
		if (id != null) {
			beh_id = Integer.parseInt(id);
		}
		String nursepeople = request.getParameter("nursepeople");// 护理人
		String patbed = request.getParameter("patbed");// 床位号
		if(patbed != null && !"".equals(patbed)){
			patbed=patbed.replaceAll(" ", "");
		}
		int beh_patbed = -1;
		if (patbed != null) {
			beh_patbed = Integer.parseInt(patbed);
		}
		String money = request.getParameter("money");// 押金
		if(money != null && !"".equals(money)){
			money=money.replaceAll(" ", "");
		}
		double ante = 0;
		if (money != null) {
			ante = Double.parseDouble(money);
		}
		
		String bingq = request.getParameter("illness");// 病情
		boolean b;
		LBeHospBean bhb;
		if (b1) {
			bhb = new LBeHospBean(beh_id, nursepeople, beh_patbed,
					ante, ante, bingq, time);
			// 修改
			b = bhs.updateOne(bhb);
		} else {
			bhb = new LBeHospBean(beh_id, nursepeople, beh_patbed,
					ante, ante, bingq, time);
			b = bhs.tianJia(bhb);
		}
		request.setAttribute("tianjia", b);
		if(b){
			index(request, response, bhs);
		}else{
			request.getRequestDispatcher("hospital/add.jsp").forward(request, response);
		}
		

	}

	public void daoChuExcel1(HttpServletRequest request,
			HttpServletResponse response, LBeHospService bhs)
			throws ServletException, IOException {

		// 火狐的编码是base64 IE的编码是URL
		String agent = request.getHeader("user-agent");
		//
		String ids = request.getParameter("id");
		// 字符串的截取
		LCanShu ub = null;
		List<LCanShu> list = new ArrayList<LCanShu>();
		String id[] = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			if (id[i] != "") {
				ub = (LCanShu) bhs.getSomeInfo(id[i]);
				list.add(ub);
			}
		}

		// 导出excel
		if (!list.isEmpty() && list != null) {
			String path = this.getServletContext().getRealPath("\\excel");
			// String path = request.getServletContext().getRealPath("\\excel");
			// 查询成功
			WritableWorkbook book = Workbook.createWorkbook(new File(path
					+ "/test.xls"));
			// 创建第一页工作簿
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 第一个参数代表列 ，第二个参数代表行
			Label label01 = new Label(0, 0, "病历号");
			Label label02 = new Label(1, 0, "姓名");
			Label label03 = new Label(2, 0, "床位号");
			Label label04 = new Label(3, 0, "联系电话");
			Label label05 = new Label(4, 0, "押金");
			Label label06 = new Label(5, 0, "主治医生");
			Label label07 = new Label(6, 0, "入院时间");
			Label label08 = new Label(7, 0, "科室");
			// 第3行第2列
			try {
				for (int i = 0; i < list.size(); i++) {
					LCanShu u = (LCanShu) list.get(i);
					Label label11 = new Label(0, i + 1, u.getBeh_id() + "");
					Label label12 = new Label(1, i + 1, u.getHosr_name());
					Label label13 = new Label(2, i + 1, u.getBeh_patbed() + "");
					Label label14 = new Label(3, i + 1, u.getHosr_phone());
					Label label15 = new Label(4, i + 1, u.getBeh_antecedent()
							+ "");
					Label label16 = new Label(5, i + 1, u.getD_name());
					Label label17 = new Label(6, i + 1, u.getBeh_time());
					Label label18 = new Label(7, i + 1, u.getKeshi());

					sheet.addCell(label11);
					sheet.addCell(label12);
					sheet.addCell(label13);
					sheet.addCell(label14);
					sheet.addCell(label15);
					sheet.addCell(label16);
					sheet.addCell(label17);
					sheet.addCell(label18);
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
				book.write();
				book.close();
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
			// -------------------

			String fileName2 = null;
			if (agent.contains("Firefox")) {
				// 火狐浏览器 base64的编码处理
				fileName2 = DownloadUtils.base64EncodeFileName("导出信息");
			} else {
				// ie 或者谷歌浏览器URL编码处理
				fileName2 = URLEncoder.encode("导出信息哈哈", "utf-8");
			}

			FileInputStream input = new FileInputStream(new File(path
					+ "/test.xls"));
			OutputStream out = response.getOutputStream();
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileName2 + ".xls");
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
