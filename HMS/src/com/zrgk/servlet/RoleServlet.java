package com.zrgk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgk.bean.MenuBean;
import com.zrgk.bean.RoleBean;
import com.zrgk.service.RoleService;
import com.zrgk.serviceImpl.RoleServiceImpl;
import com.zrgk.util.PartPage;

/**
 * 角色操作的Servlet
 * */
public class RoleServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);//调用dopost
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*----------------操作---------------------*/
		
		String method=request.getParameter("method");
		RoleService rsi=new RoleServiceImpl();
		if ("getAllRoles".equals(method)) {
			//得到所有角色信息
			String page=request.getParameter("page");
			if (page==null) {
				page="1";//初始化
			}
			int count=rsi.getCount("");
			int pa=Integer.parseInt(page);
			PartPage p=rsi.getPartPage(count, pa);
			request.setAttribute("page", p);
			request.setAttribute("method","getAllRoles");
			List<RoleBean> list=rsi.getAllRoles(page);
			request.setAttribute("roles", list);
			request.getRequestDispatcher("Role/index.jsp").forward(request, response);
		}else if ("getRoleInfo".equals(method)) {
			//得到制定角色id的信息及菜单资源
			String rid=request.getParameter("rid");
			String page=request.getParameter("page");
			String type=request.getParameter("type");
			String params=request.getParameter("params");
			if (page==null) {
				page="1";//初始化
			}
			//页面弹窗的判断值
			/*String flag=request.getParameter("flag");
			if("true".equals(flag)){
				flag="1";
			}else if("false".equals(flag)){
				flag="2";
			}*/
			List<MenuBean> mb=rsi.getRoleMenu(rid);
			RoleBean rb=rsi.getRoleInfo(rid);
			request.setAttribute("page", page);
			request.setAttribute("mbs", mb);//返回此角色的信息
			request.setAttribute("rb", rb);
			request.setAttribute("params", params);
			request.setAttribute("type", type);
			request.getRequestDispatcher("Role/editRole.jsp").forward(request, response);
		}else if("changeMenus".equals(method)){
			//更改角色的信息及菜单资源
			String menus=request.getParameter("group[]");
			String rname=request.getParameter("title");
			String rstate=request.getParameter("status");
			String rid=request.getParameter("roleid");
			String menu[];
			if(menus.length()==0){
				menu=new String[]{""};
			}else {
				menu=menus.split(",");
			}
			boolean b=rsi.changeMenu(rid, rname, rstate, menu);
			//request.getRequestDispatcher("RoleServlet.do?method=getRoleInfo&rid="+rid+"&flag="+b).forward(request, response);;
			PrintWriter out = response.getWriter();
			int flag = 0;
			if(true==b){
				flag=1;
			}else if(false==b){
				flag=2;
			}
			out.println(flag);
			out.flush();
			out.close();
		}else if ("judgeUnique".equals(method)) {
			//判断唯一性
			String rname=request.getParameter("rname");
			String rid=request.getParameter("rid");
			PrintWriter out = response.getWriter();
			if(rname==""){
				out.println(-1);
			}else{
				int count=rsi.getAllRoles(rname,rid);
				out.println(count);
			}
			out.flush();
			out.close();
		}else if ("deleteRole".equals(method)){
			//删除一个或者多个角色
			String rid=request.getParameter("rid");
			String rids[]=rid.split(",");
			boolean b=rsi.deleteRole(rids);
			int flag=0;
			//如果删除成功
			if (b==true) {
				flag=1;
			}
			PrintWriter out = response.getWriter();
			out.println(flag);
			out.flush();
			out.close();
		}else if("addRole".equals(method)){
			//跳转到添加角色页面
			List<MenuBean> mb=rsi.getMenus();
			request.setAttribute("mbs", mb);
			request.getRequestDispatcher("Role/addRole.jsp").forward(request, response);
		}else if("insertMenu".equals(method)){
			//添加角色信息
			String menus=request.getParameter("group[]");
			String rname=request.getParameter("title");
			String rstate=request.getParameter("status");
			String menu[];
			if(menus.length()==0){
				menu=new String[]{""};
			}else {
				menu=menus.split(",");
			}
			boolean b=rsi.insertRole(rname, rstate, menu);
			int flag=0;
			//如果删除成功
			if (b==true) {
				flag=1;
			}
			PrintWriter out = response.getWriter();
			out.println(flag);
			out.flush();
			out.close();
		}else if("queryRole".equals(method)){
			//角色名查询
			String rname=request.getParameter("rname");
			if(rname!=null&&rname.indexOf(" ")>-1){
				rname=rname.replace( " ", "");
			}
			if (rname==null) {//分页的时候进来
				rname=request.getParameter("params");
			}
			String page=request.getParameter("page");
			if (page==null) {
				page="1";//初始化
			}
			List<RoleBean>list=rsi.queryRole(rname,page);
			int count=rsi.getCount(rname);
			int pa=Integer.parseInt(page);
			PartPage p=rsi.getPartPage(count, pa);
			request.setAttribute("page", p);
			request.setAttribute("menus", list);
			request.setAttribute("roles", list);
			request.setAttribute("rname", rname);
			request.setAttribute("method","queryRole");
			request.getRequestDispatcher("Role/index.jsp").forward(request, response);
		}
	}
}
