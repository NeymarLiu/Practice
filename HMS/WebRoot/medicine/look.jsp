<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@  taglib  uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
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
				window.location.href="DrugServlet.do?method=getAllDrug";
		 });
    });
    </script>
</head>
<body>
<form action="DrugServlet.do?method=editChoose&id=${db.dr_id }" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">药品编号</td>
        <td name="id" value="${db.dr_id }">${db.dr_id }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">图片</td>
        <td><img src="${db.dr_url }"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">进价</td>
        <td>${db.dr_inprice }元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">售价</td>
        <td>${db.dr_outprice }元</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品名称</td>
        <td>${db.dr_name }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">药品类型</td>
        <td>${db.dr_type }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">简单描述</td>
        <td>${db.dr_simdesc }</td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">过期日期</td>
        <td>${db.dr_savetime }月</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">详细描述</td>
        <td>${db.dr_desc }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">生产厂商</td>
        <td>${db.dr_factory }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">服用说明</td>
        <td>${db.dr_direction }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">库存</td>
        <td>${db.dr_number }</td>
    </tr>
    <%-- <tr>
        <td width="10%" class="tableleft">剩余量</td>
        <td>${db.dr_number }</td>
    </tr> --%>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${db.dr_remark }</td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<a href="DrugServlet.do?method=editChoose&id=${db.dr_id }" class="btn btn-primary" type="button">更改</a> 
				<button type="button" class="btn btn-primary" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>