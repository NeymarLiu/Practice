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
    <title>入院办理--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
	<script type="text/javascript" src="Js/hospital/index.js"></script>
	<script type="text/javascript" src="Js/My97DatePicker/WdatePicker.js"></script>
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
<body onload="test1()">
<input type="hidden" id="chu" value="${chu }"/>
<input type="hidden" id="tui" value="${tui }"/>
<input type="hidden" id="isok" value="${edit}"/>
<input type="hidden" id="tianjia" value="${tianjia}"/>
<form action="BeHospitalServlet.do?type=mohu" method="post" class="definewidth m20" onsubmit="return shijian();">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病例号：</td>
        <td><input type="text" name="id" value="${lm.id }" onblur="bing()"/></td>
		
        <td width="10%" class="tableleft">主治医生：</td>
        <td><input type="text" name="doctor" value="${lm.dor}"/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td><input type="text" name="keshi" value="${lm.keshi}"/></td>
    </tr>
    <tr>
		
        <td width="10%" class="tableleft">住院时间：</td>
		
		  <td colspan="5">
			<input type="text" name="qtime" value="${lm.qtime}" onclick="piker()" onblur="qqq()"/>&nbsp;至&nbsp;
			<input type="text" name="htime" value="${lm.htime}" onclick="piker1()" onblur="chackTime()"/>
			<span id="spmh"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <button type="submit" class="btn btn-primary" type="button">查询</button> 
            <button class="btn btn-primary" type="button" onclick="qingkong()">清空</button> 
			
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
        <th>床位号</th>
        <th>联系电话</th>
        <th>押金</th>
        <th>主治医生</th>
        <th>入院时间</th>
        <th>科室</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <c:forEach var="b" items="${behList}">
    	<tr >
         	<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${b.beh_id }"></td>
            <td style="vertical-align:middle;">${b.beh_id }</td>
            <td style="vertical-align:middle;">${b.hosr_name }</td>
            <td style="vertical-align:middle;">${b.beh_patbed }</td>
            <td style="vertical-align:middle;">${b.hosr_phone }</td>
            <td style="vertical-align:middle;">${b.beh_antecedent }</td>
            <td style="vertical-align:middle;">${b.d_name }</td>
            <td style="vertical-align:middle;">${b.beh_time }</td>
            <td style="vertical-align:middle;">${b.keshi }</td>
            <c:choose>
            	<c:when test="${b.hosr_state==1}">
            		<td style="vertical-align:middle;">已住院</td>
            		<td style="vertical-align:middle;">
            		<input type="hidden" value="${b.hosr_state}" name="state"/>
					<a href="javascript:void(0);" onclick="lookOneInfos(${b.beh_id })" style="cursor: pointer;">详情>></a>&nbsp;&nbsp;&nbsp;
					<a style='cursor: pointer;' href="javascript:void(0);" onclick="lookEditInfos(${b.beh_id })">更改</a>&nbsp;&nbsp;&nbsp;
					<a style="cursor: pointer;" href="javascript:void(0);" onclick="lookOneMany(${b.beh_id })">缴纳押金</a>&nbsp;&nbsp;&nbsp;	
					</td>
            	</c:when>
            	<c:when test="${b.hosr_state==2}">
            		<td style="vertical-align:middle;">已结算</td>
            		<td style="vertical-align:middle;">
            		<input type="hidden" value="${b.hosr_state}" name="state"/>
					<a href="javascript:void(0);" onclick="lookOneInfos(${b.beh_id })" style="cursor: pointer;">详情>></a>&nbsp;&nbsp;&nbsp;				
					<a href="javascript:void(0);" onClick="tui(${b.beh_id });">退院</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0);" onClick="chu(${b.beh_id });">出院</a>
					</td>
            	</c:when>
            	<c:when test="${b.hosr_state==4}">
            		<td style="vertical-align:middle;">已出院</td>
            		<td style="vertical-align:middle;">
            		<input type="hidden" value="${b.hosr_state}" name="state"/>
					<a href="javascript:void(0);" onclick="lookOneInfos(${b.beh_id })" style="cursor: pointer;">详情>></a>&nbsp;&nbsp;&nbsp;
					</td>
            	</c:when>
            	<c:when test="${b.hosr_state==5}">
            		<td style="vertical-align:middle;">已退院</td>
            		<td style="vertical-align:middle;">
            		<input type="hidden" value="${b.hosr_state}" name="state"/>
					<a href="javascript:void(0);" onclick="lookOneInfos(${b.beh_id })" style="cursor: pointer;">详情>></a>&nbsp;&nbsp;&nbsp;
					</td>
            	</c:when>
            </c:choose>
            
            
        </tr>
    </c:forEach>
  </table>
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
          
		 <p:page pageSize="${page.pageSize }" url="BeHospitalServlet.do?type=${type}&params=${lm.id}&params2=${lm.dor}&params3=${lm.keshi}&params4=${lm.qtime}&params5=${lm.htime}" totalPage="${page.totalPage}" count="${page.count}" nowPage="${page.nowPage}"/>
		  </div>
		 <div><button type="button" class="btn btn-success" id="newNav">添加住院信息</button>&nbsp;&nbsp;&nbsp;
		 <button type="button" class="btn btn-success" id="delPro" onClick="delAll(1);">出院</button>&nbsp;&nbsp;&nbsp;
		 <button type="button" class="btn btn-success" id="delPro" onClick="delAll(2);">退院</button>&nbsp;&nbsp;&nbsp;
		 <button type="button" class="btn btn-success" id="delPro" onClick="daoChu();">导出Excel</button>
		 </div>
		 
		 </th></tr>
  </table>  
</body>
</html>
