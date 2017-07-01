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
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>/Js/ajax.js"></script>
     <script type="text/javascript" src="<%=basePath%>/Js/Resource/edit.js"></script>
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
<input type="hidden" value="${page}" name="page">
<input type="hidden" value="${type}" name="type">
<input type="hidden" value="${params}" name="params">
<form action="#" method="post" class="definewidth m20">
<input id="path" type="hidden" value="<%=basePath%>" >
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">资源名称</td>
            <td><input type="text" name="realname" value="${mb.m_name}" onkeyup="jugeUnique(${mb.m_id})"/>
            <span id="sp"></span>
            </td>
        </tr>
        <tr>
            <td class="tableleft">url</td>
            <td><input type="text" name="url" value="${mb.m_url}" onmouseover="this.title=this.value" onkeyup="getURL()"/>
            	<span id="sp2"></span>
            </td>
        </tr>
        <tr>
            <td class="tableleft">是否有效</td>
            
            <td>
            <c:if test="${mb.m_state==0}">
                <input type="radio" name="status" value="0" checked/> 有效
             	 <input type="radio" name="status" value="1" /> 无效
            </c:if>
             <c:if test="${mb.m_state==1}">
                <input type="radio" name="status" value="0" /> 有效
             	 <input type="radio" name="status" value="1"  checked/> 无效
            </c:if>
            </td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="button" class="btn btn-primary" onclick="updateResource(${mb.m_id})">更新</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
