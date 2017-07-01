<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				window.location.href="BeHospitalServlet.do?type=mohu";
		 });
    });
    </script>
</head>
<body>
<form action="hospital/index.jsp" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${hbList.hosr_name}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td>身份证</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td>${hbList.hosr_idcar}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td>${hbList.hosr_medical}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td>${hbList.hosr_phone}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <c:if test="${hbList.hosr_selfprice==1}">
        <td><input type="radio" name="pname0" value="1" disabled="disabled" checked/>否&nbsp;&nbsp;&nbsp;
        <input type="radio" name="pname0" value="0" disabled="disabled"/>是</td>
        </c:if>
        <c:if test="${hbList.hosr_selfprice==0}">
        <td><input type="radio" name="pname0" value="1" disabled="disabled"/>否&nbsp;&nbsp;&nbsp;
        <input type="radio" name="pname0" value="0" disabled="disabled" checked/>是</td>
        </c:if>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <c:if test="${hbList.hosr_sex=='男'}">
        <td ><input type="radio" name="sex" value="男" disabled="disabled" checked/>男&nbsp;&nbsp;&nbsp;
        <input type="radio" name="sex" value="女" disabled="disabled"/>女</td>
        </c:if>
        <c:if test="${hbList.hosr_sex=='女'}">
        <td><input type="radio" name="sex" value="男" disabled="disabled"/>男&nbsp;&nbsp;&nbsp;
        <input type="radio" name="sex" value="女" disabled="disabled" checked/>女</td>
        </c:if>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td>${hbList.hosr_age}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">职业</td>
        <td>${hbList.hosr_word}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <c:if test="${hbList.hosr_lookdoctor==0}">
        <td><input type="radio" name="lookd" value="0" disabled="disabled" checked/>初诊&nbsp;&nbsp;&nbsp;
        <input type="radio" name="lookd" value="1" disabled="disabled"/>复诊</td>
        </c:if>
        <c:if test="${hbList.hosr_lookdoctor==1}">
        <td ><input type="radio" name="lookd" value="0" disabled="disabled"/>初诊&nbsp;&nbsp;&nbsp;
        <input type="radio" name="lookd" value="1" disabled="disabled" checked/>复诊</td>
        </c:if>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td>${hbList.keshi}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td>${hbList.d_name}</td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${hbList.hosr_remark}</td>
	</tr>
	<tr>
        <td width="10%" class="tableleft">护理</td>
        <td>${hbList.beh_nursepeople}</td>
    </tr>
	 <tr>
        <td width="10%" class="tableleft">床位号</td>
        <td>${hbList.beh_patbed}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">押金余额</td>
        <td>${hbList.beh_leftmoney}</td>
    </tr>
	
	<tr>
        <td width="10%" class="tableleft">病情</td>
        <td>${hbList.beh_illness}</td>
	</tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>