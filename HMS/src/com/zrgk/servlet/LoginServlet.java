package com.zrgk.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgk.bean.UserBean;
import com.zrgk.service.LoginService;
import com.zrgk.service.UserService;
import com.zrgk.serviceImpl.LoginServiceImpl;
import com.zrgk.serviceImpl.UserServiceImpl;

/**
 * 登录相关操作的Servlet
 * */
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		LoginService ls=new LoginServiceImpl();
		String method=request.getParameter("method");
		//做登陆的同学请注意，此方法保留，方面后面做其他模块的同学使用
		if ("login".equals(method)) {
			String flag=request.getParameter("flag");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			UserBean ub=ls.login(username, password);
			if(ub!=null&&ub.getU_state()==0){
				request.setAttribute("flag", flag);
				request.getSession().setAttribute("ub", ub);
				request.getRequestDispatcher("MenuServlet.do?method=initMenu").forward(request, response);
			}else if(ub!=null&&ub.getU_state()==1){
				request.setAttribute("error", "此用户已被限制登录!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else {
				request.setAttribute("error", "用户名或者密码错误!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}
		}else if ("loginOut".equals(method)) {
			//退出
			request.getSession().removeAttribute("ub");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if("validate".equals(method)){
			PrintWriter out=response.getWriter();
			String  incode=request.getParameter("inCode");
			String  code=(String)request.getSession().getAttribute("validateCode");
			if(incode.equalsIgnoreCase(code)){
				out.print(true);
			}else{
				out.print(false);
			}
			//out.println(request.getSession().getAttribute("validateCode"));
			//System.out.println(request.getSession().getAttribute("validateCode"));
			out.flush();
			out.close();
		}else if ("checkLoginName".equals(method)) {
			String loginName=request.getParameter("loginName");
			PrintWriter out = response.getWriter();
			int count=ls.getCount(loginName);
			out.println(count);
			out.flush();
			out.close();
			
		}else if("password".equals(method)){
			
			UserBean ub=(UserBean) request.getSession().getAttribute("ub");
			int uid=ub.getU_id();
			//int 准换格式
	        //密码
			String ps = request.getParameter("pass");
			
			
			//service调用方法
			boolean b = ls.passWord(uid,ps);
			if(b){
				//修改成功   
				request.setAttribute("flag", "1");
	        	request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}else{
				//修改失败  
				request.getRequestDispatcher("password.jsp").forward(request, response);
				
			}
		 	}
	}

}
