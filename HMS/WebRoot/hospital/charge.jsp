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
    <title>收费项目登记</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>

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
		$('#newNav').click(function(){
				window.location.href="ChargeProjectServlet.do?method=idinsert";
		 });
    });
	
    	function checkall(){
			var alls=document.getElementsByName("check");
			var ch=document.getElementById("checkall");
			if(ch.checked){
				for(var i=0;i<alls.length;i++){
					alls[i].checked=true;	
				}	
			}else{
				for(var i=0;i<alls.length;i++){
					alls[i].checked=false;	
				}	
			}
		}
		function delAll(){
			var alls=document.getElementsByName("check");
			var ids=new Array();
			for(var i=0;i<alls.length;i++){
				if(alls[i].checked){
					ids.push(alls[i].value);
				}		
			}
			if(ids.length>0){
				if(confirm("确认操作?")){
					alert("成功!");
				}
			}else{
				alert("请选中要操作的项");
			}
		}
    $(function () {       
		$('#backid').click(function(){
				window.location.href="hospital/dispensing.jsp";
		 });
    });
    function update(cpid){
    	window.location.href="ChargeProjectServlet.do?method=editCharge&did="+cpid;
    }
    function chargeInfo(cpid){
    	window.location.href="ChargeProjectServlet.do?method=chargeInfo&did="+cpid;
    }
    function deleteCharge(cpid){
    	if(confirm("确认操作?")){
    	window.location.href="ChargeProjectServlet.do?method=delete&id="+cpid;
    	}
    }
    function clean(){
    	window.location.href="ChargeProjectServlet.do?method=initCharge"
    }
    function test1(){
    	var meg=document.getElementById("meg").value;
    	if(meg != null && meg != ""){
    		if(meg){
    		alert("保存成功！");
    		}
    	}
    }
    
    </script>
</head>
<body onload="test1()">
  <input type="hidden" value="${meg}" id="meg">
<form action="ChargeProjectServlet.do?method=mohu" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="13%" class="tableleft">收费项目名称：</td>
        <td width="10%"><input type="text" name="name" value="${name }"/></td>
		
        <td>
		<button type="submit" class="btn btn-primary" type="button">查询</button> 
			<button class="btn btn-primary" type="button" onclick="clean()">清空</button> </td>
    </tr>
   
</table>


</form>
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
        <th>编号</th>
        <th>收费项目名称</th>        
        <th>收费金额</th>
        <th>创建日期</th>
        <th>操作</th>
    </tr>
    </thead>
	    <c:forEach var="chargeProject" items="${chargeProjects }">
	    	 <tr >
            <td style="vertical-align:middle;">${chargeProject.cp_id +1100}</td>
            <td style="vertical-align:middle;">${chargeProject.cp_name}</td>
            <td style="vertical-align:middle;">${chargeProject.cp_price }</td>
            <td style="vertical-align:middle;">${chargeProject.cp_date }</td>
            <td style="vertical-align:middle;">
				<a href="javascript:void(0)" onclick="update(${chargeProject.cp_id })">修改</a>
				<a href="javascript:void(0)" onclick="deleteCharge(${chargeProject.cp_id})">删除</a>
				<a href="javascript:void(0)" onclick="chargeInfo(${chargeProject.cp_id })">查看详情
			</td>
        </tr>
	    </c:forEach>
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
  		<p:page pageSize="${pageSize }" url="ChargeProjectServlet.do?method=${method}&name=${name }"  totalPage="${pages }" count="${count }" nowPage="${nowPage }"/>
		  </div>
		 <div><button type="button" class="btn btn-success" id="newNav" >添加收费项目</button>
		 
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>
