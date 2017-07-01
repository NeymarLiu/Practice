<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/User/index.js"></script>
    <script type="text/javascript" src="Js/ajax.js"></script>
   	<script type="text/javascript" src="Js/zDialog.js"></script>
	<script type="text/javascript" src="Js/zDrag.js"></script>
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
</head>
<body>
<input type="hidden" value="${ub.u_id}" id="user">
<input type="hidden" value="${page.nowPage}" name="page">
<input type="hidden" value="${method}" name="type">
<input type="hidden" value="${uname}" name="params">
<form class="form-inline definewidth m20" action="UserServlet.do?method=fuzzyUser" method="post">    
    用户名称：
    <input type="text" name="uname" id="username"class="abc input-default" placeholder="" value="${uname }">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
    	<th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>用户名</th>
        <th>真实姓名</th>
        <th>身份证号</th>
        <th>性别</th>
        <th>邮箱</th>
        <th>住址</th>
        <th>创建时间</th>
        <th>状态</th>
        <th>角色</th>
        <th  width="10%">操作</th>
    </tr>
    </thead>
    	<c:forEach var="user" items="${users}">
	     <tr>
         	<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${user.u_id}"></td>
            <td>${user.u_loginname}</td>
            <td>${user.u_truename}</td>
            <td>${user.u_idcard}</td>
            <td>${user.u_sex==0?"男" :"女"}</td>
            <td>${user.u_email}</td>
            <td>${user.u_address}</td>
            <td>${user.u_createtime}</td>
            <td>${user.u_state==0? "启用" : "禁用"}</td>
           <td><c:forEach var="role" items="${roles}">
           		<c:if test="${ user.u_role==role.r_id}">${role.r_name}</c:if>
           </c:forEach></td>
            <td>
                <a href="javascript:void(0);" onclick="edit(${user.u_id})">编辑</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleteUser(${user.u_id})">删除</a>             
            </td>
        </tr>
        </c:forEach>	
</table>
<table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
  	<p:page pageSize="${page.pageSize }" url="UserServlet.do?method=${method}&params=${uname}" totalPage="${page.totalPage}" count="${page.count}" nowPage="${page.nowPage}"/></div>
    <div><button type="button" class="btn btn-success" id="newNav">添加用户</button>&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button></div></th></tr>
  </table>
</body>
</html>