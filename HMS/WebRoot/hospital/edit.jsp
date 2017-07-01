<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>修改住院信息--中软高科-2015</title>
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
    <script type="text/javascript" src="Js/hospital/edit.js"></script>
 

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
<body onload="test2()">

<form action="BeHospitalServlet.do?type=editInfos" onsubmit="return test()" method="post" class="definewidth m20">
<input type="hidden" name="eid" value="${tList.hosr_id }" />
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td style="readonly:'readonly'">${tList.hosr_id }</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td style="readonly:'readonly'">${tList.hosr_name}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td style="readonly:'readonly'">身份证</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td style="readonly:'readonly'">${tList.hosr_idcar}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td style="readonly:'readonly'">${tList.hosr_medical}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td style="readonly:'readonly'">${tList.hosr_phone}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <c:if test="${tList.hosr_selfprice==1}">
        <td><input type="radio" name="pname0" value="1" disabled="disabled" checked/>否&nbsp;&nbsp;&nbsp;
        <input type="radio" name="pname0" value="0" disabled="disabled"/>是</td>
        </c:if>
        <c:if test="${tList.hosr_selfprice==0}">
        <td><input type="radio" name="pname0" value="1" disabled="disabled"/>否&nbsp;&nbsp;&nbsp;
        <input type="radio" name="pname0" value="0" disabled="disabled" checked/>是</td>
        </c:if>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <c:if test="${tList.hosr_sex=='男'}">
        <td ><input type="radio" name="sex" value="男" disabled="disabled" checked/>男&nbsp;&nbsp;&nbsp;
        <input type="radio" name="sex" value="女" disabled="disabled"/>女</td>
        </c:if>
        <c:if test="${tList.hosr_sex=='女'}">
        <td><input type="radio" name="sex" value="男" disabled="disabled"/>男&nbsp;&nbsp;&nbsp;
        <input type="radio" name="sex" value="女" disabled="disabled" checked/>女</td>
        </c:if>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        <td style="readonly:'readonly'">${tList.hosr_age}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <c:if test="${tList.hosr_lookdoctor==0}">
        <td><input type="radio" name="lookd" value="0" disabled="disabled" checked/>初诊&nbsp;&nbsp;&nbsp;
        <input type="radio" name="lookd" value="1" disabled="disabled"/>复诊</td>
        </c:if>
        <c:if test="${tList.hosr_lookdoctor==1}">
        <td ><input type="radio" name="lookd" value="0" disabled="disabled"/>初诊&nbsp;&nbsp;&nbsp;
        <input type="radio" name="lookd" value="1" disabled="disabled" checked/>复诊</td>
        </c:if>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td><select disabled="disabled"><option>${tList.keshi}</option></select></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td><select disabled="disabled"><option >${tList.d_name}</option></select></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>${tList.hosr_remark} </td>
	</tr>
    <tr>
        <td width="10%" class="tableleft">护理</td>
        <td><input type="text" name="nursepeople" value="${tList.beh_nursepeople}" onblur="panduan1()"/>
        <span id="sp"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">床位号</td>
        <td><input type="text" name="patbed" value="${tList.beh_patbed}" onblur="panduan2()"/><span id="sp2"></span></td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">病情</td>
        <td><textarea name="illness">${tList.beh_illness}</textarea></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary" type="button" onclick="tishi()">保存</button> &nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>