package com.zrgk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgk.bean.MenuBean;
import com.zrgk.bean.RoleBean;
import com.zrgk.bean.UserBean;
import com.zrgk.service.MenuService;
import com.zrgk.serviceImpl.MenuServiceImpl;
import com.zrgk.util.PartPage;

/**
 * 菜单资源操作的Servlet
 * */
public class MenuServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置响应请求编码格式
		
		MenuService ms=new MenuServiceImpl();
		String method=request.getParameter("method");
		if ("initMenu".equals(method)) {//避免空指针
			UserBean ub=(UserBean)request.getSession().getAttribute("ub");
			List<MenuBean>list=ms.getMenu(ub.getU_id());
			request.setAttribute("list", list);
			//请求转发
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if ("getAllMenus".equals(method)){
			//得到所有可用菜单
			String page=request.getParameter("page");
			if (page==null) {
				page="1";//初始化
			}
			List<MenuBean>list=ms.getMenuList(page);
			int count=ms.getCount("");
			int pa=Integer.parseInt(page);
			PartPage p=ms.getPartPage(count, pa);
			request.setAttribute("page", p);
			request.setAttribute("menus", list);
			request.setAttribute("method","getAllMenus");
			request.getRequestDispatcher("Resource/index.jsp").forward(request, response);
		}else if("getMenuInfo".equals(method)){
			//根据菜单id返回相应记录
			String mid=request.getParameter("mid");
			String page=request.getParameter("page");
			String params=request.getParameter("params");
			String type=request.getParameter("type");
			MenuBean mb=ms.getMenuInfo(mid);
			request.setAttribute("mb", mb);
			request.setAttribute("page", page);
			request.setAttribute("type", type);
			request.setAttribute("params", params);
			request.getRequestDispatcher("Resource/edit.jsp").forward(request, response);
		}else if ("updateMenu".equals(method)) {
			//更新菜单信息
			String mname=request.getParameter("mName");
			String murl=request.getParameter("mUrl");
			String mstate=request.getParameter("mState");
			String mid=request.getParameter("mId");
			boolean b=ms.updateMenuInfo(mid, mname, murl, mstate);
			int flag=-1;
			if (b==true) {
				flag=1;
			}else {
				flag=2;
			}
			PrintWriter out = response.getWriter();
			out.println(flag);
			out.flush();
			out.close();
		}else if("judgeUnique".equals(method)){
			//判断菜单名称的唯一性
			String mid=request.getParameter("mid");
			String mname=request.getParameter("mname");
			PrintWriter out = response.getWriter();
			if(mname==""){//当为空时
				out.println(-1);
			}else{
				int count=ms.getAllMenus(mname,mid);
				out.println(count);
			}
			out.flush();
			out.close();
		}else if("deleteMenu".equals(method)){
			//删除一个或者多个角色
			String mid=request.getParameter("mid");
			System.out.println(mid);
			String mids[]=mid.split(",");
			boolean b=ms.deleteMenu(mids);
			int flag=0;
			//如果删除成功
			if (b==true) {
				flag=1;
			}
			PrintWriter out = response.getWriter();
			out.println(flag);
			out.flush();
			out.close();
		}else if ("insertMenu".equals(method)) {
			//添加新菜单
			String mname=request.getParameter("mname");
			String murl=request.getParameter("murl");
			String mstate=request.getParameter("mstate");
			boolean b=ms.insertMenu(mname, murl, mstate);
			int flag=-1;
			if (b==true) {
				flag=1;
			}else {
				flag=2;
			}
			PrintWriter out = response.getWriter();
			out.println(flag);
			out.flush();
			out.close();
			
		}else if ("queryMenu".equals(method)) {
			//模糊查询
			String mname=request.getParameter("mname");
			if(mname!=null&&mname.indexOf(" ")>-1){
				mname=mname.replace( " ", "");
			}
			if (mname==null) {//分页的时候进来
				mname=request.getParameter("params");
			}
			String page=request.getParameter("page");
			if (page==null) {
				page="1";//初始化
			}
			List<MenuBean>list=ms.getMenuList(mname,page);
			int count=ms.getCount(mname);
			int pa=Integer.parseInt(page);
			PartPage p=ms.getPartPage(count, pa);
			request.setAttribute("menus", list);
			request.setAttribute("mname", mname);
			request.setAttribute("page", p);
			request.setAttribute("method","queryMenu");
			request.getRequestDispatcher("Resource/index.jsp").forward(request, response);
			
		}
	}

}
