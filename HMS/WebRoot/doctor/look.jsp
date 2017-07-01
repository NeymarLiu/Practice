<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.util.List" %>
<%@page import="com.zrgk.bean.DoctorBean" %>
<%@page import="com.zrgk.bean.HosrBean" %>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<%@  taglib  uri="http://testTag/lxc"   prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <title>查看--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/ckeditor/ckeditor.js"></script>
 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
				window.location.href="doctor/index.jsp";
		 });
    });
    </script>
</head>
<body>
<%List<DoctorBean> doctor=(List<DoctorBean>)request.getAttribute("doctor"); %>
<form action="DoctorServlet.do?method=query" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td><%=doctor.get(0).getD_name() %></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td>护照</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td><%=doctor.get(0).getD_idcar() %></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">手机</td>
        <td><%=doctor.get(0).getD_phone() %></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">座机</td>
        <td><%=doctor.get(0).getD_telphone() %></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><%=doctor.get(0).getD_sex() %></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td><%=doctor.get(0).getD_age() %></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">出生年月</td>
        <td><%=doctor.get(0).getD_intime() %></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">电子邮箱</td>
        <td><%=doctor.get(0).getD_email() %></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所属科室</td>
        <td><%=doctor.get(0).getD_keshi() %></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">学历</td>
        <td><%=doctor.get(0).getD_xueli() %></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td><%=doctor.get(0).getD_desc() %></td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<a href="DoctorServlet.do?method=query" class="btn btn-success">返回列表</a>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>