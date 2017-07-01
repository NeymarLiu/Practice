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
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <title></title>
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
	<link href='Css/Ps/ps.css' rel='stylesheet' type='text/css'>
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
	<script type="text/javascript" src="Js/User/jquery.min.js"></script>
	<script type="text/javascript" src="Js/User/add.js"></script>
	<script type="text/javascript" src="Js/ajax.js"></script>
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
<form action="#" method="post" class="definewidth m20">
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">登录名</td>
            <td  ><input type="text" name="loginName" value="" onkeyup="CheckLogin(this.value)"/>
            	<span id="splo"></span>
            </td>
        </tr>
        <tr>
            <td class="tableleft" >密码</td>
            <td><input type="password" name="pass"  id="pass" value=""  style="margin: 0;padding: 0" />
            <img id="im" height="30px" width="30px" src="Images/ps.jpg" onclick="ShowPs()">
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
        <tr>
            <td class="tableleft">真实姓名</td>
            <td ><input type="text" name="trueName" value="" onblur="CheckName(this.value)"/>
            <span id="spna"></span>
            </td>
        </tr>
        <tr>
            <td class="tableleft">身份证号码</td>
            <td><input type="text" name="idcard" value="" onkeyup="CheckId(this.value)"/>
            	<span id="spid"></span>
            </td>
        </tr>
          <tr>
            <td class="tableleft">性别</td>
            	<td><input type="radio" name="sex" value="0" disabled/>男
           		<input type="radio" name="sex" value="1" disabled/>女</td>
        </tr>
         <tr>
            <td class="tableleft">联系电话</td>
            <td><input type="text" name="phone" value="" onkeyup="CheckPhone(this.value)"/>
            <span id="spph"></span>
            </td>
        </tr>
        <tr>
            <td class="tableleft">邮箱</td>
            <td><input type="text" name="email" value="" onkeyup="CheckEmail(this.value)"/>
            <span id="spem"></span>
            </td>
        </tr>
          <tr>
            <td class="tableleft">住址</td>
            <td><input type="text" name="address" value="" onblur="CheckAddress(this.value)"/>
            	<span id="spad"></span>
            </td>
        </tr>
        <tr>
            <td class="tableleft">状态</td>
            <td>
                <input type="radio" name="status" value="0" checked/> 启用
              	<input type="radio" name="status" value="1" /> 禁用
            </td>
        </tr>
        <tr>
            <td class="tableleft">角色</td>
            <td>
            	<select name="role" onclick="CheckRole()">
        			<option value="" >--请选择--
            		<c:forEach var="role" items="${roles}">
        			<option value="${role.r_id }" >${role.r_name }
            		</c:forEach>
       			 </select>
       			 <span id="spro"></span>
        	</td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button class="btn btn-primary" type="button" onclick="InsertUser()">添&nbsp;加</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form></body>
</html>