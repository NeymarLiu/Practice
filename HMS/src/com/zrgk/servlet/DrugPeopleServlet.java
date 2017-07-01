package com.zrgk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zrgk.bean.DrugPage;
import com.zrgk.bean.DrugPeopleBean;
import com.zrgk.service.DrugPeopleService;
import com.zrgk.serviceImpl.DrugPeopleServiceImpl;
/**
 * 住院发药
 * @author 任兵
 *
 */
@SuppressWarnings("serial")
public class DrugPeopleServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		DrugPeopleService dps=new DrugPeopleServiceImpl();
		String method=request.getParameter("method");
		if("lookDrug".equals(method)){
			//详情
			String id= request.getParameter("bid");
			String np= request.getParameter("nowpage");
			int nowpage=0;
			if(np ==null || np == ""){
				nowpage = 1;
			}else{
				nowpage = Integer.parseInt(np);
			}
			int count=dps.lookDrugPeople(id);
			DrugPage dp = new DrugPage(count,nowpage);
			List<DrugPeopleBean> list=dps.lookNowpageDrugPeople(nowpage, id);
			request.setAttribute("dplist", list);
			request.setAttribute("dp", dp);
			int pageMax = dp.getCount()%dp.getSize()==0?dp.getCount()/dp.getSize():(dp.getCount()/dp.getSize()+1);
			request.setAttribute("pageMax",pageMax);
			request.setAttribute("id", id);
			request.getRequestDispatcher("hospital/dispensing-look.jsp").forward(request, response);
		}else if("giveDrugChooce".equals(method)){
			//发药选择
			String id= request.getParameter("bid");
			List<DrugPeopleBean> list=dps.giveDrugChooce(id);
			request.setAttribute("dplist", list);
			request.getRequestDispatcher("hospital/dispensing-give.jsp").forward(request, response);
		}else if("giveDrugNumber".equals(method)){
			String beh_id=request.getParameter("bid");
			String dr_id=request.getParameter("dname");
			//得到未发药品数量
			double number = dps.getDrugNumber(beh_id, dr_id);
			//得到病人的余额
			double beh_leftmoney = dps.getLeftmoney(beh_id);
			//得到该药品的售价
			double outprise = dps.getOutPrise(dr_id);
			//储存到session中，为发药时用
			request.setAttribute("beh_id", beh_id);
			request.setAttribute("dr_id", dr_id);
			List<Double> listD = new ArrayList<Double>();
			listD.add(number);
			listD.add(beh_leftmoney);
			listD.add(outprise);
			if(listD != null){
				response.setContentType("txt/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
				String json = gson.toJson(listD).toString();
				out.print(json);
					//关闭流
				out.flush();
				out.close();
			}
		}else if("giveDrug".equals(method)){
			//发药
			String n=request.getParameter("number");
			int number=Integer.parseInt(n);
			String beh_id=request.getParameter("bid");
			String dr_id=request.getParameter("dname");
			String road = request.getParameter("road");
			Date d = new Date();
			String s = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(s);
			String time = sdf.format(d);
			//得到病人的余额
			double beh_hadmoney = dps.getLeftmoney(beh_id);
			//得到该药品的售价
			double outprise = dps.getOutPrise(dr_id);
			//计算出发药之后的余额
			double beh_leftmoney = beh_hadmoney - number*outprise;
			//判断药品数量是否足够,得到药品数量与发药数量比较
			int DrugNumber = dps.getDrugHadNumber(Integer.parseInt(dr_id));
			if(DrugNumber>=number){
				DrugNumber = DrugNumber-number;
				if(beh_leftmoney>=0){
					//病人余额足够，可以发药
					//扣取病人余额
			boolean b =dps.updateLeftmoney(Integer.parseInt(beh_id),beh_leftmoney);
			if(b){
				//病人余额扣除成功，减少库存
			boolean b1 = dps.updateDrugNumber(dr_id, number);
			if(b1){
			DrugPeopleBean dpb=new DrugPeopleBean(Integer.parseInt(beh_id),Integer.parseInt(dr_id),time);
			boolean b2=dps.updateDrugPeople(dpb, number);
			if(b2){
				request.setAttribute("fayao", "1");
			}else{
				boolean bb = dps.updateLeftmoney(Integer.parseInt(beh_id),beh_hadmoney);
				boolean bool2 = dps.updateDrugNumber(dr_id,DrugNumber);
				if(bb){//病人押金归还成功
					if(bool2){
						//病人押金归还成功,药品库存退还成功
						request.setAttribute("fayao", "3");
					}else{
						//病人押金归还成功,药品库存退还失败
						request.setAttribute("fayao", "8");
					}
					
				}else{
					if(bool2){
						//病人押金归还失败,药品库存退还成功
						request.setAttribute("fayao", "9");
					}else{
						//病人押金归还失败,药品库存退还失败
						request.setAttribute("fayao", "10");
					}
				}
			}
			}else{
				//药品扣除失败，归还病人押金
				boolean bb = dps.updateLeftmoney(Integer.parseInt(beh_id),0);
				if(bb){//病人押金归还成功
					request.setAttribute("fayao", "3");
				}else{
					//病人押金归还失败
					request.setAttribute("fayao", "7");
				}
				}
			}else{
				//病人余额扣除失败，重新操作
				request.setAttribute("fayao", "4");
			}
			}else{
				//病人余额不足提醒病人交钱
				request.setAttribute("fayao", "5");
			}
			}else{
				//库存不足，提醒添加库存
				request.setAttribute("fayao", "6");
			}
			if("tosend1".equals(road)){
				List<DrugPeopleBean> dplist = dps.giveDrugChooce(beh_id);
				request.setAttribute("dplist", dplist);
				request.getRequestDispatcher("hospital/dispensing-give.jsp").forward(request, response);
			}else if("tosend2".equals(road)){
				String hosr_id = request.getParameter("pid");
				String np = request.getParameter("nowpage");
				int nowpage=0;
				if(np ==null || np == ""){
					nowpage = 1;
				}else{
					nowpage = Integer.parseInt(np);
				}
				//得到这个病人的所有药品
					int count = dps.lookDrugPeople(hosr_id);
					//分页查询
					DrugPage dp = new DrugPage(count,nowpage);
					List<DrugPeopleBean> list=dps.lookNowpageDrugPeople(nowpage, hosr_id);
					request.setAttribute("dplist", list);
					request.setAttribute("dp", dp);
					int pageMax = dp.getCount()%dp.getSize()==0?dp.getCount()/dp.getSize():(dp.getCount()/dp.getSize()+1);
					request.setAttribute("pageMax",pageMax);
					request.setAttribute("id", hosr_id);
					request.getRequestDispatcher("DrugPeopleServlet.do?method=lookDrug&id="+beh_id).forward(request, response);
			}
			
		}else if("givesDrugChooce".equals(method)){
			//批量发药选择
			String id=request.getParameter("hid");
			String name=request.getParameter("hname");
			List<DrugPeopleBean> names=dps.givesDrugChooce(id);
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("names", names);
			request.getRequestDispatcher("hospital/dispensing-gives.jsp").forward(request, response);
		}else if("givesDrugNumber".equals(method)){
			String beh_id = request.getParameter("beh_id");
			String dr_id = request.getParameter("dr_id");
			//得到这些病人这个药品最少发药数量，
			double needNumber = dps.getDrugNumber(beh_id,dr_id);
			response.setContentType("txt/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(needNumber);
			out.flush();
			out.close();
		}else if("givesDrug".equals(method)){
			//批量发药     
			String ids = request.getParameter("hid");
			String dr_id = request.getParameter("drname");
			String number = request.getParameter("number");
			String name = request.getParameter("hname");
			//处理beh_id，得到数组
			String id[] = ids.split(",");
			int drid = 0;
			if(dr_id != null){
				drid = Integer.parseInt(dr_id);
			}
			int givesNumber = 0;
			if(number != null){
				givesNumber = Integer.parseInt(number);
			}
			//得到批量发发药所需的药品数量
			int allNumber =id.length * givesNumber;
			//得到药品库存
			int DrugNumber =  dps.getDrugHadNumber(drid);
			//得到药品售价
			double outprise = dps.getOutPrise(dr_id);
			//发药所需押金
			double prise = outprise*givesNumber;
			//计算出批量发该数量的药品需要多少费用，然后再从数据库的到这些病人的费用，跟这个费用比较，
			
			//发药的时间
			Date d = new Date();
			String ss = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(ss);
			String time = sdf.format(d);
			//定义一个集合，用来储存扣费是否成功，发药是否成功，集合里面为病人（id+操作详情，如，发药成功，扣费失败）
			List<String> list = new ArrayList<String>();
			String s = "";
				//如果出现低于这个的，则不发药，并且提醒病人叫押金,
			//判断药品库存是否足够
			if(DrugNumber>allNumber){
				//药品库存充足，循环病人id的数组，
				for (int i = 0; i < id.length; i++) {
					double beh_hadmoney = dps.getLeftmoney(id[i]);
					double beh_leftmoney = beh_hadmoney - prise;
					if(beh_leftmoney>=0){
						//该病人押金充足,扣取病人押金
						boolean b = dps.updateLeftmoney(Integer.parseInt(id[i]), beh_leftmoney);
						if(b){
							//扣取病人押金成功,去减少库存数量
							boolean bool = dps.updateDrugNumber(dr_id, givesNumber);
							if(bool){
								DrugPeopleBean dpb =new DrugPeopleBean(Integer.parseInt(id[i]),Integer.parseInt(dr_id),time);
								boolean c = dps.updateDrugPeople(dpb, givesNumber);
								if(c){
									//修改成功，，，发药成功
									s+="发药成功";
									list.add(s);
								}else{
									//修改失败，退还药品数量，退还病人押金
									boolean bb = dps.updateLeftmoney(Integer.parseInt(id[i]),beh_hadmoney);
									boolean bool2 = dps.updateDrugNumber(dr_id,DrugNumber);
									s+="发药失败，已退还药品数量，已退还病人押金";
									list.add(s);
								}
							}else{
								//减少库存数量失败，退还病人押金
								boolean bb = dps.updateLeftmoney(Integer.parseInt(id[i]),0);
								s+="减少库存数量失败，已退还病人押金";
								list.add(s);
							}
						}else{
							//扣取病人押金失败
							s+="扣取病人押金失败";
							list.add(s);
						}
					}else{
						//该病人押金不足，提醒交钱
						s+="病人余额不足，提醒病人去交钱";
						list.add(s);
					}
				}
			}else{
				s+="药品库存不足，添加药品库存";
				list.add(s);
			}
			List<DrugPeopleBean> names=dps.givesDrugChooce(ids);
			if(list.size() != 0){
				request.setAttribute("listSS", list);
			}else{
				request.setAttribute("listSS", "1");
			}
			request.setAttribute("id", id);//储存批量操作的病人id
			request.setAttribute("name", name);//储存批量操作的病人姓名
			request.setAttribute("names", names);
			request.getRequestDispatcher("hospital/dispensing-gives.jsp").forward(request, response);
		} else if("getAllDrugPeople".equals(method)) {
			//查询所有 模糊查询
			String name= request.getParameter("pname");
			String s=request.getParameter("sss");
			if(s!=null){
				if(name != "" && name != null){
					name = new String(name.getBytes("ISO-8859-1"),"utf-8");
				}
			}
			String id= request.getParameter("pid");
				if(id != "" && id != null){
					id = new String(id.getBytes("ISO-8859-1"),"utf-8");
				}
			int count=dps.getAllDrugPeople(id, name);
			String np= request.getParameter("nowpage");
			int nowpage=0;
			if(np ==null || np == ""){
				nowpage = 1;
			}else{
				nowpage = Integer.parseInt(np);
			}
			DrugPage dp = new DrugPage(count,nowpage);
			List<DrugPeopleBean> list= dps.getNowpageDrugPeople(nowpage, id, name);
			request.setAttribute("dplist", list);
			request.setAttribute("dp", dp);
			int pageMax = dp.getCount()%dp.getSize()==0?dp.getCount()/dp.getSize():(dp.getCount()/dp.getSize()+1);
			request.setAttribute("pageMax",pageMax);
			request.setAttribute("name", name);
			request.setAttribute("id", id);
			request.getRequestDispatcher("hospital/dispensing.jsp").forward(request, response);
		}
	}

}
