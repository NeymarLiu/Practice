<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <title>结算详细--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="Js/hospital/account_look.js"></script>

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
<input type="hidden" value="${idd}" id="myid"/>
<input type="hidden" value="${page.count}" id="ex"/>
<table class="table table-bordered table-hover definewidth m10" >

   <thead>
    <tr>
        <th>病历号</th>
        <th>姓名</th>
        <th>收费项目</th>
        <th>收费金额</th>
        <th>收费日期</th>
    </tr>
    </thead>
    <c:forEach var="b" items="${lc }">
    	<tr >
            <td style="vertical-align:middle;"><span id="pid">${b.id }</span></td>
            <td style="vertical-align:middle;">${b.name }</td>
            <td style="vertical-align:middle;">${b.chap_name }</td>
            <td style="vertical-align:middle;">${b.chap_price }</td>
            <td style="vertical-align:middle;">${b.chap_time }</td>
		</tr>
    </c:forEach>
	     
	     
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
          <!-- <a href='#' >第一页</a> <a href='#'>上一页</a>     <span class='current'>1</span><a href='#'>2</a><a href='/chinapost/index.php?m=Label&a=index&p=3'>3</a><a href='#'>4</a><a href='#'>5</a>  <a href='#' >下一页</a> <a href='#' >最后一页</a>
		  &nbsp;&nbsp;&nbsp;共<span class='current'>32</span>条记录<span class='current'> 1/33 </span>页
		   -->
		   <p:page pageSize="${page.pageSize }" url="AccountServlet.do?type=${type}&myid=${idd}" totalPage="${page.totalPage}" count="${page.count}" nowPage="${page.nowPage}"/>
		   </div>
		 <div>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
				<button type="button" class="btn btn-success" onclick="daoChu()">导出excel</button>
		 </div>
		 
		 </th></tr>
  </table>
  
  
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">总花费：</td>
        <td>${ll.huafei} 元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">押金：</td>
        <td> ${ll.yajin} 元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">余额：</td>
        <td>
        <c:if test="${ll.yue >0 }">${ll.yue } 元</c:if>
        <c:if test="${ll.yue <=0 }"><font color="red">余额不足，请充值！</font> </c:if>
        </td>
    </tr>
</table>
  
</body>
</html>
