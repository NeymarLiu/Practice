<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://testTag/lxc" prefix="p" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <title>住院结算--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/style.css" />
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/Js/hospital/account.js"></script>
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
<body onload="test2()">
<input type="hidden" id="js" value="${js }"/>
<form action="AccountServlet.do?type=mohu" method="post" class="definewidth m20" onsubmit="return bing();">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病例号：</td>
        <td><input type="text" name="id" value="${lm.id }" onblur="bing()"/></td>
        <td width="10%" class="tableleft">姓名：</td>
        <td><input type="text" name="name" value="${lm.name }"/></td>
    </tr>
    <tr>
		  <td colspan="4"><center>
            <button type="submit" class="btn btn-primary" type="button">查询</button> 
            <button class="btn btn-primary" type="button" onclick="qingkong()">清空</button> 
			</center>
        </td>
    </tr>
</table>
</form>
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>病历号</th>
        <th>姓名</th>
        <th>押金</th>
        <th>当前余额</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach var="b" items="${lb }">
    <tr >
         	<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${b.beh_id }"></td>
            <td style="vertical-align:middle;">${b.beh_id }</td>
            <td style="vertical-align:middle;">${b.name }</td>
            <td style="vertical-align:middle;">${b.beh_antecedent }</td>
            <td style="vertical-align:middle;">${b.beh_leftmoney }</td>
            <td style="vertical-align:middle;">未结算</td>
            <td style="vertical-align:middle;"><a href="javascript:void(0);" onclick="xinxi(${b.beh_id })">详细信息</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="jiesuan(${b.beh_id })">结算</a></td>
        </tr>
    </c:forEach>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
          <!-- <a href='#' >第一页</a> <a href='#'>上一页</a>     <span class='current'>1</span><a href='#'>2</a><a href='/chinapost/index.php?m=Label&a=index&p=3'>3</a><a href='#'>4</a><a href='#'>5</a>  <a href='#' >下一页</a> <a href='#' >最后一页</a>
		  &nbsp;&nbsp;&nbsp;共<span class='current'>32</span>条记录<span class='current'> 1/33 </span>页
		   -->
		   
		   <p:page pageSize="${page.pageSize }" url="AccountServlet.do?type=${type}&params=${lm.id}&params2=${lm.name}" totalPage="${page.totalPage}" count="${page.count}" nowPage="${page.nowPage}"/>
		   </div>
		 <div>
			<button type="button" class="btn btn-success" id="delPro" onclick="daoChu()">导出Excel</button>
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>

