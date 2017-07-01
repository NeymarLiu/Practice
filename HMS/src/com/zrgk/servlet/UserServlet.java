package com.zrgk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgk.bean.RoleBean;
import com.zrgk.bean.UserBean;
import com.zrgk.service.RoleService;
import com.zrgk.service.UserService;
import com.zrgk.serviceImpl.RoleServiceImpl;
import com.zrgk.serviceImpl.UserServiceImpl;
import com.zrgk.util.PartPage;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService us=new UserServiceImpl();
		RoleService rs=new RoleServiceImpl();
		String method=request.getParameter("method");
		if ("initUser".equals(method)) {//避免空指针
			//用户首页加载
			String page=request.getParameter("page");
			if (page==null) {
				page="1";//初始化
			}
			int count=us.getCount("");
			int pa=Integer.parseInt(page);
			PartPage p=us.getPartPage(count, pa);
			request.setAttribute("page", p);
			request.setAttribute("method","initUser");
			List<UserBean> list=us.initUser(page);
			List<RoleBean>roles=rs.getAllRoles();
			request.setAttribute("users",list);
			request.setAttribute("roles",roles);
			request.getRequestDispatcher("User/index.jsp").forward(request, response);
		}else if ("editUser".equals(method)) {
			//编辑用户信息
			String uid=request.getParameter("uid");
			String page=request.getParameter("page");
			String type=request.getParameter("type");
			String params=request.getParameter("params");
			UserBean ub=us.getUser(uid);
			List<RoleBean>roles=rs.getAllRolesOrder();
			//携带模糊查询条件以及页数
			request.setAttribute("ub",ub);
			request.setAttribute("roles",roles);
			request.setAttribute("page",page);
			request.setAttribute("type",type);
			request.setAttribute("params",params);
			request.getRequestDispatcher("User/editUser.jsp").forward(request, response);
		}else if ("judgeUnique".equals(method)) {
			//判断用户名唯一性
			String uname=request.getParameter("uname");
			String uid=request.getParameter("uid");
			PrintWriter out = response.getWriter();
			if(uname==""){
				out.println(-1);
			}else{
				int count=us.getAllUsers(uname,uid);
				out.println(count);
			}
			out.flush();
			out.close();
		}else if ("addUser".equals(method)) {
			//跳转到添加用户信息页面
			List<RoleBean>roles=rs.getAllRoles();
			request.setAttribute("roles",roles);
			request.getRequestDispatcher("User/addUser.jsp").forward(request, response);
		}else if("insertUser".equals(method)){
			//添加用户信息
			String loginName=request.getParameter("loginName");
			String pass=request.getParameter("pass");
			String trueName=request.getParameter("trueName");
			String idCard=request.getParameter("idCard");
			String sex=request.getParameter("sex");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			String state=request.getParameter("state");
			String role=request.getParameter("role");
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=sdf.format(date);//当前时间
			boolean b=us.addUser(loginName, pass, trueName, idCard, sex, email, phone, address, time, state, role);
			int flag=-1;
			if (b==true) {
				flag=0;
			}else {
				flag=1;
			}
			PrintWriter out = response.getWriter();
			out.println(flag);
			out.flush();
			out.close();
		}else if("deletetUser".equals(method)){
			//删除一个或者多个用户
			String uids=request.getParameter("uid");
			String uid[]=uids.split(",");
			boolean b=us.deleteUser(uid);
			int flag=0;
			//如果删除成功
			if (b==true) {
				flag=1;
			}
			PrintWriter out = response.getWriter();
			out.println(flag);
			out.flush();
			out.close();
			
		}else if("fuzzyUser".equals(method)){
			//模糊查询
			String uname=request.getParameter("uname");
			if(uname!=null&&uname.indexOf(" ")>-1){
				uname=uname.replace( " ", "");
			}
			if (uname==null) {//分页的时候进来
				uname=request.getParameter("params");
			}
			String page=request.getParameter("page");
			if (page==null) {
				page="1";//初始化
			}
			List<UserBean>list=us.fuzzyUser(uname,page);
			int count=us.getCount(uname);
			int pa=Integer.parseInt(page);
			PartPage p=us.getPartPage(count, pa);
			request.setAttribute("page", p);
			request.setAttribute("uname", uname);
			request.setAttribute("method","fuzzyUser");
			List<RoleBean>roles=rs.getAllRoles();
			request.setAttribute("users",list);
			request.setAttribute("roles",roles);
			request.getRequestDispatcher("User/index.jsp").forward(request, response);
		}else if("updateUser".equals(method)){
			//更新用户信息
			String pass=request.getParameter("pass");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			String address=request.getParameter("address");
			String state=request.getParameter("state");
			String role=request.getParameter("role");
			String uid=request.getParameter("uid");
			boolean b=us.updateUser(pass, phone, address, email, role, state, uid);
			int flag=-1;
			if (b==true) {
				flag=0;
			}else {
				flag=1;
			}
			PrintWriter out = response.getWriter();
			out.println(flag);
			out.flush();
			out.close();
		}else if("getIdCardCount".equals(method)){
			//身份证唯一性验证
			String idCard=request.getParameter("idCard");
			PrintWriter out = response.getWriter();
			if(idCard==""){
				out.println(-1);
			}else{
				int count=us.getIdCardCount(idCard);
				out.println(count);
			}
			out.flush();
			out.close();
		}
	}
}
