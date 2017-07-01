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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/style.css" />
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/common.js"></script>
 	<script type="text/javascript" src="<%=basePath%>/Js/Resource/index.js"></script>
 	<script type="text/javascript" src="<%=basePath%>/Js/ajax.js"></script>
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
<input type="hidden" value="${page.nowPage}" name="page">
<input type="hidden" value="${method}" name="type">
<input type="hidden" value="${mname}" name="params">
<form class="form-inline definewidth m20" action="MenuServlet.do?method=queryMenu" method="post">    
    资源(菜单)名称：
    <input type="text" name="mname" id="username"class="abc input-default" placeholder="" value="${mname}">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
    	<th width="5%"><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>资源名称</th>
        <th>路径Url</th>
        <th>是否有效</th>
        <th  width="10%">操作</th>
    </tr>
    </thead>
         <c:forEach var="menu" items="${menus}">
          <tr><td style="vertical-align:middle;">
         <input type="checkbox" name="check" value="${menu.m_id}"></td>
           <td>${menu.m_name}</td>
           <td>${menu.m_url}</td>
           <td>${menu.m_state==0?"启用":"禁用"}</td>
           <td>
           <a href="javascript:void(0)" onclick="edit(${menu.m_id})">编辑</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="deleteMenu(${menu.m_id})">删除</a>             
          </td></c:forEach>  </tr>
      	
</table>
<table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
  		<p:page pageSize="${page.pageSize }" url="MenuServlet.do?method=${method}&params=${mname}" totalPage="${page.totalPage}" count="${page.count}" nowPage="${page.nowPage}"/></div>
         <div><button type="button" class="btn btn-success" id="newNav">添加资源</button>&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-success" id="delPro" onClick="delAll();">删除选中</button></div></th></tr>
  </table>
</body>
</html>