<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.util.List" %>
<%@page import="com.zrgk.bean.UserBean" %>
<%@page import="com.zrgk.bean.DoctorBean" %>
<%@page import="com.zrgk.bean.HosrBean" %>
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
    <title>门诊医生--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
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
				window.location.href="DoctorServlet.do?method=selDoctor";
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
		
		function clean(){
			//清除查询内容
			document.getElementById("docode").value="";
			document.getElementById("doname").value="";
			document.getElementById("dokeshi").value="";
			window.location.href="DoctorServlet.do?method=query?";
		}
		
		
		function putExport(){
			//到处excal
			
				var idArray=document.getElementsByName("check");
				var newArray=new Array();
				var count=0;
				for(var i=0;i<idArray.length;i++){
					if(idArray[i].checked ){
					//被选中
						
						newArray[count]=idArray[i].value;
						alert(newArray);
						count++;
					}
				}
				if(newArray.length != 0){
					if(confirm("确认导出？")){
					//异步
				
					window.location.href="DoctorServlet.do?method=excal&allId="+newArray;
				
					}
				}else{
						alert("请选择您需要导出的信息");
					}
			
		}
		
	function check(){
		//验证查找信息是否为空
		var bln=false;
		var number=document.getElementById("docode").value;
		var c = number.replace(/\s+/g,"");
		document.getElementById("docode").value=c;
		var cc=c.split(".");
		if(isNaN(c) || cc.length != 1){
			alert("您输入的查询数据有误，医生编码只能为纯数字");
			bln=false;
		}else{

			bln=true;
		}
			
			return bln;
	}
		
		function lokUp(doctorId){
			window.location.href="DoctorServlet.do?method=information&did="+doctorId;
		}
		
		function ed(doctorId){
			window.location.href="DoctorServlet.do?method=updated&did="+doctorId;
		}
		
		
    </script>
</head>
<body>

<%List<DoctorBean> dlist=(List<DoctorBean>)session.getAttribute("dlist"); %>
<form action="DoctorServlet.do?method=select" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">医生编号：</td>
        <td><input type="text" name="pcode" value="${pcode }" id="docode"/></td>
		
        <td width="10%" class="tableleft">医生姓名：</td>
        <td><input type="text" name="pname" value="${pname }" id="doname"/></td>
		
        <td width="10%" class="tableleft">科室：</td>
        <td><input type="text" name="pkeshi" value="${pkeshi }" id="dokeshi"/></td>
    </tr>
    <tr>
		  <td colspan="6">
		  <center>
            <input type="submit" class="btn btn-primary" onclick="return check()" value="查询"/> 
            <button type="submit" class="btn btn-primary" type="button" onclick="clean()">清空</button> 
		  </center>
        </td>
    </tr>
</table>
</form>
   
<table class="table table-bordered table-hover definewidth m10" >
   <thead>
    <tr>
    	<th><input type="checkbox" id="checkall" onChange="checkall();"></th>
        <th>医生编号</th>
        <th>医生姓名</th>
        <th>入院时间</th>
        <th>所属科室</th>
        <th>操作</th>
    </tr>
    </thead>
         	<c:forEach var="doctor" items="${doctorlist}"  >
         		<tr >
         		<td style="vertical-align:middle;"><input type="checkbox" name="check" value="${doctor.d_id }"></td>
            <td style="vertical-align:middle;">${doctor.d_code }</td>
            <td style="vertical-align:middle;">${doctor.d_name }</td>
            <td style="vertical-align:middle;">${doctor.d_intime }</td>
            <td style="vertical-align:middle;">${doctor.d_keshi }</td>
            <td style="vertical-align:middle;"><a href="javascript:void(0);" onclick="lokUp(${doctor.d_id })">详情>>></a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="ed(${doctor.d_id })">更改</a></td>
             </tr>
         	</c:forEach>
         	  
  </table>
  
  <table class="table table-bordered table-hover definewidth m10" >
  	<tr><th colspan="5">  <div class="inline pull-right page">
          
          <p:page pageSize="${pageSize}" url="DoctorServlet.do?method=${method}&pname=${pname}&pcode=${pcode}&pkeshi=${pkeshi}" totalPage="${totalPage }" count="${dCount }" nowPage="${nowPage }"/>
          
		  </div>
		 <div><button type="button" class="btn btn-success" id="newNav">添加新医生</button>&nbsp;<button type="button" class="btn btn-success" id="delPro" onclick="putExport()">导出Excel</button>
		 </div>
		 
		 </th></tr>
  </table>
  
</body>
</html>
