<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/Css/style.css" />
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/bootstrap.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/ckform.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>/Js/Role/check.js"></script>
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
<body >
<input type="hidden" value="${ub.u_role}" id="user">
<input type="hidden" value="${page}" name="page">
<input type="hidden" value="${type}" name="type">
<input type="hidden" value="${params}" name="params">
<form action="RoleServlet.do?method=changeMenus&roleid=${rb.r_id }" method="post" class="definewidth m20"  >
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">角色名称</td>
        <td><input type="text" name="title" value="${rb.r_name }" onclick="clearTitle()" onkeyup="jugeUnique(${rb.r_id })"/>
        	<span id="sp"></span>
        </td>
    </tr>
    <tr>
        <td class="tableleft">状态</td>
        <td>
            <c:if test="${rb.r_state==0}">
            <input type="radio" name="status" value="0" checked /> 启用
           	<input type="radio" name="status" value="1"  /> 禁用
            </c:if>
            <c:if test="${rb.r_state==1}">
            <input type="radio" name="status" value="0"  /> 启用
           	<input type="radio" name="status" value="1" checked /> 禁用
            </c:if>
        </td>
    </tr>
    <tr>
        <td class="tableleft">权限</td>
        <td>
			<c:forEach var="mb" items="${mbs}">
				<c:if test="${mb.flag==1}">
					<ul><label class='checkbox inline'><input type='checkbox'  checked name='group[]' value='${mb.m_id }' />${mb.m_name}</label></ul>
				</c:if>
				<c:if test="${mb.flag==0}">
					<ul><label class='checkbox inline'><input type='checkbox'  name='group[]' value='${mb.m_id }' />${mb.m_name}</label></ul>
				</c:if>
			</c:forEach>
		</td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button  class="btn btn-primary" type="button" onclick="updateMenu(${rb.r_id})">更&nbsp;新</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" onclick="defaultSelect(${rb.r_id})" >恢复默认</button>&nbsp;&nbsp;
            <button type="button" class="btn btn-success" onclick="clearSelect()" >清空选项</button>&nbsp;&nbsp;
            <button type="button" class="btn btn-success" id="backid">返回列表</button>  
        </td>
    </tr>
</table>
</form>
</body>
</html>
