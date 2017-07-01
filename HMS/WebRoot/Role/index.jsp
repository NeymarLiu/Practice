<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@  taglib  uri="http://testTag/lxc"   prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色管理首页</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/style.css" />
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/Js/Role/check.js"></script>
	<script type="text/javascript" src="<%=basePath%>/Js/Role/index.js"></script>
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
<input type="hidden" value="${ub.u_role}" id="user">
<input type="hidden" value="${method}" name="type">
<input type="hidden" value="${rname}" name="params">
<input type="hidden" value="${page.nowPage}" name="page">
<form class="form-inline definewidth m20" action="RoleServlet.do?method=queryRole" method="post">  
    角色名称：
    <input type="text" name="rname" id="rolename"class="abc input-default" placeholder="" value="${rname }">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10" >
    <thead>
    <tr>
    	<th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>角色名称</th>
        <th>状态</th>
        <th width="10%">操作</th>
    </tr>
    </thead>
    <c:if test="${roles!=null }">
    <c:forEach var="role" items="${ roles}">
    	<tr>
        <td style="vertical-align:middle;"><input type="checkbox" name="check" value="${role.r_id }"></td>
        <td>${role.r_name}</td>
        <td>${role.r_state==0? "启用":"禁用" }</td>
        <td><a href="javascript:void(0);" onclick="edit(${role.r_id})">编辑</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleteRole(${role.r_id })">删除</a> </td>
      </tr>
    </c:forEach>
    </c:if>
 </table>
        
        
   <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">   <div class="inline pull-right page">
  		<p:page pageSize="${page.pageSize }" url="RoleServlet.do?method=${method}&params=${rname}" totalPage="${page.totalPage}" count="${page.count}" nowPage="${page.nowPage}"/></div>
  		<div><button type="button" class="btn btn-success" id="newNav">添加角色 </button>&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button></div></th></tr>
  </table>     
 </body>
</html>

