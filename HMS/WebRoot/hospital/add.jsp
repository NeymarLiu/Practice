<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>录入住院信息--中软高科-2015</title>
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
 	<script type="text/javascript" src="Js/hospital/add.js"></script>

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
<form action="BeHospitalServlet.do?type=tianjia" onsubmit="return test()" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10" id="tableId">
    <tr>
        <td width="10%" class="tableleft">病历号 </td>
        <td><input type="text" name="id" id="id" onblur="bing()" onkeyup="bingli()" autocomplete="off" disableautocomplete /><span id="sp"></span>
        <div id="divmenus" name="divmenus"  style="position:absolute;z-index:10;width: 180px;border-width:5px;border-color: #FFF;" >
    					<!-- div -->
    	</div>
        </td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td id="name" style="readonly:'readonly'"></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">证件类型</td>
        <td id="sheng" style="readonly:'readonly' ; "></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">证件号</td>
        <td id="idcard" style="readonly:'readonly'"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">社保号</td>
        <td id="secard" style="readonly:'readonly'"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">联系电话</td>
        <td id="phone" style="readonly:'readonly'"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">是否自费</td>
        <td><input type="radio" name="self" value="1" disabled="disabled"/>否&nbsp;&nbsp;&nbsp;
        <input type="radio" name="self" value="0" disabled="disabled"/>是</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">性别</td>
        <td><input type="radio" name="sex" value="男" disabled="disabled"/>男&nbsp;&nbsp;&nbsp;
        <input type="radio" name="sex" value="女" disabled="disabled"/>女</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">年龄</td>
        
        <td id="age" style="readonly:'readonly'"></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">初复诊</td>
        <td><input type="radio" name="look" value="0" disabled="disabled"/>初诊&nbsp;&nbsp;&nbsp;
        <input type="radio" name="look" value="1" disabled="disabled"/>复诊</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">所挂科室</td>
        <td><select disabled="disabled" name="ke"><option id="keshi"></option></select></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">指定医生</td>
        <td><select disabled="disabled"><option id="doctor"></option></select></td>
    </tr>
	<tr>
        <td width="10%" class="tableleft">备注</td>
        <td>暂无  以上信息为输入病历号自动带出来的</td>
	</tr>
    <tr>
        <td width="10%" class="tableleft">护理</td>
        <td><input type="text" name="nursepeople" value="" onblur="panduan1()"/>
		<span id="sp1"></span>
		</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">床位号</td>
        <td><input type="text" name="patbed" value="" onblur="panduan2()"/><span id="sp2"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">缴费押金</td>
        <td><input type="text" name="money" value="" onblur="panduan3()"/><span id="sp3"></span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">病情</td>
        <td><textarea name="illness" onblur="panduan4()"></textarea><span id="sp4"></span></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;
				<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>