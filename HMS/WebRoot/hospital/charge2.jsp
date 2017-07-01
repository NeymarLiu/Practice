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
    <title>收费项目管理</title>
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
				window.location.href="hospital/charge-new.jsp";
		 });
    });
    $(function () {       
		$('#backid').click(function(){
				window.location.href="hospital/dispensing.jsp";
		 });
    });
    
    function WHosrinfo(obj){
    	window.location.href="WHosrServlet.do?method=WHosrinfo&id="+obj;
    }
    function update(){
    	window.location.href="WHosrServlet.do?method=initWHosr";
    }
    
    function check(){
    	b=true;
  		var d=document.getElementsByName("inp")[0].value;
  		var sp=document.getElementById("sp");
  		var sp2=document.getElementById("sp2");
  		var regex=/^[1-9][0-9]*/;
  		if(regex.test(d)){
  		b=true;
  		sp.innerHTML="<font color='green'>可用</font>";
  		sp2.innerHTML="";
  			var regex2=/\s+/g;
  			var d2=d.replace(regex2,"");
  			sp2.innerHTML=d2;
  		} else{
  		b=false;
  		sp.innerHTML="<font color='red'>不可用</font>";
  		sp2.innerHTML="";
  		}
  	}
  	function aa(){
    
    	return b;
    }
    </script>
</head>
<body>
   
<form action="WHosrServlet.do?method=mohu" method="post" class="definewidth m20" onsubmit="return aa()">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号：</td>
        <td><input type="text" name="inp" onblur="check()"  value="${id}"><span id="sp"></span><span id="sp2" ></span></td>
        <td width="10%" class="tableleft">姓名：</td>
        <td><input type="text" name="name" value="${name }"/></td>
    </tr>
    <tr>
		
		
		  <td colspan="4">
			<center>
				<button type="submit" class="btn btn-primary" type="button">查询</button> 
				<button class="btn btn-primary" type="button" onclick="update()">清空</button> 
			</center>
        </td>
    </tr>
</table>
</form>
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
        <th>病历号</th>
        <th>姓名</th>
        <th>住院日期</th>        
        <th>操作</th>
    </tr>
    </thead> 
    	<c:forEach var="WHosr" items="${wHosrs}">
	    <tr >
            <td style="vertical-align:middle;">${WHosr.hosr_id}</td>
            <td style="vertical-align:middle;">${WHosr.hosr_name}</td>
            <td style="vertical-align:middle;">${WHosr.beh_time}</td>
            <td style="vertical-align:middle;">
				<a href="javascript:void(0);" onclick="WHosrinfo(${WHosr.hosr_id})">查看详情</a>
				<a href="WHosrServlet.do?method=add&bid=${WHosr.hosr_id}&name=${WHosr.hosr_name}">添加收费项</a>
			</td>
        </tr>
	    </c:forEach>
		
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
		 <p:page pageSize="${pageSize }" url="WHosrServlet.do?method=${method}&id=${id }&name=${name }"  totalPage="${pages }" count="${count }" nowPage="${nowPage }"/>
		  </div>
		 </th></tr>
  </table>
  
</body>
</html>
