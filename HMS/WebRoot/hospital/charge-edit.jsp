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
				window.location.href="ChargeProjectServlet.do?method=initCharge";
		 });
    });
    function getXhr(){
    		var xhr;
    		try{
    			xhr= new XMLHttpRequest();
	    	}catch(e){
	    		try{
	    			xhr=new ActiveXObject("Microsoft.XMLHTTP");
	    		}catch(e){
	    			try{
	    				xhr=new ActiveXObject("Msxm12.XMLHTTP");
	    			}catch(e){
	    				alert("你的浏览器不支持ajax，请跟换浏览器，推荐火狐。");
	    			}
	    		}
	    	}
	    	return xhr;
    	}
    	function onlyname(){
    	    b=false;
    		var id=document.getElementsByName("cpid")[0].value;
    		var name=document.getElementsByName("cp_name")[0].value;
    		var sp=document.getElementById("sp");
    		//alert(name);
    		if(name!=null && name!=""){
    		var xhr=getXhr();
    		//alert(xhr);
    		xhr.open("get", "ChargeProjectServlet.do?method=onlyname2&id="+id+"&name="+name+"&time="+new Date().getTime(),false);
    		//xhr.setRequestHeader("Content-type", "application/x-ww-form-urlencoded");
    		xhr.onreadystatechange = function() {
	    		if(xhr.readyState == 4 && xhr.status ==200){
	    			// 四个状态值
	    			var result=xhr.responseText;
    				if(result=="0"){
    				b=true;
    				sp.innerHTML="<span style='color:green'>正确</span>";
    				}else if(result=="1"){
    				sp.innerHTML="<span style='color:red'>已存在</span>";
    				}
    			}
    		};
    		xhr.send(null);
    		}else{
    		sp.innerHTML="<span style='color:red'>请输入创建的项目名称</span>";
    		b=false;
    		}
    		return b;
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
<form action="ChargeProjectServlet.do?method=editAdd&id=${chargeProject.cp_id}" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">编号</td>
        <td><span id="ids">${chargeProject.cp_id+1100}</span>
        <input type="hidden" value="${chargeProject.cp_id}" name="cpid">
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费项目名称</td>
        <td><input type="text" name="cp_name" value="${chargeProject.cp_name }"  onblur="onlyname()" /><span id="sp"></span></td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">收费金额</td>
        <td><input type="text" name="cp_price" value="${chargeProject.cp_price }"/></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary" >保存</button> &nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>