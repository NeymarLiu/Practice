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
    <title>添加收费项目--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>Css/style.css" />
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
				window.location.href="WHosrServlet.do?method=initWHosr";
		 });
    });
    
    function getprice(obj){
    	var d=document.getElementById("price"+obj);
    	var d2=document.getElementsByName("pname")[0];
    	d2.value=d.value;
    } 
    function test1(){
    	var meg=document.getElementById("meg").value;
    	if(meg != null && meg != ""){
    		if(meg){
    		alert("保存成功！");
    		}
    	}
    }
    function test2(){
    	var ss=document.getElementsByName("cps")[0].value;
    	if(ss>0){
    	return true;
    	}else if(ss == 0){
    	alert("请选择要添加的收费项！");
    	return false;
    	}
    	
    }
    </script>
</head>
<body onload="test1()">
<input type="hidden" value="${meg}" id="meg"/>
<form action="WHosrServlet.do?method=insert&id=${hors_id}&name=${hors_name } " method="post" class="definewidth m20" onsubmit="return test2()">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td>${hors_id}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${hors_name}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费项目</td>
        <td><select name="cps" onchange="getprice(this.value)" >
       			 <option value="0">--请选择--
        		<c:forEach var="cp" items="${list}">
        		<option value="${cp.cp_id }" >${cp.cp_name }
        		</c:forEach>
        	</select> 
        	<input  type="hidden"  id="price0"  value="">
        	<c:forEach var="cp" items="${list}">
        	<input  type="hidden"  id="price${cp.cp_id }"  value="${cp.cp_price}">
        	</c:forEach>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费金额</td>
        <td><input type="text" name="pname" value="" readonly="readonly" style="cursor: pointer;"/></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>