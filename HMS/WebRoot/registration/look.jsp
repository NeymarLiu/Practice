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
    <title>查看--中软高科-2015</title>
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
				window.location.href="registration/index.jsp";
		 });
    });
    </script>
</head>
<body>
<a style="align:right" href="ReigisterServlet.do?method=edit&mid=${allHosr.hosr_id }">编辑</a>
<form action="registration/index.html" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${allHosr.hosr_name }</td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">身份证号</td>
        <td>${allHosr.hosr_idcar }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">挂号费</td>
        <td>${allHosr.hosr_regprice }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td>${allHosr.hosr_medical }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td>${allHosr.hosr_phone }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td>
        	<c:if test="${allHosr.hosr_selfprice == 0 }">是</c:if>
        	<c:if test="${allHosr.hosr_selfprice == 1 }">否</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td>${allHosr.hosr_sex }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td>${allHosr.hosr_age }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">职业</td>
        <td>${allHosr.hosr_word }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td>
        	<c:if test="${allHosr.hosr_lookdoctor == 0 }">是</c:if>
        	<c:if test="${allHosr.hosr_lookdoctor == 1 }">否</c:if>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td>${allHosr.keshi }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td>${doctor.d_name }</td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${allHosr.hosr_remark }</td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<a href="ReigisterServlet.do?method=query" class="btn btn-success">返回列表</a>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>