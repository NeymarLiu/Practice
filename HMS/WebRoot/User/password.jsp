<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.zrgk.bean.UserBean" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
	<meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
	<link href='Css/Ps/ps.css' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
	<script type="text/javascript" src="Js/User/jquery.min.js"></script>
	<script type="text/javascript" src="Js/User/add.js"></script>
  </head>
  <script type="text/javascript">
  function oldps(){
	  var b=false;
	  var ops=document.getElementsByName("password")[0].value;
	  var ops2=document.getElementsByName("ps")[0].value;
	  var osp=document.getElementById("osp");
	  if(ops != ""){
		  if(ops2 == ops){
			  osp.innerHTML="<font color='green'>√</font>";
			  b=true;
		  }else{
			  osp.innerHTML="<font color='red'>原密码错误</font>";
			  b=false;
		  }  
	  }else{
		  osp.innerHTML="<font color='red'>原密码不能为空</font>";
	  }
	 
	  return b;
  }
  function onps(){
	  var ps=document.getElementById("pass").value;
		 var b3=CheckPas(ps);
		 return b3;
}
  function newps(){
	  var b=false;
	  var nps=document.getElementsByName("pass")[0].value;
	  var rps=document.getElementsByName("repassword")[0].value;
	  var osp=document.getElementById("rps");
	  if(nps==rps&&nps!=""){
		  osp.innerHTML="<font color='green'>√</font>";
		  b=true;
		  
	  }else if(rps==""){
	  		osp.innerHTML="<font color='red'>密码不能为空!</font>";
	  		b=false;
	  }else{
		  osp.innerHTML="<font color='red'>密码不一致!</font>";
		  b=false;
	  }
	  return b;
	  
  }
 function aa(){
 	 var b=false;
	 var b1=oldps();
	 var b2=newps();
	 var b3=onps();
	// alert(b4);
	 
	 if(b1&&b2&&b3){
			 b=true;
		  } 
		 return b; 
 }

  </script>
  <body>
  <%UserBean ub=(UserBean)request.getAttribute("ub"); %>
   <form class="form-inline definewidth m20" action="LoginServlet.do?method=password" method="post" onsubmit="return aa();">  
   <input type="hidden" value="${ub.u_id }" name="id"> 
   <input type="hidden" value="${ub.u_password}" name="ps">  
   <table>
		<tr>
			<td align="right">原密码:</td><td><input type="password" name=password onblur="oldps()"/>
			<span id="osp"></span>
			</td>
		</tr>
	    <tr>
			 <td class="tableleft" >新密码:</td>
            <td><input type="password" name="pass"  id="pass" value=""  style="margin: 0;padding: 0" onblur="onps()"/>
            <span id="spps"></span>
            <div id="level" class="pw-strength" > 
			<div class="pw-bar"></div>
			<div class="pw-bar-on"></div>
			<div class="pw-txt">
			<span>弱</span>
			<span>中</span>
			<span>强</span>
			</div>
			</div>	
            </td>
		</tr>
		<tr><td></td></tr>
		<tr>
			<td align="right">确认密码:</td><td><input type="password" name=repassword onblur="newps()"/>
			<span id="rps"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><br/>
				<input type="submit" value="保存" class="btn btn-primary"/>
			</td>
		</tr>
   </table>
</form>
  </body>
</html>
